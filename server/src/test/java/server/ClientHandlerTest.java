package server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Test;

import shared.MapFactory;
import shared.UnitsFactory;
import shared.Board;
import shared.Player;

public class ClientHandlerTest {

  private ClientHandler getTestClientHandler() {
    Socket clientSocket = null;
    DataInputStream input = new DataInputStream(System.in);
    DataOutputStream output = new DataOutputStream(System.out);
    MapFactory mapFac = new MapFactory();
    UnitsFactory UnitsFac = new UnitsFactory();
    Board board = new Board(2, mapFac, UnitsFac);  
    Lock lock = new ReentrantLock();
    Condition isReady  = lock.newCondition();
    HashMap<Integer, HashMap<String, String>> disconnectedUsers = new HashMap<Integer, HashMap<String, String>>();
    HashMap<Integer, Integer> disconnectedGames = new HashMap<Integer, Integer>();
    ClientHandler t = new ClientHandler(clientSocket, input, output, board, "King", 
                                        lock, isReady, disconnectedUsers, disconnectedGames,
                                        1000, "mm");
    return t;
  }

  @Test
  public void test_sendGameEnd() throws IOException {
    ClientHandler t = getTestClientHandler();
    Player p = t.getPlayer();
    assertEquals(p.getName(), "King");
    t.sendGameEndMsg();
  }

  @Test
  public void test_getConnectFlag() {
    ClientHandler t = getTestClientHandler();
    assertEquals(true, t.getConnectFlag());
  }

  @Test
  public void test_closeConnection() {
    ClientHandler t = getTestClientHandler();
    t.closeConnection();
  }
 
  // @Test
  // public void test_checkActionStr() {
  //   ClientHandler t = getTestClientHandler();
  //   String testStr = null;
  //   assertEquals(false, t.checkActionStr(testStr));
  //   testStr = "abcd";
  //   assertEquals(false, t.checkActionStr(testStr));
  //   testStr = "abcd efg";
  //   assertEquals(false, t.checkActionStr(testStr));
  //   testStr = "abcd efg hi";
  //   assertEquals(false, t.checkActionStr(testStr));
  //   testStr = "abcd efg 3";
  //   assertEquals(true, t.checkActionStr(testStr));
  // }

}
