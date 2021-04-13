package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import shared.Board;

/**
 * This class is a thread connects to each client
 */
public class ServerHandler extends Thread {

  private DataInputStream input;
  private DataOutputStream output;
  private Socket clientSocket;
  private int portNum;
  private HashMap<Integer, GameServer> gameRooms;
  private HashMap<String, String> userLogInfo;
  private HashMap<Integer, HashSet<String>> currentGames;
  private HashMap<GameServer, Integer> gameIDs;
  private String username;
  private static int gameID = 1000;
  private static String aiUsername = "AIUSER";
  private HashMap<Integer, Board> gameBoards;
  private HashMap<Integer, HashMap<String, String>> disconnectedUsers;
  private HashMap<Integer, Integer> disconnectedGames;

  public ServerHandler (DataInputStream input, DataOutputStream output, 
                          Socket clientSock, int portNum, HashMap<Integer, GameServer> gameRooms,
                          HashMap<String, String> userLogInfo, HashMap<Integer, HashSet<String>> currentGames,
                          HashMap<GameServer, Integer> gameIDs, HashMap<Integer, Board> gameBoards,
                          HashMap<Integer, HashMap<String, String>> disconnectedUsers,
                          HashMap<Integer, Integer> disconnectedGames) {
    this.input = input;
    this.output = output;
    this.clientSocket = clientSock;
    this.portNum = portNum;
    this.gameRooms = gameRooms;
    this.userLogInfo = userLogInfo;
    this.currentGames = currentGames;
    this.gameIDs = gameIDs;
    this.gameBoards = gameBoards;
    this.disconnectedUsers = disconnectedUsers;
    this.disconnectedGames = disconnectedGames;
  }

  public void run () {
      login();
      askInfo();
  }

