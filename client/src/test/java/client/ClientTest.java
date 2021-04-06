package client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ClientTest {
  @Test
  public void test_client() throws IOException {
  
      Thread server = new Thread(new FakeServer(1234));
      server.start();
      Client client1 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client1.connectToServer();
      client1.loginOrReg("r");
      assertEquals(true, client1.login("aa", "aa"));
      Client client2 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client2.connectToServer();
      client2.loginOrReg("r");
      assertEquals(false, client2.login("aa", "11"));
      client2.loginOrReg("r");
      assertEquals(true, client2.login("bb", "11"));
    
  }

}
