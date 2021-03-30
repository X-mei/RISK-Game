package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import shared.Board;

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
  
  public Server(int portNum) throws IOException {
    this.portNum = portNum;
    this.serverSocket = new ServerSocket(portNum);
    this.gameRooms = new HashMap<Integer, GameServer>();
    this.userLogInfo = new HashMap<String, String>();
    this.currentGames = new HashMap<Integer, HashSet<String>>();
    this.gameIDs = new HashMap<GameServer, Integer>();
    this.gameBoards = new HashMap<Integer, Board>();
    this.disconnectedUsers = new HashMap<Integer, HashMap<String, String>>();
    // create rooms in advance for 2-5 people
    for (int i = 2; i < 5; i++) {
      gameRooms.put(i, new GameServer(portNum, i, gameBoards, disconnectedUsers));
    }
  }

  public void assignRoom() throws IOException {
    // accept the client player
    Socket clientSocket = serverSocket.accept();
    System.out.println("One player is connected.");
    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
    ServerHandler server = new ServerHandler(input, output, clientSocket, portNum, 
                                              gameRooms, userLogInfo, currentGames, 
                                              gameIDs, gameBoards, disconnectedUsers);
    server.start();
  }
}
