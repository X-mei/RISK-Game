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
      int split = line.indexOf(' ');
      this.playerName = line.substring(0, split);
      this.playerSeq = Integer.parseInt(line.substring(split + 1));
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
