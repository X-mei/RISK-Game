package client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ClientTest {

  @Test
  public void test_2client() throws IOException {
  
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
      client1.answerInfo("c");
      client2.answerInfo("c");
      client1.sendGameRoom("2");
      client2.sendGameRoom("2");
      client1.recvNameAndNum();
      client2.recvNameAndNum();
      client1.recvPrompt();
      client2.recvPrompt();
      client1.recvStartStatus();
      client2.recvStartStatus();
      client1.recvAssignPrompt();
      client2.recvAssignPrompt();
      String[] prompts = client1.recvPrompts();
      String[] prompts2 = client2.recvPrompts();
      String[] assign1 = new String[3];
      assign1[0] = "3";
      assign1[1] = "3";
      assign1[2] = "3";
      String[] assign2 = new String[3];
      client1.sendAssignTerritory(assign1);
      client2.sendAssignTerritory(assign1);


      // three players
      Client client3 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client3.connectToServer();
      client3.loginOrReg("r");
      assertEquals(true, client3.login("11", "aa"));
      Client client4 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client4.connectToServer();
      client4.loginOrReg("r");
      assertEquals(true, client4.login("22", "11"));
      Client client5 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client5.connectToServer();
      client5.loginOrReg("r");
      assertEquals(true, client5.login("33", "11"));
      client3.answerInfo("c");
      client4.answerInfo("c");
      client5.answerInfo("c");
      client3.sendGameRoom("3");
      client4.sendGameRoom("3");
      client5.sendGameRoom("3");
      client3.recvNameAndNum();
      client4.recvNameAndNum();
      client5.recvNameAndNum();
      client3.recvPrompt();
      client4.recvPrompt();
      client5.recvPrompt();
      client3.recvStartStatus();
      client4.recvStartStatus();
      client5.recvStartStatus();
      client3.recvAssignPrompt();
      client4.recvAssignPrompt();
      client5.recvAssignPrompt();
      prompts = client3.recvPrompts();
      prompts2 = client4.recvPrompts();
      String[] prompts3 = client5.recvPrompts();
      client3.sendAssignTerritory(assign1);
      client4.sendAssignTerritory(assign1);
      client5.sendAssignTerritory(assign1);

      // four players
      Client client6 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client6.connectToServer();
      client6.loginOrReg("l");
      assertEquals(true, client6.login("11", "aa"));
      Client client7 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client7.connectToServer();
      client7.loginOrReg("r");
      assertEquals(true, client7.login("44", "11"));
      Client client8 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client8.connectToServer();
      client8.loginOrReg("r");
      assertEquals(true, client8.login("55", "11"));
      Client client9 = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      client9.connectToServer();
      client9.loginOrReg("r");
      assertEquals(true, client9.login("66", "11"));
      client6.answerInfo("c");
      client7.answerInfo("c");
      client8.answerInfo("c");
      client9.answerInfo("c");
      client6.sendGameRoom("4");
      client7.sendGameRoom("4");
      client8.sendGameRoom("4");
      client9.sendGameRoom("4");
      client6.recvNameAndNum();
      client7.recvNameAndNum();
      client8.recvNameAndNum();
      client9.recvNameAndNum();
      client6.recvPrompt();
      client7.recvPrompt();
      client8.recvPrompt();
      client9.recvPrompt();
      client6.recvStartStatus();
      client7.recvStartStatus();
      client8.recvStartStatus();
      client9.recvStartStatus();
      client6.recvAssignPrompt();
      client7.recvAssignPrompt();
      client8.recvAssignPrompt();
      client9.recvAssignPrompt();
      prompts = client6.recvPrompts();
      prompts2 = client7.recvPrompts();
      prompts3 = client8.recvPrompts();
      String[] prompts4 = client9.recvPrompts();
      String[] assign3 = new String[2];
      assign3[0] = "4";
      assign3[1] = "4";
      client6.sendAssignTerritory(assign3);
      client7.sendAssignTerritory(assign3);
      client8.sendAssignTerritory(assign3);
      client9.sendAssignTerritory(assign3);

      // five players

  }

  

}
