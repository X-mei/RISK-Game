package server;


import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class ServerTest {

  @Test
  public void test_server() throws IOException, InterruptedException {
    Server server = new Server(12345);
    Thread client1 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "aa", true));
    Thread client2 = new Thread(new FakeClient("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "bb"));
    client1.start();
    server.assignRoom();
    client2.start();
    server.assignRoom();

    Thread.sleep(3000);
    Thread client3 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "aa", false));
    client3.start();
    server.assignRoom();

    Thread client4 = new Thread(new FakeClient3("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "test", true));
    client4.start();
    server.assignRoom();
    server.assignRoom();

    Thread client5 = new Thread(new FakeClient3("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "test1", false));
    client5.start();
    server.assignRoom();
    server.assignRoom();
    server.assignRoom();

    // wait for the game ends
    Thread.sleep(10000);

    client1.join();
    client2.join();
    client3.join();
    client4.join();
    client5.join();
  }


}











