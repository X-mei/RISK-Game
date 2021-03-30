package server;
import java.io.*;
import java.lang.Thread.State;

import shared.Board;
import shared.MapFactory;
import shared.UnitsFactory;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Build a server class for socket communication
 */
public class GameServer implements Runnable {
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  private int playerNum;
  Board board;
  private ArrayList<String> names;
  private List<ClientHandler> threadList;
  private ArrayList<Socket> clientSocks;
  private ArrayList<String> usernames;
  final Lock lock;
  final Condition isReady;
  private int gameID;
  private HashMap<Integer, Board> gameBoards;
  private HashMap<Integer, HashMap<String, String>> disconnectedUsers;

  public GameServer(int portNum, int playerNum, HashMap<Integer, Board> gameBoards,
                      HashMap<Integer, HashMap<String, String>> disconnectedUsers) {
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
    this.threadList = Collections.synchronizedList(new ArrayList<ClientHandler>());
    this.clientSocks = new ArrayList<Socket>();
    this.usernames = new ArrayList<String>();
    this.lock = new ReentrantLock();
    this.isReady  = lock.newCondition();
    this.gameBoards = gameBoards;
    this.disconnectedUsers = disconnectedUsers;
  }

  public void setGameID(int gameID) {
    this.gameID = gameID;
  }

  /**
   * This function adds client socket into clientSocks list
   */
  public void addClient(Socket clientSock) {
    clientSocks.add(clientSock);
  }

  public void addUsername(String username) {
    this.usernames.add(username);
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
      this.board = board;
      gameBoards.put(gameID, board);
      for (Socket client: clientSocks) {
        DataInputStream input = new DataInputStream(client.getInputStream());
        DataOutputStream output = new DataOutputStream(client.getOutputStream());
        String name = names.get(num - 1);
        String username = usernames.get(num - 1);
        ClientHandler t = new ClientHandler(client, input, output, board, name, 
                                              lock, isReady, disconnectedUsers, gameID, username);
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

  public void reconnectUser(Socket client, String name, String username) {
    try {
      DataInputStream input = new DataInputStream(client.getInputStream());
      DataOutputStream output = new DataOutputStream(client.getOutputStream());
      ClientHandler t = new ClientHandler(client, input, output, board, name, 
                                              lock, isReady, disconnectedUsers, gameID, username);
      t.start();
      threadList.add(t);
    } catch (IOException e) {
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
    Iterator<ClientHandler> iterator = threadList.iterator();
    for (; iterator.hasNext();) {
      ClientHandler t = iterator.next();
      if (t.getDisconnectFlag() == true) {
        iterator.remove();
      }
    }
    // for (ClientHandler t: threadList) {
    //   if (t.getDisconnectFlag() == true) {
    //     threadList.remove(t);
    //   }
    // }
    if (threadList.size() != playerNum) {
      return false;
    }
    for (ClientHandler t: threadList) {
      if (t.getState() != State.WAITING && t.getConnectFlag() == true) {
        return false;
      }
    }
    return true;
  }


}





