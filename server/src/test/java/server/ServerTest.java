package server;


import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerTest {
  @Test
  public void test_server() throws IOException, InterruptedException {
    Server server = new Server(12345);
    Thread client1 = new Thread(new FakeClient("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "aa"));
    Thread client2 = new Thread(new FakeClient2("127.0.0.1", 12345, new BufferedReader(new InputStreamReader(System.in)), System.out, "bb"));
    client1.start();
    client2.start();
    
    for(int i = 0; i < 2; i++) {
      server.assignRoom();
    }
    client1.join();
    client2.join();
  }

}











