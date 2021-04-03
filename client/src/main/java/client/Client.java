package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Client class 
 */
public class Client {
  private Socket clientSocket;
  private String serverIPAddr;
  private int portNum;
  private final PrintStream out; // system.println
  private final BufferedReader inputReader; // read input from terminal
  private DataInputStream dataIn; // receive msg
  private DataOutputStream dataOut; // send msg
  private String playerName;
  private int playerSeq;
  private boolean reconnected;
  private int startStatus;

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
  public String recvNameAndSeq() {
    String prompt = null;
    try {
      String line = dataIn.readUTF(); 
      this.playerName = line;
      prompt = dataIn.readUTF();
      out.println(prompt);
    } catch (IOException e){
      out.println("Receive failed.");
    }
    return prompt;
  }

  public void recvStartStatus() {
    try {
      String line = dataIn.readUTF(); 
      this.startStatus = Integer.parseInt(line);
    } catch (IOException e){
      out.println("Receive failed.");
    }
  }

  /**
   * This function receives the prompt about assigning
   * the territory and send the message.
   * @throws IOException
   */
  public void recvAssignTerritory() throws IOException {
    try {
      String promptMsg = dataIn.readUTF(); 
      out.println(promptMsg);
      while(true) {
        String line = dataIn.readUTF(); 
        out.println(line);
        if (line.equals("Wait for other players to assign the units...")) {
          break;
        }
        String readIn = inputReader.readLine();
        dataOut.writeUTF(readIn);
      }
    } catch (IOException e) {
      out.println("Receive failed.");
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
  
  

}
