package server;
import java.io.*;
import java.lang.Thread.State;

import shared.ActionFactory;
import shared.Board;
import shared.MapFactory;
import shared.Player;
import shared.UnitsFactory;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
  private ServerSocket serverSocket ;
  private ActionFactory actFactory;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  private int port;
  private int playerNum;
  Board board;
  private ArrayList<String> names;
  private ArrayList<Thread> threadList;
  final Lock lock;
  final Condition isReady;

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
    this.threadList = new ArrayList<Thread>();
    this.lock = new ReentrantLock();
    this.isReady  = lock.newCondition();
  }

  public void buildserver() throws IOException{
    //waits and listen
    serverSocket = new ServerSocket(port);
    int num = 1;
    Board board = new Board(playerNum, mapFac, UnitsFac);  
    while (num <= playerNum) {
      Socket clientSocket = null;
      try{
        clientSocket = serverSocket.accept();
        System.out.println("Player " + num + " is connected");
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        String name = names.get(num-1);
        Thread t = new ClientHandler(clientSocket, input, output, board, name, lock, isReady);
        t.start();
        threadList.add(t);
        num++;
      }
      catch(Exception e){
        clientSocket.close(); 
        e.printStackTrace(); 
      }
    }
    try {
      synchronizeThreads();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void synchronizeThreads() throws InterruptedException {
    while(true) {
      while(!areAllWaiting()) {
        // loop to wait all threads to finish
      }
      // if game over, break
      lock.lock();
      isReady.signalAll();
      lock.unlock();
    }
  }

  public boolean areAllWaiting() {
    for (Thread t: threadList) {
      if (t.getState() != State.WAITING) {
        return false;
      }
    }
    return true;
  }


}





