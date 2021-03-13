package server;
import java.io.*;

import shared.ActionFactory;
import shared.Board;
import shared.MapFactory;
import shared.Player;
import shared.UnitsFactory;

import java.net.*;
import java.util.ArrayList;

public class Server {
  private ServerSocket serverSocket ;
  private ActionFactory actFactory;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  private int port;
  private int playerNum;
  Board board;
  private ArrayList<String> names;

  public Server(int portNum, int playerNum) {
    this.serverSocket = null;
    this.port = portNum;
    this.mapFac = new MapFactory();
    this.UnitsFac = new UnitsFactory();
    this.board = null;
    this.playerNum = playerNum;
    this.names = new ArrayList<>();
    names.add("King");
    names.add("Red");
    names.add("Pink");
    names.add("Blue");
    names.add("Green");
  }

  public void buildserver() throws IOException{
    //waits and listen
    serverSocket = new ServerSocket(port);
    int num = 1;
    while (num <= playerNum) {
      Player ply = new Player(names.get(num-1), num, actFactory);
      Socket clientSocket = null;
      try{
        clientSocket = serverSocket.accept();
        System.out.println("A player is connected");
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        Thread t = new ClientHandler(ply,clientSocket,input,output);
        t.start();
        num++;
      }
      catch(Exception e){
        clientSocket.close(); 
        e.printStackTrace(); 
      }
    }  
  }



}





