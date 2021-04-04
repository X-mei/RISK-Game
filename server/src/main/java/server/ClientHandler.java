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
import shared.TechAction;
import shared.Territory;
import shared.UnitsFactory;
import shared.UpgradeAction;

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
  final LinkedHashSet<UpgradeAction> upgradeSoldierHashSet;
  private TechAction techUpgrade;
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
    this.upgradeSoldierHashSet = new LinkedHashSet<UpgradeAction>();
    this.techUpgrade = null;
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
    actionSet.add('U');
    actionSet.add('T');
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
    int playerNum = board.getPlayerNum();
    try{
      // send playerInfo
      output.writeUTF(playerInfo);
      output.writeUTF(Integer.toString(playerNum));
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
      while(true) {
        String recv1 = input.readUTF();
        String recv2 = input.readUTF();
        String recv3 = input.readUTF();
        try {
          int unitsNum1 = Integer.parseInt(recv1);
          int unitsNum2 = Integer.parseInt(recv2);
          int unitsNum3 = Integer.parseInt(recv3);
          if (unitsNum1 + unitsNum2 + unitsNum3 > totalUnits) {
            output.writeUTF("input again");
            continue;
          } else {
            unitsAssign[0] = unitsNum1;
            unitsAssign[1] = unitsNum2;
            unitsAssign[2] = unitsNum3;
            break;
          }
        } catch(NumberFormatException e) {
          output.writeUTF("input again");
        }
      }
      int j = 0;
      for(Territory t: player.getTerritoryList()) {
        int [] unitsToAdd = new int[7];
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
      upgradeSoldierHashSet.clear();
      techUpgrade = null;
      String boardMsg = board.displayAllPlayerAllBoard();
      output.writeUTF(boardMsg);
      Boolean valid = true;
      Boolean actionValid = true;
      Boolean techUpgradeMarker = true;
      String received = null;
      String techUpdate = "";
      while(true) {
        String prompt = "";
        if (!valid) {
          prompt += "Invalid input format! Please input this action again.\n";
        }
        else if (!actionValid) {
          prompt += "Invalid action! Please input all actions you want again.\n";
        }
        prompt += techUpdate;
        prompt += "You are the " + playerName + " player, what would you like to do?";
        valid = true;
        actionValid = true;
        techUpdate = "";
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
          Player actionPlayer = board.getPlayerByName(playerName);
          actionPlayer.refreshTempFoodResource();
          actionPlayer.refreshTempTechResource();
          // rule checker of move and attack actions
          if(board.checkIfUpgradeBoolean(upgradeSoldierHashSet) && board.checkIfActionBoolean(moveHashSet, "Move") && board.checkIfActionBoolean(attackHashSet, "Attack")) {
            output.writeUTF("Wait for other players to perform the action...");
            lock.lock();
            isReady.await();
            lock.unlock();
            break;
          } else {
            moveHashSet.clear();
            attackHashSet.clear();
            upgradeSoldierHashSet.clear();
            techUpgrade = null;
            actionValid = false;
            continue;
          }
        }
        else if(chr == 'M' || chr == 'A'){
          output.writeUTF("Please enter the action: src dest count Level");
          String actionInfo = input.readUTF();
          //check the input string
          /*if(!checkActionStr(actionInfo)){
            valid = false;
            continue;
          }*/
          BasicAction act = player.formAction(received, actionInfo);
          if(act.getActionName().equals("M")){
            moveHashSet.add(act);
          }
          else{
            attackHashSet.add(act);
          }  
        }
        else if(chr == 'U'){
          output.writeUTF("Please enter the action: Territory start-level count final-level");
          String actionInfo = input.readUTF();
          //check the input string
          /*if(!checkActionStr(actionInfo)){
            System.out.println("checkActionStr(actionInfo): " + checkActionStr(actionInfo));
            valid = false;
            continue;
          }*/
          UpgradeAction act = player.formUpgradeAction(actionInfo);
          System.out.println(act.getfLevel() + " " + act.getsLevel() + " " + act.getCount());
          upgradeSoldierHashSet.add(act);
        }
        else{
          //output.writeUTF("Please enter the action: Territory start-level count final-level");
          //String actionInfo = input.readUTF();
          //check the input string
          //if(!checkActionStr(actionInfo)){
            //valid = false;
            //continue;
          //}
          if(techUpgradeMarker){
            TechAction act = player.formTechAction();
            techUpgrade = act;
            techUpgradeMarker = false;
            techUpdate = "";
          }
          else{
            techUpdate = "You can only upgrade tech level once in one turn!\n";
            //output.writeUTF("You can only upgrade tech level once in one turn!");
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
      board.processOneTurnUpdateUnits(upgradeSoldierHashSet);
      board.processOneTurnMove(moveHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackPre(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      HashMap<String, HashMap<String, BasicAction>> newAttackMap = board.mergeOneTurnAttackV2(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackNextV2(newAttackMap);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.spawnOneUnitForPlayer(playerName);
      board.spawnResourceForPlayer(playerName);
      board.processUpdateTech(techUpgrade);
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
