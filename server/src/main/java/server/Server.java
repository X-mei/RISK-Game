package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * This is a class which manages all the games.
 */
public class Server {
  private ServerSocket serverSocket;
  private int portNum;
  private HashMap<Integer, GameServer> gameRooms;
  
  public Server(int portNum) throws IOException {
    this.portNum = portNum;
    this.serverSocket = new ServerSocket(portNum);
    this.gameRooms = new HashMap<Integer, GameServer>();
    // create rooms in advance for 2-5 people
    for (int i = 2; i < 5; i++) {
      gameRooms.put(i, new GameServer(portNum, i));
    }
  }

  public void assignRoom() throws IOException {
    // accept the client player
    Socket clientSocket = serverSocket.accept();
    System.out.println("One player is connected.");
    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
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
      gameServer = new GameServer(portNum, playerNum);
      gameRooms.put(playerNum, gameServer);
    }
    gameServer.addClient(clientSocket);
    if (gameServer.isReadyToStart()) {
      System.out.println("This room is ready to start, playerNum: " + playerNum);
      Thread newGame = new Thread(gameServer);
      newGame.start();
    }
  }
}
