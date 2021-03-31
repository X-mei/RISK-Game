package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import shared.BasicAction;
import shared.Board;
import shared.MapFactory;
import shared.Player;
import shared.Territory;
import shared.UnitsFactory;

/**
 * ClientHandler class extends from thread and
 * handles the game logic.
 */
public class ClientHandler extends Thread {
  final DataInputStream input;
  final DataOutputStream output;
  final Socket socket;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  final LinkedHashSet<BasicAction> moveHashSet;
  final LinkedHashSet<BasicAction> attackHashSet;
  final Board board;
  final String playerName;
  final Player player;
  final HashSet<Character> actionSet;
  final Lock lock;
  final Condition isReady;
  Boolean connectFlag;
  Boolean disconnectFlag;
  int statusFlag;
  private HashMap<Integer, HashMap<String, String>> disconnectedUsers;
  private HashMap<Integer, Integer> disconnectedGames;
  final String username;
  final int gameID;

  public ClientHandler(Socket s, DataInputStream in, DataOutputStream out, 
                        Board b, String name, Lock lock, Condition isReady,
                        HashMap<Integer, HashMap<String, String>> disconnectedUsers,
                        HashMap<Integer, Integer> disconnectedGames,
                        int gameID, String username){
    this.input = in;
    this.output = out;
    this.socket = s;
    this.mapFac = new MapFactory();
    this.UnitsFac = new UnitsFactory();
    this.moveHashSet = new LinkedHashSet<BasicAction>();
    this.attackHashSet = new LinkedHashSet<BasicAction>();
    this.board = b;
    this.playerName = name;
    this.connectFlag = true;
    this.disconnectFlag = false;
    this.statusFlag = 1;
    this.player = getPlayer();
    this.actionSet = new HashSet<Character>();
    actionSet.add('D');
    actionSet.add('M');
    actionSet.add('A');
    this.lock = lock;
    this.isReady = isReady;
    this.disconnectedUsers = disconnectedUsers;
    this.disconnectedGames = disconnectedGames;
    this.gameID = gameID;
    this.username = username;
  }

  @Override
  public void run(){
    String playerInfo = playerName;
    try{
      // send playerInfo
      output.writeUTF(playerInfo);
      output.writeUTF("Hi, You are player " + playerName + ", and this gameID is " + gameID);
      output.writeUTF(Integer.toString(statusFlag));
      
      // ask client to assign territory
      if (statusFlag == 1) {
        assignTerritory();
      }
      statusFlag = 2;
      // while loop, check if game ends
      while(!board.checkSinglePlayerLose(playerName) && board.checkGameEnd().equals("")){
        sendBoardPromptAndRecv();
        updateBoard();
      }
      if (board.checkSinglePlayerLose(playerName) && board.checkGameEnd().equals("")) {
        output.writeUTF("You lost all your territories!");
        output.writeUTF("Do you want to exit or continue watching the game? Input c to continue or else to exit.");
        // TODO : check lowercase or uppercase, both okay
        String isContinue = input.readUTF();
        if (isContinue.equals("c")) {
          // only send board msg
          sendBoardMsg();
          sendGameEndMsg();
        } 
        else {
          connectFlag = false;
        }
      } else {
        connectFlag = false;
        sendGameEndMsg();
      }
      connectFlag = false;
    } catch(IOException e){
      // disconnect
      if (disconnectedUsers.containsKey(gameID)) {
        HashMap<String, String> users = disconnectedUsers.get(gameID);
        users.put(username, playerName);
      } else {
        HashMap<String, String> users = new HashMap<String, String>();
        users.put(username, playerName);
        disconnectedUsers.put(gameID, users);
      }
      this.disconnectFlag = true;
      disconnectedGames.put(gameID, statusFlag);
      System.out.println(username + ": " + playerName + " in gameID: " + gameID + " disconnected. StatusFlag = " + statusFlag);
      //e.printStackTrace();
    } finally {
      //close connection when game over
      closeConnection();
    }
  }

  /**
   * This function gets the player of this current
   * client.
   * @return Player
   */
  public Player getPlayer() {
    ArrayList<Player> playerList = board.getPlayers();
    for (Player p: playerList) {
      if (p.getName().equals(playerName)) {
        return p;
      }
    }
    return null;
  }

  /**
   * This function gets the thread's connect flag.
   * If the player exits the game, return false.
   * @return boolean
   */
  public boolean getConnectFlag() {
    return connectFlag;
  }

  public boolean getDisconnectFlag() {
    return disconnectFlag;
  }

  public void setStatusFlag(int flag) {
    this.statusFlag = flag;
  }

