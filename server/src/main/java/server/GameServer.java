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

/**
 * Build a server class for socket communication
 */
public class GameServer implements Runnable {
  private ServerSocket serverSocket ;
  private ActionFactory actFactory;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  private int port;
  private int playerNum;
  Board board;
  private ArrayList<String> names;
  private ArrayList<ClientHandler> threadList;
  private ArrayList<Socket> clientSocks;
  final Lock lock;
  final Condition isReady;

  public GameServer(int portNum, int playerNum) {
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
    this.clientSocks = new ArrayList<Socket>();
    this.lock = new ReentrantLock();
    this.isReady  = lock.newCondition();
  }

  /**
   * This function adds client socket into clientSocks list
   */
  public void addClient(Socket clientSock) {
    clientSocks.add(clientSock);
  }

  /**
   * This function checks if all the players are entered in
   * this game room and ready to start.
   * @return boolean
   */
  public boolean isReadyToStart() {
    if (playerNum == clientSocks.size()) {
      return true;
    }
    return false;
  }

  /**
   * This function builds the server socket and 
   * waits for the client to connect.
   * Each client will start a new thread.
   * @throws IOException
   */
  public void run() {
    try {
      int num = 1;
      Board board = new Board(playerNum, mapFac, UnitsFac);  
      for (Socket client: clientSocks) {
        DataInputStream input = new DataInputStream(client.getInputStream());
        DataOutputStream output = new DataOutputStream(client.getOutputStream());
        String name = names.get(num-1);
        ClientHandler t = new ClientHandler(client, input, output, board, name, lock, isReady);
        t.start();
        threadList.add(t);
        num++;
      }
      synchronizeThreads();
    } catch (IOException e) {
      e.printStackTrace();
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





