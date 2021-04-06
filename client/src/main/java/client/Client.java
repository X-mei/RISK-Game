package client;

import java.io.*;
import java.net.Socket;

/**
 * Client class 
 */
public class Client {
  private Socket clientSocket;
  private String serverIPAddr;
  private int portNum;
  private final PrintStream out; // system.printnl
  private final BufferedReader inputReader; // read input from terminal
  private DataInputStream dataIn; // receive msg
  private DataOutputStream dataOut; // send msg
  private String playerName;
  private int playerSeq;
  private boolean reconnected;
  private int startStatus;
  private int playerNumber;
  private int terriNum;

  public Client(String ip, int port, BufferedReader inputSource, PrintStream out) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.inputReader = inputSource;
    this.out = out;
    this.reconnected = false;
    this.startStatus = 1;
  }

  public boolean getReconnected() {
    return reconnected;
  }

  public int getStartStatus() {
    return startStatus;
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

  public void loginOrReg(String choice) {
    try {
      String line = dataIn.readUTF();
      out.println(line);
      dataOut.writeUTF(choice);
    } catch (IOException e) {
      out.println("Receive failed.");
    }
  }

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
   * This function receives playerName and playerSeq
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

  public int recvStartStatus() {
    try {
      String line = dataIn.readUTF(); 
      this.startStatus = Integer.parseInt(line);
    } catch (IOException e){
      out.println("Receive failed.");
    }
    return startStatus;
  }

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

  public void sendInstruction(String action) {
    try {
      dataOut.writeUTF(action);
      out.println(action);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function recv the board prompt and send action
   */
  public boolean recvBoardPromptAndSend() throws IOException {
    String boardMsg = dataIn.readUTF();
    if (boardMsg.equals("You lost all your territories!")) {
      out.println(boardMsg);
      return false;
    }
    if (boardMsg.equals("The game ends.")) {
      out.println(boardMsg);
      return false;
    }
    out.println(boardMsg);
    while(true) {
      String line = dataIn.readUTF(); 
      out.println(line);
      if (line.equals("Wait for other players to perform the action...")) {
        break;
      }
      String readIn = inputReader.readLine();
      dataOut.writeUTF(readIn);
    }
    return true;
  }

  /**
   * This checks if the client wants to exit or continue
   * @throws IOException
   */
  public boolean exitOrContinue() throws IOException {
    String prompt = dataIn.readUTF();
    if (!prompt.equals("Do you want to exit or continue watching the game? Input c to continue or else to exit.")) {
      out.println(prompt);
      return false;
    }
    out.println(prompt);
    String readIn = null;
    while(true) {
      readIn = inputReader.readLine();
      if (readIn.equals("c")) {
        dataOut.writeUTF(readIn);
        return true;
      } else {
        dataOut.writeUTF(readIn);
        return false;
      }
    }
  }

  /**
   * This function recv the message
   */
  public void recvMsg() throws IOException {
    try {
      while(true) {
        String msg = dataIn.readUTF();
        out.println(msg);
        if (msg.equals("The game ends.")) {
          msg = dataIn.readUTF();
          out.println(msg);
          break;
        }
      }
    } catch (IOException e) {
      closeConnection();
    } 
  }

  /**
   * This function returns the playerSeq
   * @return playerSeq
   */
  public int getPlayerSeq() {
    return playerSeq;
  }

  /**
   * This function returns the playerName
   * @return playerName
   */
  public String getPlayerName() {
    return playerName;
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
