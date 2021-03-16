/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
      try {
        int playerNum = 0;
        Scanner scan = new Scanner(System.in);
        while(true) {
          System.out.println("How many players do you want to include?");
          // TODO: check the integer
          try {
            playerNum = Integer.parseInt(scan.nextLine());
          } catch(NumberFormatException e) {
            System.out.println("Please input number.");
            continue;
          }
          if (playerNum >= 2 && playerNum <= 5) {
            break;
          }
          System.out.println("Invalid input. Player number should be >=2 and <= 5.");
        }
        scan.close();
        Server server = new Server(12345, playerNum);
        System.out.println("Wait for players...");
        server.buildserver();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    }
}
