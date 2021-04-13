package server;

import shared.Board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This is a class which manages all the games.
 */
public class Server {
  private ServerSocket serverSocket;
  private int portNum;
  private HashMap<Integer, GameServer> gameRooms;
  private HashMap<String, String> userLogInfo;
  private HashMap<Integer, HashSet<String>> currentGames;
  private HashMap<GameServer, Integer> gameIDs;
  private HashMap<Integer, Board> gameBoards;
  private HashMap<Integer, HashMap<String, String>> disconnectedUsers;
  private HashMap<Integer, Integer> disconnectedGames;
  
  public Server(int portNum) throws IOException {
    this.portNum = portNum;
    this.serverSocket = new ServerSocket(portNum);
    this.gameRooms = new HashMap<Integer, GameServer>();
    this.userLogInfo = new HashMap<String, String>();
    this.currentGames = new HashMap<Integer, HashSet<String>>();
    this.gameIDs = new HashMap<GameServer, Integer>();
    this.gameBoards = new HashMap<Integer, Board>();
    this.disconnectedUsers = new HashMap<Integer, HashMap<String, String>>();
    this.disconnectedGames = new HashMap<Integer, Integer>();
    // create rooms in advance for 2-5 people
    for (int i = 2; i <= 5; i++) {
      gameRooms.put(i, new GameServer(portNum, i, gameBoards, disconnectedUsers, disconnectedGames, 0));
    }
  }

  /**
   * spawn a thread for every incoming client
   * @throws IOException
   */
  public void assignRoom() throws IOException {
    // accept the client player
    Socket clientSocket = serverSocket.accept();
    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
    String info = input.readUTF();
    boolean aiPlayer;
    if (info.equals("0")) {
      aiPlayer = true;
      System.out.println("One ai player is connected.");
    } else {
      aiPlayer = false;
      System.out.println("One human player is connected.");
    }
    ServerHandler server = new ServerHandler(input, output, clientSocket, portNum, 
                                              gameRooms, userLogInfo, currentGames, 
                                              gameIDs, gameBoards, disconnectedUsers,
                                              disconnectedGames);
    server.start();
  }
}
