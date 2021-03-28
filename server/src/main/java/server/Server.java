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

import static java.lang.Thread.sleep;

/**
 * Build a server class for socket communication
 */
public class Server {
  private ServerSocket serverSocket ;
  private ActionFactory actFactory;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  private int port;
  private int playerNum;
  Board board;
  private ArrayList<String> names;
  private ArrayList<ClientHandler> threadList;
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
    this.threadList = new ArrayList<ClientHandler>();
    this.lock = new ReentrantLock();
    this.isReady  = lock.newCondition();
  }

  /**
   * This function builds the server socket and 
   * waits for the client to connect.
   * Each client will start a new thread.
   * @throws IOException
   */
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
        ClientHandler t = new ClientHandler(clientSocket, input, output, board, name, lock, isReady);
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

  /**
   * This function checks when to signal all the threads
   * and if the game ends, break the while loop
   * @throws InterruptedException
   */
  public void synchronizeThreads() throws InterruptedException {
    while(true) {
      while(!areAllWaiting()) {
        // loop to wait all threads to finish
      }
      sleep(100);
      lock.lock();
      isReady.signalAll();
      lock.unlock();
      // if game over, break
      boolean endFlag = true;
      for (ClientHandler t: threadList) {
        endFlag &= !t.getConnectFlag();
      }
      if (endFlag) {
        break;
      }
    }
  }

  /**
   * This function checks if all the threads are in the WAITING
   * state, if return true, otherwise return false.
   * @return boolean
   */
  public boolean areAllWaiting() {
    for (ClientHandler t: threadList) {
      if (t.getState() != State.WAITING && t.getConnectFlag() == true) {
        return false;
      }
    }
    return true;
  }


}





