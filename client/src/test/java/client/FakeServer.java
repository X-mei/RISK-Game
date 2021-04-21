package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import shared.Board;

public class FakeServer extends Thread {
  private ServerSocket serverSocket;
  private int portNum;
  private HashMap<Integer, FakeGameServer> gameRooms;
  private HashMap<String, String> userLogInfo;
  private HashMap<Integer, HashSet<String>> currentGames;
  private HashMap<FakeGameServer, Integer> gameIDs;
  private HashMap<Integer, Board> gameBoards;
  private HashMap<Integer, HashMap<String, String>> disconnectedUsers;
  private HashMap<Integer, Integer> disconnectedGames;

  public FakeServer(int portNum) throws IOException {
    this.portNum = portNum;
    this.serverSocket = new ServerSocket(portNum);
    this.gameRooms = new HashMap<Integer, FakeGameServer>();
    this.userLogInfo = new HashMap<String, String>();
    this.currentGames = new HashMap<Integer, HashSet<String>>();
    this.gameIDs = new HashMap<FakeGameServer, Integer>();
    this.gameBoards = new HashMap<Integer, Board>();
    this.disconnectedUsers = new HashMap<Integer, HashMap<String, String>>();
    this.disconnectedGames = new HashMap<Integer, Integer>();
    // create rooms in advance for 2-5 people
    for (int i = 2; i <= 5; i++) {
      gameRooms.put(i, new FakeGameServer(portNum, i, gameBoards, disconnectedUsers, disconnectedGames));
    }
  }

  public void run() {
    try {
      while(true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("One player is connected.");
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
        FakeServerHandler server = new FakeServerHandler(input, output, clientSocket, portNum, 
                                                  gameRooms, userLogInfo, currentGames, 
                                                  gameIDs, gameBoards, disconnectedUsers,
                                                  disconnectedGames);
        server.start();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}




