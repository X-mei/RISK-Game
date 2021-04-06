package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServer extends Thread {
  private ServerSocket serverSocket;
  private int portNum;

  public FakeServer(int portNum) throws IOException {
    this.portNum = portNum;
    this.serverSocket = new ServerSocket(portNum);
  }

  public void run() {
    try {
      Socket clientSocket = serverSocket.accept();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
