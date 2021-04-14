package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.io.*;


public class FakeClient2 extends Thread {
  private Socket clientSocket;
  private String serverIPAddr;
  private int portNum;
  private final PrintStream out; // system.printnl
  private final BufferedReader inputReader; // read input from terminal
  private DataInputStream dataIn; // receive msg
  private DataOutputStream dataOut; // send msg
  private String playerName;
  private boolean reconnected;
  private int startStatus;
  private int playerNumber;
  private int terriNum;
  private String username;
  private boolean newFlag;

  public FakeClient2(String ip, int port, BufferedReader inputSource, PrintStream out, String username, boolean newFlag) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.inputReader = inputSource;
    this.out = out;
    this.reconnected = false;
    this.startStatus = 1;
    this.username = username;
    this.newFlag = newFlag;
  }

  public void run() {
    if (newFlag) {
      try {
        dataOut.writeUTF("1");
      } catch(IOException e) {
        e.printStackTrace();
      } 
      connectToServer();
      loginOrReg("r");
      login(username, "111");
      answerInfo("c");
      sendGameRoom("2");
      String info = recvNameAndNum();
      String prompt = recvPrompt();
      int status = recvStartStatus();
      prompt = recvAssignPrompt();
      String[] prompts = recvPrompts();
      String[] assign1 = new String[3];
      
      if (playerName.equals("Red")) {
        assign1[0] = "3";
        assign1[1] = "3";
        assign1[2] = "3";
        sendAssignTerritory(assign1);
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("T");
        recvInstruction();
        sendInstruction("U");
        recvInstruction();
        sendInstruction("Ilios Lv1 2 Lv2");
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("A");
        recvInstruction();
        sendInstruction("Volskaya Ilios 1 Lv1");
        recvInstruction();
        sendInstruction("D");
        recvInstruction();
        sendInstruction("A");
        recvInstruction();
        sendInstruction("Volskaya Hanamura 3 Lv1");
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("A");
        recvInstruction();
        sendInstruction("Ilios Hollywood 2 Lv2");
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("M");
        recvInstruction();
        sendInstruction("Junkertown Ilios 3 Lv1");
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("M");
        recvInstruction();
        sendInstruction("Ilios Hollywood 3 Lv1");
        recvInstruction();
        sendInstruction("A");
        recvInstruction();
        sendInstruction("Hollywood Dorado 3 Lv1");
        recvInstruction();
        sendInstruction("D");
      }else {
        assign1[0] = "1";
        assign1[1] = "1";
        assign1[2] = "1";
        sendAssignTerritory(assign1);
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("D");
        recvBoardPrompt();
        recvBoardPrompt();
        recvInstruction();
        sendInstruction("D");
      }
      
    } else {
      try {
        dataOut.writeUTF("1");
      } catch(IOException e) {
        e.printStackTrace();
      } 
      connectToServer();
      loginOrReg("l");
      login(username, "111");
      answerInfo("p");
      sendRoomID("1000");
      recvBoardPrompt();
      recvInstruction();
      sendInstruction("D");
    }
    
  }

    /**
   * This function connects to server
   * @throws IOException
   */
  public void connectToServer() {
    try {
      this.clientSocket = new Socket(serverIPAddr, portNum);
      this.dataIn = new DataInputStream(clientSocket.getInputStream());
      this.dataOut = new DataOutputStream(clientSocket.getOutputStream());
      out.println("Successfully connected to host " + serverIPAddr + " :" + portNum);
      
    } catch(IOException e) {
      out.println("Cannot connect to host " + serverIPAddr + " :" + portNum);
    }
  }

  /**
   * This function sends the choice of login or register to server
   * @param choice
   */
  public void loginOrReg(String choice) {
    try {
      String line = dataIn.readUTF();
      out.println(line);
      dataOut.writeUTF(choice);
    } catch (IOException e) {
      out.println("Receive failed.");
    }
  }

  /**
   * This function sends login info to server
   * @param username
   * @param password
   * @return true or false to indicate the success of login
   */
  public boolean login(String username, String password) {
    try {
      dataOut.writeUTF(username);
      dataOut.writeUTF(password);
      String line = dataIn.readUTF();
      out.println(line);
      if (line.equals("Login successfully.")) {
        return true;
      }
    } catch (IOException e) {
      out.println("Receive failed.");
    }
    return false;
  }

  /**
   * This function gives the choice to server
   * @param choice
   */
  public void answerInfo(String choice) {
    try {
      String line = dataIn.readUTF(); 
      out.println(line);
      //String readIn = inputReader.readLine();
      dataOut.writeUTF(choice);
      if (choice.equals("p")) {
        out.println("choose previous game.");
        //sendRoomID();
      } else {
        out.println("create a new game.");
        //sendGameRoom();
      }
    } catch (IOException e) {
      out.println("Receive failed.");
    }
  }

  /**
   * This function sends roomID to server
   * @param choice
   * @return true or false to indicate the roomID's validity
   */
  public boolean sendRoomID(String choice) {
    try {
      dataOut.writeUTF(choice);
      String line = dataIn.readUTF();
      out.println(line);
      if (line.equals("Enter successfully.")) {
        reconnected = true;
        return true;
      }
    } catch (IOException e) {
      out.println("Receive failed.");
    }
    return false;
  }

  /**
   * This function tells the server which game room
   * @throws IOException
   */  
  public void sendGameRoom(String choice) {
    try {
      String line = dataIn.readUTF();
      out.println(line);
      out.println("my room choice: " + choice);
      dataOut.writeUTF(choice);
      line = dataIn.readUTF();
      out.println(line);
    } catch (IOException e) {
      out.println("Receive failed.");
    }
  }

  /**
   * This function receives playerName and total player Number
   * @return playerNum
   */
  public String recvNameAndNum() {
    String playerNum = null;
    try {
      String line = dataIn.readUTF(); 
      this.playerName = line;
      playerNum = dataIn.readUTF();
      out.println(playerNum);
      this.playerNumber = Integer.parseInt(playerNum);
    } catch (IOException e){
      out.println("Receive failed.");
    }
    return playerNum;
  }

  /**
   * This function receives prompt of user info
   * @return prompt
   */
  public String recvPrompt() {
    String prompt = null;
    try {
      prompt = dataIn.readUTF();
      out.println(prompt);
    } catch (IOException e){
      out.println("Receive failed.");
    }
    return prompt;
  }

  /**
   * This function receives startStatus
   * @return startStatus
   */
  public int recvStartStatus() {
    try {
      String line = dataIn.readUTF(); 
      this.startStatus = Integer.parseInt(line);
    } catch (IOException e){
      out.println("Receive failed.");
    }
    return startStatus;
  }

  /**
   * This function receives assign prompts from server
   * @return String
   */
  public String recvAssignPrompt() {
    String promptMsg = null;
    try {
      promptMsg = dataIn.readUTF();
      out.println(promptMsg);
    } catch (IOException e) {
      out.println("receive failed.");
    }
    return promptMsg;
  }

  /**
   * This function receives assign Territories prompts from server
   * @return String[] array
   */
  public String[] recvPrompts() {
    this.terriNum = 2;
    if (playerNumber == 2 || playerNumber == 3) {
      this.terriNum = 3;
    }
    String[] prompt = new String[terriNum];
    try {
      for (int i = 0; i < terriNum; i++) {
        prompt[i] = dataIn.readUTF();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prompt;
  }

  /**
   * This function receives the prompt about assigning
   * the territory and send the message.
   * @throws IOException
   */
  public boolean sendAssignTerritory(String[] input) {
    try {
      for(int i = 0; i < terriNum; i++) {
        dataOut.writeUTF(input[i]);
      }
      String line = dataIn.readUTF();
      out.println(line);
      if (line.equals("Wait for other players to assign the units...")) {
        return true;
      }
    } catch (IOException e) {
      out.println("Receive failed.");
    }
    return false;
  }

  /**
   * This function receives board's prompt message from server
   * @return String of board's prompt
   */
  public String recvBoardPrompt() {
    String boardMsg = null;
    try {
      boardMsg = dataIn.readUTF();
      out.println(boardMsg);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return boardMsg;
  }

  /**
   * This function receives instruction from server
   * @return String of instruction
   */
  public String recvInstruction() {
    String instructionMsg = null;
    try {
      instructionMsg = dataIn.readUTF();
      out.println(instructionMsg);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return instructionMsg;
  }

  /**
   * This function sends action instruction to server
   * @param action
   */
  public void sendInstruction(String action) {
    try {
      dataOut.writeUTF(action);
      out.println(action);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function closes the connection.
   */
  void closeConnection(){
    try{
      this.dataIn.close();
      this.dataOut.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  /**
   * This function checks if the input string is
   * valid to form an action.
   * @return boolean
   */
  public Boolean checkActionStr(String str){
    if(str == null){
      return false;
    }
    String[] sections = str.split(" ", 0);
    if(sections.length != 4){
      return false;
    }
    for(int i = 0; i < sections.length; i++){
      if(sections[i] == null){
        return false;
      }
    }
    for(int i = 0; i < sections[2].length(); i++){
      if(!Character.isDigit(sections[2].charAt(i))){
        return false;
      }
    }
    return true;
  }

}





