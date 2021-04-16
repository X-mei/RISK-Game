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
import java.util.LinkedHashSet;

import shared.*;

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
  private final RuleChecker moveRuleChecker;
  private final RuleChecker spyRuleChecker;
  private final RuleChecker teleportAttackRuleChecker;
  private final RuleChecker attackRuleChecker;
  private final SpecialRuleChecker upgradeRuleChecker;
  private final String [] soldierNames = {"Lv1","Lv2","Lv3","Lv4","Tel","Lv5","Lv6","Lv7"};
  private final HashMap<String, Integer> soldierBonusLevelTable;
  private final HashMap<String, Integer> techLevelReqReference;


  public AIPlayer(String ip, int port, PrintStream out, int playerNum, String username) {
    this.serverIPAddr = ip;
    this.portNum = port;
    this.out = out;
    this.playerNumber = playerNum;
    this.username = username;
    this.attackRuleChecker = new NeighborChecker(new UnitMovingChecker(new ResourceChecker(null)));
    this.spyRuleChecker = new NeighborChecker(null);//new SpyUnitMovingChecker(null)));
    this.teleportAttackRuleChecker = new UnitMovingChecker(new ResourceChecker(null));
    this.moveRuleChecker = new ExistanceChecker(new OwnerChecker(new RouteChecker(new UnitMovingChecker(new ResourceChecker(null)))));
    this.upgradeRuleChecker = new UpgradeChecker(null);
    this.soldierBonusLevelTable = new HashMap<>();
    soldierBonusLevelTable.put("Lv1", 1);
    soldierBonusLevelTable.put("Lv2", 2);
    soldierBonusLevelTable.put("Lv3", 4);
    soldierBonusLevelTable.put("Lv4", 6);
    soldierBonusLevelTable.put("Tel", 6);
    soldierBonusLevelTable.put("Lv5", 9);
    soldierBonusLevelTable.put("Lv6", 12);
    soldierBonusLevelTable.put("Lv7", 16);
    this.techLevelReqReference = new HashMap<>();
    techLevelReqReference.put("Lv1", 1);
    techLevelReqReference.put("Lv2", 1);
    techLevelReqReference.put("Lv3", 2);
    techLevelReqReference.put("Lv4", 3);
    techLevelReqReference.put("Tel", 3);
    techLevelReqReference.put("Lv5", 4);
    techLevelReqReference.put("Lv6", 5);
    techLevelReqReference.put("Lv7", 6);
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
    int resourceCost = 50+(p.getTechLevel()-1)*(p.getTechLevel())/2*25;
    p.updateTempTechResource(-resourceCost);
    // The set stores all the name of potential target to attack
    HashSet<String> set = new HashSet<>();
    int lowest_score = Integer.MAX_VALUE;
    int selfScore = 0;
    String potentialTarget = "";
    String potentialSrc = "";
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
            selfScore = t.calculateUnitsPower();
          }
          set.add(t.getTerritoryName());
        }
      }
    }

    // If no lowest score are found, then no adjacent hostile territory to attack
    if (lowest_score == Integer.MAX_VALUE){
      return;
    }
    int scoreGap = lowest_score-selfScore;
    // If there is a deficiency in the score, try to upgrade the unit
    if (scoreGap > 0){
      scoreGap = generateMoveDecisions(actions, potentialSrc, scoreGap);
    }
    // If the score gap still exist, move unit from other territory
    if (scoreGap > 0){
      scoreGap = generateUpgradeDecisions(actions, potentialSrc, scoreGap);
    }
    // If the score gap still exist, unable to attack
    if (scoreGap > 0){
      return;
    }

    int curScore = 0;
    // For each type of soldier in descending bonus
    for (int i=soldierNames.length-1; i>=0; --i){
      int cnt = 0;
      // Add soldier one by one until the total score is higher than the target
      for (; cnt<board.getTerritoryUnitsCount(potentialSrc, soldierNames[i]); ++cnt){
        curScore += soldierBonusLevelTable.get(soldierNames[i]);
        if (curScore >= lowest_score){
          break;
        }
      }
      if (cnt == 0){
        continue;
      }
      // Generate action string for this type of soldier
      String actionStr = "A "+potentialSrc+" "+cnt+" "+potentialTarget+" "+soldierNames[i];
      actions.add(actionStr);
      // If we already have enough score, break
      if (curScore >= lowest_score){
        break;
      }
    }
  }


  /**
   * Generate upgrade action needed to make attack possible
   * @param actions list of actions
   * @param potentialSrc  name of the territory to upgrade
   * @param scoreReq the score gap to close
   * @return the score left to close the gap
   */
  public int generateUpgradeDecisions(ArrayList<String> actions, String potentialSrc, int scoreReq){
    
    Player p = board.getPlayerByName(playerName);
    int techLevel = p.getTechLevel();
    
    // For each soldier in the source territory
    for (int i=0; i<soldierNames.length-1; ++i){
      // If no such type of soldier, continue
      if (board.getTerritoryUnitsCount(potentialSrc, soldierNames[i]).equals(0)){
        continue;
      }
      if (scoreReq <= 0) {
        break;
      }
      // If the current type of soldier have a higher requirement than the techLevel, break
      // Since all other level of soldier have a even higher tech requirement
      if (techLevelReqReference.get(soldierNames[i]) > techLevel){
        break;
      }
      // For all the available soldier type to upgrade to
      for (int j=soldierNames.length-1; j>=0; --j){
        // If that level is not reachable then continue to next level
        if (techLevelReqReference.get(soldierNames[j]) > techLevel){
          continue;
        }
        int cnt = 1;
        // Upgrade this type of soldier to the given level one at a time
        for (; cnt<board.getTerritoryUnitsCount(potentialSrc, soldierNames[i]); ++cnt){
          scoreReq -= (soldierBonusLevelTable.get(soldierNames[j]) - soldierBonusLevelTable.get(soldierNames[i]));
          if (scoreReq <= 0){
            break;
          }
        }
        // Update the temp soldier count, so the attack can access them
        board.updateTempCount(potentialSrc, soldierNames[i], cnt);
        board.updateTempCount(potentialSrc, soldierNames[j], -cnt);
        String actionStr = "U "+potentialSrc+" "+soldierNames[i]+" "+cnt+" "+soldierNames[j];
        UpgradeAction act = new UpgradeAction(playerName, potentialSrc+" "+soldierNames[i]+" "+cnt+" "+soldierNames[j]);
        actions.add(actionStr);
        break;
      }
    }
    return scoreReq;
  }

  public int generateMoveDecisions(ArrayList<String> actions, String potentialSrc, int scoreReq){
    Player p = board.getPlayerByName(playerName);
    Territory srcTerr = board.getAllTerritroy().get(potentialSrc);  //get source territory object
    LinkedHashSet<Territory> ownedTerr = p.getTerritoryList();
    ArrayList<Territory> ownedOtherTerr = new ArrayList<>(); //store all other same owner territories
    
    for(Territory t : ownedTerr){
      if(!t.getTerritoryName().equals(srcTerr.getTerritoryName())){
        ownedOtherTerr.add(t);
      }
    }
    for(Territory t : ownedOtherTerr){  
      //check all soldiers in one territory 
      for(int count = 7; count >= 0; count --){
        String soldierName = soldierNames[count];
        Soldiers singleSoldierObj = t.getOneUnits(soldierName);
        int singleSoldierCount = 0;
        //for each kind of soldier, check how many of them can satisfy the score
        for(int i = 1; i <= singleSoldierObj.getCount(); i ++){
            singleSoldierCount = i;
            scoreReq -= singleSoldierObj.getBonus();
            if(scoreReq < 0){
                break;
            }            
        }
        if(singleSoldierCount == 0){
            continue;
        }
        String actionStr = "M "+ t.getTerritoryName() + " " + srcTerr.getTerritoryName() +" " +  singleSoldierCount +" "+ singleSoldierObj.getName();
        BasicAction act = new Attack(playerName, t.getTerritoryName() + " " + srcTerr.getTerritoryName() +" " +  singleSoldierCount +" "+ singleSoldierObj.getName());
        if (attackRuleChecker.checkAction(act, board) == null) {
          actions.add(actionStr);//add a move action
        }
      }
    }
    return scoreReq;
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
        board.refreshTemp(playerName);
        board.getPlayerByName(playerName).refreshTempFoodResource();
        board.getPlayerByName(playerName).refreshTempTechResource();
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

  
  public void setPlayername(String PName){
    this.playerName = PName;
  }
}
