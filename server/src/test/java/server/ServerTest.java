package server;


import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerTest {
  @Test
  public void test_server() throws IOException, InterruptedException {
    Server server = new Server(12345);
    Thread client1 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "aa", true));
    Thread client2 = new Thread(new FakeClient("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "bb"));
    //Thread client3 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "bb", false));
    //Thread client4 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "aa", false));
    client1.start();
    server.assignRoom();
    client2.start();
    server.assignRoom();
    //client3.start();
    //client4.start();

    client1.join();
    client2.join();
    //client3.join();
    //client4.join();

  }

}