  /**
   * This function asks the client whether to login or register
   */
  public void login() {
    try {
      while(true) {
        output.writeUTF("Register or Log in ? Input r to register, other to login.");
        String received = input.readUTF();
        if (received.equals("r")) {
          if (newAccount()) {
            break;
          }
        } else {
          if (authAccount()) {
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function creates an new account for the user
   * @return true or false whether the username repeats
   */
  public boolean newAccount() {
    try {
      //output.writeUTF("Please input your username and password.");
      String username = input.readUTF();
      //output.writeUTF("Please input your password.");
      String password = input.readUTF();
      if (userLogInfo.containsKey(username)) {
        output.writeUTF("Enter again.");
        return false;
      }
      userLogInfo.put(username, password);
      this.username = username;
      output.writeUTF("Login successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * This function validates the username and password when
   * the user choose to login
   * @return true or false whether it is authenticated
   */
  public boolean authAccount() {
    try {
      //output.writeUTF("Please input your username.");
      String username = input.readUTF();
      //output.writeUTF("Please input your password.");
      String password = input.readUTF();
      if (userLogInfo.containsKey(username)) {
        if (userLogInfo.get(username).equals(password)) {
          this.username = username;
          output.writeUTF("Login successfully.");
          return true;
        }
      }
      output.writeUTF("Enter again.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * This function asks the client whether wants to enter
   * an old game or choose a new game
   */
  public void askInfo() {
    try {
      output.writeUTF("Do you want to enter a previous game or enter a new game? Input p to select previous, others to create new.");
      String received = input.readUTF();
      if (received.equals("p")) {
        enterRoom();
      } else if (received.equals("a1")) {
        assignRoomForAI(1);
      } else if (received.equals("a2")) {
        assignRoomForAI(0);
      } else {
        assignRoom();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function assigns room for ai
   */
  public void assignRoomForAI(int aiNum) {
    System.out.println("assign Room for ai");
    GameServer gameServer = gameRooms.get(aiNum);
    gameServer.addClient(clientSocket);
    // ready to start the game
    if (gameServer.isReadyToStart()) {
      // store corresponding gameID
      gameIDs.put(gameServer, gameID);
      gameServer.setGameID(gameID);
      System.out.println("This room is ready to start, gameID: " + gameID);
      gameID++;
      // detach a new thread for a room
      Thread newGame = new Thread(gameServer);
      newGame.start();
    }
  }

  /**
   * This validates the roomID the user inputs
   */
  public void enterRoom() {
    try {
      String name = null;
      int room = 0;
      while(true) {
        String roomID = input.readUTF();
        try {
          room = Integer.parseInt(roomID);
        } catch(NumberFormatException e) {
          output.writeUTF("Wrong roomID.");
          continue;
        }
        // check the room id
        if (!disconnectedUsers.containsKey(room)) {
          output.writeUTF("Wrong roomID.");
          continue;
        } else {
          HashMap<String, String> users = disconnectedUsers.get(room);
          if (!users.containsKey(username)) {
            output.writeUTF("Wrong roomID.");
            continue;
          } else {
            name = users.get(username);
          }
        }
        output.writeUTF("Enter successfully.");
        break;
      }
      // get the old gameServer
      for (HashMap.Entry<GameServer, Integer> entry : gameIDs.entrySet()) {
        if (entry.getValue() == room) {
          GameServer gameServer = entry.getKey();
          gameServer.reconnectUser(clientSocket, name, username);
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This spawns a new thread for the game
   */
  public void assignRoom() {
    try {
      int playerNum = 0;
      output.writeUTF("Which game room do you want to enter? Input 2-5.");
      String received = input.readUTF();
      System.out.println("receive the data: " + received);
      try {
        playerNum = Integer.parseInt(received);
        output.writeUTF("Wait for other players to join.");
      } catch(NumberFormatException e) {
        e.printStackTrace();
      }

      /**
       * 1 human player vs AI player
       */
      if (playerNum == 1) {
        System.out.println("Create a new room for player and AI player.");
        GameServer gameServer = new GameServer(portNum, 2, gameBoards, disconnectedUsers, disconnectedGames, 1);
        gameServer.addClient(clientSocket);
        gameServer.addUsername(username);
        gameRooms.put(1, gameServer);
        // add AI player and connect to server
        AIPlayer aiPlayer = new AIPlayer("127.0.0.1", 12345, System.out, 1, aiUsername);
        gameServer.addAIPlayer1(aiPlayer);
        aiUsername += "1";
        gameServer.addUsername("aiplayer");
        Thread ai = new Thread(aiPlayer);
        ai.start();

      } 
      /**
       * 2 AI players
       */
      else if (playerNum == 0) {
        System.out.println("Create a new room for 2 AI players.");
        GameServer gameServer = new GameServer(portNum, 2, gameBoards, disconnectedUsers, disconnectedGames, 2);
        gameRooms.put(0, gameServer);
        // add 2 AI players and connect to server
        AIPlayer aiPlayer1 = new AIPlayer("127.0.0.1", 12345, System.out, 2, aiUsername);
        gameServer.addAIPlayer1(aiPlayer1);
        aiUsername += "1";
        gameServer.addUsername("aiplayer1");
        Thread ai1 = new Thread(aiPlayer1);
        ai1.start();
        AIPlayer aiPlayer2 = new AIPlayer("127.0.0.1", 12345, System.out, 2, aiUsername);
        gameServer.addAIPlayer1(aiPlayer2);
        aiUsername += "1";
        gameServer.addUsername("aiplayer2");
        Thread ai2 = new Thread(aiPlayer2);
        ai2.start();

      } 
      /**
       * normal games
       */
      else {
        System.out.println("Get a room for playerNum: " + playerNum);
        GameServer gameServer = gameRooms.get(playerNum);
        if (gameServer.isReadyToStart()) {
          System.out.println("Create a new room for playerNum: " + playerNum);
          gameServer = new GameServer(portNum, playerNum, gameBoards, disconnectedUsers, disconnectedGames, 0);
          gameRooms.put(playerNum, gameServer);
        }
        gameServer.addClient(clientSocket);
        gameServer.addUsername(username);
        // store current gameID and player username
        if (currentGames.containsKey(gameID)) {
          currentGames.get(gameID).add(username);
        } else {
          HashSet<String> users = new HashSet<>();
          users.add(username);
          currentGames.put(gameID, users);
        }
        // ready to start the game
        if (gameServer.isReadyToStart()) {
          // store corresponding gameID
          gameIDs.put(gameServer, gameID);
          gameServer.setGameID(gameID);
          System.out.println("This room is ready to start, playerNum: " + playerNum + " gameID: " + gameID);
          gameID++;
          // detach a new thread for a room
          Thread newGame = new Thread(gameServer);
          newGame.start();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } 
    
  }
}
