package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
   * This function receives playerName and playerSeq
   */
  public void recvBoardPromptAndSend() throws IOException {
    try {
      String boardMsg = dataIn.readUTF(); 
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
    } catch (IOException e){
      out.println("Receive failed.");
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
   * This function indicates that the first Player
   * should decide how many people in the game.
   */
  
  

}
