package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

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

  public Client(String ip, int port, BufferedReader inputSource, PrintStream out) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.inputReader = inputSource;
    this.out = out;
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
   * This function receives playerName and playerSeq
   */
  public void recvNameAndSeq() throws IOException {
    try {
      String line = dataIn.readUTF(); 
      out.println(line);
      this.playerName = line;
    } catch (IOException e){
      out.println("Receive failed.");
    }
  }

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

  void closeConnection(){
    try{
      this.dataIn.close();
      this.dataOut.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }
  
  

}
