package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import shared.BasicAction;
import shared.Board;
import shared.Player;
import shared.Territory;

/**
 * This class is for AI player to play with human player
 */
public class AIPlayer implements Runnable {
  private Socket aiPlayerSocket;
  private String serverIPAddr;
  private int portNum;
  private final PrintStream out; // system.printnl
  private DataInputStream dataIn; // receive msg
  private DataOutputStream dataOut; // send msg
  private String playerName;
  private int playerNumber;
  private int terriNum;
  private String username;
  private Board board;

  public AIPlayer(String ip, int port, PrintStream out, int playerNum, String username) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.out = out;
    this.playerNumber = playerNum;
    this.username = username;
  }

  /**
   * This function add board to ai player
   */
  public void addBoard(Board b) {
    this.board = b;
  }

  /**
   * This function connects to server
   * @throws IOException
   */
  public void connectToServer() {
    try {
      this.aiPlayerSocket = new Socket(serverIPAddr, portNum);
      this.dataIn = new DataInputStream(aiPlayerSocket.getInputStream());
      this.dataOut = new DataOutputStream(aiPlayerSocket.getOutputStream());
      out.println("AI player successfully connected to host " + serverIPAddr + " :" + portNum);
    } catch(IOException e) {
      out.println("Cannot connect to host " + serverIPAddr + " :" + portNum);
    }
  }

  /**
   * This function returns the aiPlayer socket
   * @return Socket
   */
  public Socket getSocket() {
    return aiPlayerSocket;
  }

  /**
   * This function sends whether the client is AI player or not
   * @param
   */
  public void sendPlayerIdentity() {
    try {
      dataOut.writeUTF("0");
    } catch(IOException e) {
      out.println("send failed");
    }
  }

  /**
   * This function sends the choice of login or register to server
   * @param
   */
  public void loginOrReg() {
    try {
      String line = dataIn.readUTF();
      out.println(line);
      dataOut.writeUTF("r");
    } catch (IOException e) {
      out.println("Receive failed.");
    }
  }

  /**
   * This function sends login info to server
   * @param
   * @param
   * @return true or false to indicate the success of login
   */
  public boolean login() {
    try {
      dataOut.writeUTF(username);
      out.println("username: " + username);
      dataOut.writeUTF("AIPLAYER");
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
   * @param
   */
  public void answerInfo() {
    try {
      String line = dataIn.readUTF(); 
      out.println(line);
      //String readIn = inputReader.readLine();
      if (playerNumber == 1) {
        dataOut.writeUTF("a1");
      } else {
        dataOut.writeUTF("a2");
      }
      out.println("I am an ai player");
    } catch (IOException e) {
      out.println("Receive failed.");
    }
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
    this.terriNum = 3;
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
   * This function decide action choice
   */
  // TODO: implement this function, 
  // return a hashmap, <actionName, actionString> eg: "A", "Dorado Hanamura 3 Lv1"
  // the last one will be "D"
  // One problem, key may not repeat!

  /**
   * This class generate the actions to perform in the string of format
   * @param actions the list of actions to to put into
   */
  public void decideActionChoice(ArrayList<String> actions) {
    generateAttackDecisions(actions);
    actions.add("T");
    actions.add("D");
  }

  /**
   * This class generate the attack action, it will call other method to generate move
   * and upgrade action to make attack possible.
   * @param actions is the list of actions to perform
   */
  public void generateAttackDecisions(ArrayList<String> actions){
    Player p = board.getPlayerByName(playerName);
    // The set stores all the name of potential target to attack
    HashSet<String> set = new HashSet<>();
    int lowest_score = Integer.MAX_VALUE;
    String potentialTarget;
    String potentialSrc;
    // For all the territory owned by the AI player
    for (Territory t: p.getTerritoryList()){
      // For all the neighbor of this territory
      for (Territory neighbor: t.getNeighbours()){
        // If the neighbor is owned by player herself then pass
        if (neighbor.getOwner().equals(t.getOwner())){
          continue;
        }
        // If already calculated the power score then pass
        if (set.contains(t.getTerritoryName())){
          continue;
        }
        else {
          // Calculate the score
          int score = neighbor.calculateUnitsPower();
          // Update the lowest score and potential target
          if (score < lowest_score){
            potentialTarget = neighbor.getTerritoryName();
            potentialSrc = t.getTerritoryName();
            lowest_score = score;
          }
          set.add(t.getTerritoryName());
        }
      }
    }
    if (lowest_score == Integer.MAX_VALUE){
      return;
    }
    else {

    }
  }

  public void generateMoveDecisions(ArrayList<String> actions, int scoreReq){

  }

  public void generateUpgradeDecisions(ArrayList<String> actions, int scoreReq){

  }

  /**
   * This function lets the AI player starts the game
   */
  public void run() {
    try {
      out.println("------------------------------");
      out.println("This is info for ai player");
      connectToServer();
      sendPlayerIdentity();
      loginOrReg();
      login();
      answerInfo();
      String playerInfo = dataIn.readUTF();
      this.playerName = playerInfo;
      String playerNum = dataIn.readUTF();
      String info = dataIn.readUTF();
      out.println(info);
      String status = dataIn.readUTF();
      // game starts
      String promptAssign = recvAssignPrompt();
      String[] prompts = recvPrompts();
      // assign Territory
      String[] inputs = new String[3];
      inputs[0] = "1";
      inputs[1] = "2";
      inputs[2] = "3";
      sendAssignTerritory(inputs);
      // start to play turns 
      while (true) {
        String boardMsg = recvBoardPrompt();
        String promptInfo = recvBoardPrompt();
        // The game ends
        if (boardMsg.equals("You lost all your territories!")) {
          break;
        } 
        if (boardMsg.equals("The game ends.")) {
          break;
        }
        out.println(board.displaySinlgePlayerBoardV3(playerName));
        // send a series of actions
        ArrayList<String> actions = new ArrayList<>();
        decideActionChoice(actions);
        for (String action : actions) {
          // recv "what would you like to do"
          String instructionMsg = recvInstruction();
          // send action choice
          String temp = ""+action.charAt(0);
          dataOut.writeUTF(temp);
          if (!temp.equals("D")) {
            if (temp.equals("T")) {
              continue;
            } else {
              // recv "input str format"
              String prompt = dataIn.readUTF();

              // send action string
              dataOut.writeUTF(temp.substring(2));
            }
          } else {
            // recv "wait for other players to perform action"
            String prompt = dataIn.readUTF();
            break;
          }
        }
      }

      out.println("------------------------------");
    } catch(IOException e) {
      e.printStackTrace();
    }
    
  }
}
