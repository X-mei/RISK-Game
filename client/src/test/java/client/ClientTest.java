package client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

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
      client1.recvBoardPrompt();
      client1.recvInstruction();
      client1.sendInstruction("D");
      client2.recvBoardPrompt();
      client2.recvInstruction();
      client2.sendInstruction("D");


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
      client3.recvBoardPrompt();
      client3.recvInstruction();
      client3.sendInstruction("D");
      client4.recvBoardPrompt();
      client4.recvInstruction();
      client4.sendInstruction("D");
      client5.recvBoardPrompt();
      client5.recvInstruction();
      client5.sendInstruction("D");

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
      client6.recvBoardPrompt();
      client6.recvInstruction();
      client6.sendInstruction("D");
      client7.recvBoardPrompt();
      client7.recvInstruction();
      client7.sendInstruction("D");
      client8.recvBoardPrompt();
      client8.recvInstruction();
      client8.sendInstruction("D");
      client9.recvBoardPrompt();
      client9.recvInstruction();
      client9.sendInstruction("D");

      // five players
      // Client clientA = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      // clientA.connectToServer();
      // clientA.loginOrReg("l");
      // assertEquals(true, clientA.login("11", "aa"));
      // Client clientB = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      // clientB.connectToServer();
      // clientB.loginOrReg("l");
      // assertEquals(true, clientB.login("44", "11"));
      // Client clientC = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      // clientC.connectToServer();
      // clientC.loginOrReg("l");
      // assertEquals(true, clientC.login("55", "11"));
      // Client clientD = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      // clientD.connectToServer();
      // clientD.loginOrReg("l");
      // assertEquals(true, clientD.login("66", "11"));
      // Client clientE = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
      // clientE.connectToServer();
      // clientE.loginOrReg("r");
      // assertEquals(true, clientE.login("00", "11"));
      // clientA.answerInfo("c");
      // clientB.answerInfo("c");
      // clientC.answerInfo("c");
      // clientD.answerInfo("c");
      // clientE.answerInfo("c");
      // clientA.sendGameRoom("5");
      // clientB.sendGameRoom("5");
      // clientC.sendGameRoom("5");
      // clientD.sendGameRoom("5");
      // clientE.sendGameRoom("5");
      // client6.recvNameAndNum();
      // client7.recvNameAndNum();
      // client8.recvNameAndNum();
      // client9.recvNameAndNum();
      // client6.recvPrompt();
      // client7.recvPrompt();
      // client8.recvPrompt();
      // client9.recvPrompt();
      // client6.recvStartStatus();
      // client7.recvStartStatus();
      // client8.recvStartStatus();
      // client9.recvStartStatus();
      // client6.recvAssignPrompt();
      // client7.recvAssignPrompt();
      // client8.recvAssignPrompt();
      // client9.recvAssignPrompt();
      // prompts = client6.recvPrompts();
      // prompts2 = client7.recvPrompts();
      // prompts3 = client8.recvPrompts();
      // String[] prompts4 = client9.recvPrompts();
      // String[] assign3 = new String[2];
      // assign3[0] = "4";
      // assign3[1] = "4";
      // client6.sendAssignTerritory(assign3);
      // client7.sendAssignTerritory(assign3);
      // client8.sendAssignTerritory(assign3);
      // client9.sendAssignTerritory(assign3);
  }

  @Test
  public void test_checkActionStr() {
    Client client = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
    String str = null;
    assertEquals(false, client.checkActionStr(str));
    str = "d dd f";
    assertEquals(false, client.checkActionStr(str));
    str = "d  dd d";
    assertEquals(false, client.checkActionStr(str));
    str = "d d d d";
    assertEquals(false, client.checkActionStr(str));
    str = "d d 1 d";
    assertEquals(true, client.checkActionStr(str));
  }

  

}
