package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  private String playerName;
  private Socket clientSocket;
  private String serverIPAddr;
  private int portNum;
  private final PrintStream out;
  private final BufferedReader inputReader;

  public Client(String ip, int port, String playerName, BufferedReader inputSource, PrintStream out) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.playerName = playerName;
    this.inputReader = inputSource;
    this.out = out;
  }

  /**
   * This function connects to server
   * @throws IOException
   */
  public void connectToServer() throws IOException {
    try {
      this.clientSocket = new Socket(serverIPAddr, portNum);
      out.println("Successfully connected to host " + serverIPAddr + " :" + portNum);
    } catch(UnknownHostException e) {
      out.println("Cannot connect to host " + serverIPAddr + " :" + portNum);
    }
  }
  

}