  /**
   * This function tells the client to assign the units
   * to their territories
   * @throws IOException
   */
  public void assignTerritory() throws IOException {
    try {
      int totalUnits = board.getTotalUnits();
      int unitsSetup = 0;
      String[] promptMsg = board.askUnitSetup(playerName);
      int[] unitsAssign = new int[promptMsg.length];
      int i = 0;
      output.writeUTF("You have total " + totalUnits + " to set up in your territories.");
      while(i < promptMsg.length) {
        output.writeUTF(promptMsg[i] + "You have " + (totalUnits - unitsSetup) + " units left.");
        String received = input.readUTF();
        int unitsNum;
        try {
          unitsNum = Integer.parseInt(received);
        } catch(NumberFormatException e) {
          continue;
        }
        if (unitsNum + unitsSetup > totalUnits) {
          continue;
        } else {
          unitsSetup += unitsNum;
          unitsAssign[i] = unitsNum;
          i++;
        }
      }
      int j = 0;
      for(Territory t: player.getTerritoryList()) {
        int[] unitsToAdd = new int[1];
        unitsToAdd[0] = unitsAssign[j];
        board.singleTerritoryUnitSetup(t.getTerritoryName(), unitsToAdd);
        j++;
      }
      output.writeUTF("Wait for other players to assign the units...");
      lock.lock();
      isReady.await();
      lock.unlock();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This function sends the prompt and play one turn
   * @throws IOException
   */
  public void sendBoardPromptAndRecv() throws IOException {
    try {
      moveHashSet.clear();
      attackHashSet.clear();
      String boardMsg = board.displayAllPlayerAllBoard();
      output.writeUTF(boardMsg);
      Boolean valid = true;
      Boolean actionValid = true;
      String received = null;
      while(true) {
        String prompt = "";
        if (!valid) {
          prompt += "Invalid input format! Please input this action again.\n";
        }
        else if (!actionValid) {
          prompt += "Invalid action! Please input all actions you want again.\n";
        }
        prompt += "You are the " + playerName + " player, what would you like to do?\n(M)ove\n(A)ttack\n(D)one";
        valid = true;
        actionValid = true;
        output.writeUTF(prompt);
        received = input.readUTF();
        if(received.length() != 1){
          valid = false;
          continue;
        }
        received = received.toUpperCase();
        char chr =  received.charAt(0);
        if(!actionSet.contains(chr)){
          valid = false;
          continue;
        }
        if(chr == 'D') {
          board.refreshTemp(playerName);
          // rule checker of move and attack actions
          if(board.checkIfActionBoolean(moveHashSet, "Move") && board.checkIfActionBoolean(attackHashSet, "Attack")) {
            output.writeUTF("Wait for other players to perform the action...");
            lock.lock();
            isReady.await();
            lock.unlock();
            break;
          } else {
            moveHashSet.clear();
            attackHashSet.clear();
            actionValid = false;
            continue;
          }
        }
        else{
          output.writeUTF("Please enter the action: src dest count");
          String actionInfo = input.readUTF();
          //check the input string
          if(!checkActionStr(actionInfo)){
            valid = false;
            continue;
          }
          BasicAction act = player.formAction(received, actionInfo);
          if(act.getActionName().equals("M")){
            moveHashSet.add(act);
          }
          else{
            attackHashSet.add(act);
          }  
        }
      }
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This function transmits the two action set to the 
   * board and update the board.
   */
  public void updateBoard() {
    try {
      board.processOneTurnMove(moveHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackPre(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      LinkedHashSet<BasicAction> newAttackSet = board.mergeOneTurnAttack(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackNext(newAttackSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.spawnOneUnitForPlayer(playerName);
      lock.lock();
      isReady.await();
      lock.unlock();
      sleep(100);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * This function only sends the message
   */
  void sendBoardMsg() throws IOException {
    try {
      while (board.checkGameEnd().equals("")) {
        String boardMsg = board.displayAllPlayerAllBoard();
        output.writeUTF(boardMsg);
        for (int i = 0; i < 6; i++) {
          lock.lock();
          isReady.await();
          lock.unlock();
        }
        sleep(100);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function sends the game end message
   */
  void sendGameEndMsg() throws IOException {
    output.writeUTF("The game ends.");
    String winner = board.checkGameEnd();
    output.writeUTF(winner + " wins the game!");
  }

  /**
   * This function close the connection of input
   * and output.
   */
  void closeConnection(){
    try{
      this.input.close();
      this.output.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  /**
   * This function checks if the input string is
   * valid to form an action.
   * @return boolean
   */
  Boolean checkActionStr(String str){
    if(str == null){
      return false;
    }
    int pos1 = str.indexOf(" ");
    if(pos1 == -1){
      return false;
    }
    String substr1 = str.substring(pos1 + 1);
    int pos2 = substr1.indexOf(" ");
    if(pos2 == -1){
      return false;
    }
    String substr2 = substr1.substring(pos2 + 1);
    for(int i = 0; i < substr2.length(); i++){
      if(!Character.isDigit(substr2.charAt(i))){
        return false;
      }
    }
    return true;
  }
}
