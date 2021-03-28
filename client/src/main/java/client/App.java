/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {

    public static void main(String[] args) {
      try {
        Client client = new Client(/*"dku-vcm-1221.vm.duke.edu"*/"10.200.69.73"/*"127.0.0.1"*/, 12345, new BufferedReader(new InputStreamReader(System.in)), System.out);
        client.connectToServer();
        client.recvNameAndSeq();
        client.recvAssignTerritory();
        while(true) {
          if (!client.recvBoardPromptAndSend()) {
            break;
          }
        }
        if (client.exitOrContinue()) {
          client.recvMsg();
        }
        client.closeConnection();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
}
