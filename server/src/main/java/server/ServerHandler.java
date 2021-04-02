package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import shared.Board;

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

  public void login() {
    try {
      output.writeUTF("Register or Log in ? Input r to register, other to login.");
      String received = input.readUTF();
      if (received.equals("r")) {
        newAccount();
      } else {
        authAccount();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void newAccount() {
    try {
      while(true) {
        output.writeUTF("Please input your username and password.");
        String username = input.readUTF();
        //output.writeUTF("Please input your password.");
        String password = input.readUTF();
        if (userLogInfo.containsKey(username)) {
          continue;
        }
        userLogInfo.put(username, password);
        this.username = username;
        output.writeUTF("Login successfully.");
        break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void authAccount() {
    try {
      while(true) {
        //output.writeUTF("Please input your username.");
        String username = input.readUTF();
        //output.writeUTF("Please input your password.");
        String password = input.readUTF();
        if (userLogInfo.containsKey(username)) {
          if (userLogInfo.get(username).equals(password)) {
            this.username = username;
            output.writeUTF("Login successfully.");
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void askInfo() {
    try {
      output.writeUTF("Do you want to enter a previous game or enter a new game? Input p to select previous, others to create new.");
      String received = input.readUTF();
      if (received.equals("p")) {
        enterRoom();
      } else {
        assignRoom();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void enterRoom() {
    try {
      String name = null;
      int room = 0;
      while(true) {
        output.writeUTF("Please input room id.");
        String roomID = input.readUTF();
        try {
          room = Integer.parseInt(roomID);
        } catch(NumberFormatException e) {
          continue;
        }
        // check the room id
        if (!disconnectedUsers.containsKey(room)) {
          continue;
        } else {
          HashMap<String, String> users = disconnectedUsers.get(room);
          if (!users.containsKey(username)) {
            continue;
          } else {
            name = users.get(username);
          }
        }
        output.writeUTF("Enter successfully.");
        break;
      }
      // TODO: assign old room
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

  public void assignRoom() {
    try {
      int playerNum = 0;
      while (true) {
        // TODO: check the input
        output.writeUTF("Which game room do you want to enter? Input 2-5.");
        String received = input.readUTF();
        System.out.println("receive the data: " + received);
        try {
          playerNum = Integer.parseInt(received);
          if (playerNum < 2 || playerNum > 5) {
            continue;
          }
          output.writeUTF("Wait for other players to join.");
        } catch(NumberFormatException e) {
          continue;
        }
        break;
      }
      System.out.println("Get a room for playerNum: " + playerNum);
      GameServer gameServer = gameRooms.get(playerNum);
      if (gameServer.isReadyToStart()) {
        System.out.println("Create a new room for playerNum: " + playerNum);
        gameServer = new GameServer(portNum, playerNum, gameBoards, disconnectedUsers, disconnectedGames);
        gameRooms.put(playerNum, gameServer);
      }
      gameServer.addClient(clientSocket);
      gameServer.addUsername(username);
      // store current gameid and player username
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
    } catch (IOException e) {
      e.printStackTrace();
    } 
    
  }
}
