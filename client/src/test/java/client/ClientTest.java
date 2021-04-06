package client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ClientTest {
  @Test
  public void test_client() {
    try {
      Thread server = new Thread(new FakeServer(1234));
      server.start();
      Client client = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client.connectToServer();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
