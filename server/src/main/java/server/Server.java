package server;
import java.io.*; 

import shared.Player;

import java.net.*;

public class Server {
  private ServerSocket serverSocket ;
  private int port;
  public Server(int portNum) {
    serverSocket = null;
    port = portNum;
  }
  public void buildserver() throws IOException{
    //waits and listen
    serverSocket = new ServerSocket(port);
    int num = 1;
    while (true) {
      String name = null;
      if(num == 1){name = "King";}
      if(num == 2){name = "Red";}
      if(num == 3){name = "Pink";}
      if(num == 4){name = "Blue";}
      if(num == 5){name = "Green";}
      Player ply = new Player(name,num);
      num++;
      Socket clientSocket = null;
      try{
        clientSocket = serverSocket.accept();
        System.out.println("A player is connected");

        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        Thread t = new ClientHandler(ply,clientSocket,input,output);
        t.start();
      }
      catch(Exception e){
        clientSocket.close(); 
        e.printStackTrace(); 
      }
    }  
  }
}





