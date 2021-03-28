package shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Function; 

public class Board {
  protected int playerNum;  //number of player
  protected int remainedPlayerNum;
  //protected int foodAmount;
  //protected int techPoint;
  //protected int techLevel;
  //protected int tempTechPoint;
  protected int totalUnitsNum;  //number of units for each player; 
  protected HashMap<String, LinkedHashSet<Territory>> gameBoard;  //the map
  protected MapFactory mapF;  //map factory to create the map
  protected LinkedHashMap<String, Territory> allTerritory;  //a map to store all territories on the map, key is it's name
  protected UnitsFactory UnitsF;  //units factory to create different kinds of units
  protected HashMap<String, Function<Integer, Soldiers>> unitsCreateFunction;   
  private final RuleChecker moveRuleChecker;
  private final RuleChecker attackRuleChecker;
  private final SpecialRuleChecker upgradeRuleChecker;
  private HashMap<String, HashMap<String,Integer>> tempCount;
  private LinkedHashSet<String> UnitName;
  private ArrayList<Player> playerList;
  private HashMap<Integer, Integer> techUpgradetable;

  /**
   * Constrcut a board
   * @param num number of players
   * @param mapFac  map factory
   * @param UnitsFac  unit factory
   */
  public Board(int num, MapFactory mapFac, UnitsFactory UnitsFac){
    this.playerNum = num;
    this.remainedPlayerNum = num;
    this.totalUnitsNum = 20;
    //this.foodAmount = 100;
    //this.techPoint = 100;
    //this.techLevel = 1;
    this.mapF = mapFac;
    this.gameBoard = mapF.getMap(num);
    this.UnitsF = UnitsFac;
    createPlayer(gameBoard, new ActionFactory());
    this.unitsCreateFunction = new HashMap<String, Function<Integer, Soldiers>>();
    setUpUnitsCreationMap();
    this.allTerritory = new LinkedHashMap<String, Territory>();
    for(String s : gameBoard.keySet()){
      for(Territory t : gameBoard.get(s)){
        allTerritory.put(t.getTerritoryName(), t);
      }
    }
    this.UnitName = new LinkedHashSet<>();
    UnitName.add("Basic Soldiers");
    this.attackRuleChecker = new OwnerChecker(new NeighborChecker(new UnitMovingChecker(null)));
    this.moveRuleChecker = new OwnerChecker(new RouteChecker(new UnitMovingChecker(null)));
    this.upgradeRuleChecker = new UpgradeChecker(null);
    this.tempCount = new HashMap<String, HashMap<String,Integer>>();
    //this.tempTechPoint = techPoint;
    //create a tech upgrade reference table
    techUpgradetable = new HashMap<>();
    techUpgradetable.put(1, 0);
    techUpgradetable.put(2, 50);
    techUpgradetable.put(3, 75);
    techUpgradetable.put(4, 125);
    techUpgradetable.put(5, 200);
    techUpgradetable.put(6, 300);
  }

/**
 * create the player list, used in board conctructor
 * @param gameBoard
 * @param actF
 * @return
 */
  private void createPlayer(HashMap<String, LinkedHashSet<Territory>> gameBoard, ActionFactory actF){
    this.playerList = new ArrayList<>();
    int i = 1;
    for(String s : gameBoard.keySet()){
      Player p = new Player(s, i, actF);
      p.addTerritory(gameBoard.get(s));
      playerList.add(p);
      i++;
    }
  }

  public ArrayList<Player> getPlayers() {
    return playerList;
  }

  public HashMap<String, LinkedHashSet<Territory>> getBoard(){
    return gameBoard;
  }

  public int getPlayerNum(){
    return playerNum;
  }

  public int getTotalUnits() {
    return totalUnitsNum;
  }
/*
  public int getFoodAmount() {
    return foodAmount;
  }

  public int getTechPoint() {
    return techPoint;
  }
  
  public int getTechLevel() {
    return techLevel;
  }

  public Player getPlayer(String pName) {
    for (Player p : playerList) {
      if (p.getName() == pName) {
        return p;
      }
    }
    return null;
  }
  
  */
  /**
   * Ask one player how they want to place his units in each of his territory
   * For a player with n territories, he will be asked for n times since each question for a territory
   * @param playerName is the plyaer's name
   * @return a string array which contains all the unit setup questions for one player
   */
  public String[] askUnitSetup(String playerName){
    LinkedHashSet<Territory> singlePlayerTerritories = gameBoard.get(playerName);
    int territoryNumForOnePlayer = singlePlayerTerritories.size();
    String[] unitSetupStrings = new String[territoryNumForOnePlayer];
    int i = 0;
    for(Territory t : singlePlayerTerritories){
      unitSetupStrings[i] = "You have " + territoryNumForOnePlayer + " territories, how do you want to place units on " + t.getTerritoryName() + " ?\n";
      i++;
    }
    return unitSetupStrings;
  }


  public void setUpUnitsCreationMap(){
    unitsCreateFunction.put("Basic Soldiers", (count) -> UnitsF.createBasicSoldiers(count));
  }

  public LinkedHashMap<String, Territory> getAllTerritroy(){
    return allTerritory;
  }

  public Territory getTerritory(String name){
    return allTerritory.get(name);
  }

  public void refreshTemp(String Tname){
    for(Territory t : gameBoard.get(Tname)){
      LinkedHashMap<String, Soldiers> m = t.getSoldiers();
      HashMap<String, Integer> temp = new HashMap<String, Integer>();
      for (Map.Entry<String, Soldiers> entry : m.entrySet()) {
        temp.put(entry.getKey(), entry.getValue().getCount());
      }
      tempCount.put(t.getTerritoryName(), temp);
    }
  }
/*
  public void refreshTempTechPoint() {
    tempTechPoint = techPoint;
  }

  public void updateTempTechPoint(Integer pnt) {
    tempTechPoint -= pnt;
  }*/
  
  public Integer getTerritoryUnitsCount(String Tname, String Sname){
    return tempCount.get(Tname).get(Sname);
  }

  public void updateTempCount(String Tname, String Sname, Integer cnt){
    HashMap<String, Integer> temp = tempCount.get(Tname);
    if (temp.get(Sname) == null) {
      temp.put(Sname, -cnt);
    }
    else {
      temp.put(Sname, temp.get(Sname) - cnt);
    }
  }

  /**
   * Set all kinds of units on one territory 
   * @param territoryName is the target territory
   * @param count is a array contains all numbers of each kind of unit
   */
  public void singleTerritoryUnitSetup(String territoryName, int[] count){
    int ind = 0;
    Territory t = allTerritory.get(territoryName);
    for (String s : UnitName){
      Soldiers u = unitsCreateFunction.get(s).apply(count[ind++]);
      t.setUnits(u);
    }
  }

  /**
   * process one turn game
   * @param actionSet the set of all actions(all same kind)
   */
  public synchronized void processOneTurnMove(LinkedHashSet<BasicAction> actionSet){
    for(BasicAction a : actionSet){
      processSingleBasicMove(a);
    }
  }

  public synchronized void processOneTurnAttackPre(LinkedHashSet<BasicAction> actionSet){
    for(BasicAction a : actionSet){
      processSingleBasicAttackPre(a);
    }
  }

  
  public synchronized void processOneTurnAttackNext(LinkedHashSet<BasicAction> actionSet){
    for(BasicAction a : actionSet){
      processSingleBasicAttackNext(a);
    }
  }
  /**
   * merge attack
   * @param actionSet
   * @return
   */
  public synchronized LinkedHashSet<BasicAction> mergeOneTurnAttack(LinkedHashSet<BasicAction> actionSet){
    HashMap<String, BasicAction> tempMap = new HashMap<>();
    LinkedHashSet<BasicAction> newAttackset = new LinkedHashSet<>();
    for(BasicAction b : actionSet){
      String destName = b.getDestination();
      int attackCount = b.getCount();
      if(tempMap.containsKey(destName)){
        BasicAction temp = tempMap.get(destName);
        temp.modifyCount(attackCount);
      }
      else{
        tempMap.put(destName, b);
      }
    }
    for(String s : tempMap.keySet()){
      newAttackset.add(tempMap.get(s));
    }
    return newAttackset;
  }


  /**
   * identify the kind of one single attack and process it
   * @param basicAct the attack object
   */
  // public synchronized void processSingleBasicAction(BasicAction basicAct){
  //   if(basicAct.getActionName().equals("M")){
  //     processSingleBasicMove(basicAct);
  //   }
  //   else if(basicAct.getActionName().equals("A")){
  //     processSingleBasicAttackWhole(basicAct);
  //   }
  // }

  /**
   * process single move action
   * @param basicAct
   */
  public synchronized void processSingleBasicMove(BasicAction basicAct){
    String src = basicAct.getSource();  //get src territory name
    String dest = basicAct.getDestination();  //get dest territory name
    String soldierName = basicAct.getLevelName(); //get moved soldier name
    Soldiers srcSoldier = getSoldiers(soldierName, src);  //get moved soldier object in src
    Soldiers destSoldier = getSoldiers(soldierName, dest);  //get moved soldier object in dest
    int foodConsumed = basicAct.getFoodConsume(); //get move consumed food
    int count = basicAct.getCount();  //get move number
    //update soldier number in src an dest
    srcSoldier.updateCount(srcSoldier.getCount() - count);  
    destSoldier.updateCount(destSoldier.getCount() + count);
    String actionOwner = basicAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    //update player's food, food consumed
    actionPlayer.updateFoodResource(-foodConsumed);
  }

  /**
   * find a player object by the string:name of the player 
   * @param playerName
   * @return the found player object
   * if no found, return null
   */
  public Player getPlayerByName(String playerName){
    Player pFind = null;
    for(Player p : playerList){
      if(p.getName().equals(playerName)){
        pFind = p;
        break;
      }
    }
    return pFind;
  }

   /**
    * upgrade single soldier action
    * @param upAct
    */
  public synchronized void processSingleUpdateUnit(UpgradeAction upAct){
    String src = upAct.getSource();  
    String soldierSLevel = upAct.getsLevel(); 
    String soldierFLevel = upAct.getfLevel(); 
    int updateSoldierNum = upAct.getCount();
    Soldiers sLevelSoldier = getSoldiers(soldierSLevel, src);  
    Soldiers fLevelSoldier = getSoldiers(soldierFLevel, src);
    int sLevelSoldierCost = sLevelSoldier.getCost();
    int fLevelSoldierCost = fLevelSoldier.getCost();
    int totalTechCost = updateSoldierNum * (fLevelSoldierCost - sLevelSoldierCost);
    String actionOwner = upAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    actionPlayer.updateTechResource(-totalTechCost);
  }
/**
 * Upgrade tech level, only once per round
 * Also check if the tech upgrade is valid
 * @param t
 */
  public synchronized void processUpdateTech(TechAction techUpAct){
    String techUpOwner = techUpAct.getActionOwner();  
    Player actionPlayer = getPlayerByName(techUpOwner);
    int currTechLevel = actionPlayer.getTechLevel();
    if(currTechLevel >= 6){
      System.out.println("Reached Highest Tech Level 6, no more upgrade");
      return;
    }
    int techUpgradeCost = techUpgradetable.get(currTechLevel + 1);
    if(techUpgradeCost > actionPlayer.getTechResource()){
      System.out.println("Not enough tech resource to upgrade tech");
      return;
    }
    actionPlayer.updateTechResource(-techUpgradeCost);
    actionPlayer.updateTechLevel();
  }

  /**
   * before the attack, extract specific numbers of soldiers from source territory
   * @param basicAct
   */
  public synchronized void processSingleBasicAttackPre(BasicAction basicAct){
    String src = basicAct.getSource();
    String soldierName = basicAct.getLevelName(); //get moved soldier name
    Soldiers srcSoldier = getSoldiers(soldierName, src);
    int count = basicAct.getCount();
    srcSoldier.updateCount(srcSoldier.getCount() - count);
    String actionOwner = basicAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    //update player's food, food consumed, count * 1
    actionPlayer.updateFoodResource(-count);
  }


  /**
   * process single attack action
   * @param basicAct
   */
  public synchronized void processSingleBasicAttackNext(BasicAction basicAct){
    String src = basicAct.getSource();
    String dest = basicAct.getDestination();
    int count = basicAct.getCount();
    Soldiers attackSoldier = new BasicSoldiers(count);
    Soldiers destSoldier = getSoldiers("Basic Soldiers", dest);
    Soldiers testSoldier = getSoldiers("null Soldiers", dest);  //This line is useless, only for test coverage
    while(attackSoldier.getCount() > 0 && destSoldier.getCount() > 0){
      int srcRandom = attackSoldier.randomNum();
      int destRandom = destSoldier.randomNum();
      //int srcRandom = 2;
      //int destRandom = 1;
      if(srcRandom > destRandom){
        destSoldier.updateCount(destSoldier.getCount() - 1);
      }
      else if(srcRandom < destRandom){
        attackSoldier.updateCount(attackSoldier.getCount() - 1);
      }
      else{
        continue;
      }
    }
    //update onwner if needed 
    if(destSoldier.getCount() == 0){
      Territory tDest = allTerritory.get(dest);
      Territory tSrc = allTerritory.get(src);
      String attackerName = tSrc.getOwner();
      String defenderName = tDest.getOwner();
      tDest.updateOwner(attackerName);
      LinkedHashSet<Territory> attackerTerriSet = gameBoard.get(attackerName);
      LinkedHashSet<Territory> defenderTerriSet = gameBoard.get(defenderName);
      attackerTerriSet.add(tDest);
      defenderTerriSet.remove(tDest);
      destSoldier.updateCount(attackSoldier.getCount());
    }
  }

  /**
   * process one complete attack action
   

  public void processSingleBasicAttackWhole(BasicAction basicAct){
    processSingleBasicAttackPre(basicAct);
    processSingleBasicAttackNext(basicAct);
  }
*/
  public Boolean checkIfActionBoolean(HashSet<BasicAction> actions, String type) {
    for (BasicAction action : actions) {
      String output;
      if (type == "Move") {
        output = moveRuleChecker.checkAction(action, this);
        if (output == null) {
          continue;
        }
        else {
          return false;
        }
      }
      else {
        output = attackRuleChecker.checkAction(action, this);
        if (output == null) {
          continue;
        }
        else {
          return false;
        }
      }
    }
    return true;
  }

  public Boolean checkIfUpgradeBoolean(HashSet<UpgradeAction> actions) {
    String output;
    for (UpgradeAction action : actions) {
      output = upgradeRuleChecker.checkAction(action, this);
      if (output == null) {
        continue;
      }
      else {
        return false;
      }
    }
    return true;
  }
/*
  public Boolean checkIfTechUpdateBoolean(TechAction action) {
    Integer cost = 0;
    if (techLevel == 6) {
      return false;
    }
    int multiplyer = 0;
    for (int i = 0; i<techLevel; ++i){
      multiplyer += i;
    }
    cost = 50 + multiplyer*25;
    if (techPoint < cost) {
      return false;
    }
    else {
      return true;
    }
  }*/
  
  /**
   * get specific kind of soldier's number on one territory
   * @param UnitsName the name of the soldier we want 
   * @param territoryName which territory we want to find
   * @return the soldier object 
   */
  private Soldiers getSoldiers(String UnitsName, String territoryName){
    Territory terr = allTerritory.get(territoryName);
    LinkedHashSet<Soldiers> terrAllUnits = terr.getUnits();
    Soldiers terrBasicSoldier = null;
    for(Soldiers u : terrAllUnits){
      if(u.getName().equals(UnitsName)){
        terrBasicSoldier = u;
        break;
      }
    }
    return terrBasicSoldier;
    
  }

  public String displayAllPlayerAllBoard(){
    String ans = "";
    for(String s : gameBoard.keySet()){
      ans += displaySinlgePlayerBoard(s);
    }
    return ans;
  }


  private String displaySinlgePlayerBoard(String playerName){ 
    LinkedHashSet<Territory> terriSet = gameBoard.get(playerName);
    String s1 = playerName + " player:\n";
    // Eg: ans = "King player:
    //            ------------
    //           "
    String ans = s1 + createDottedLine(s1.length());
    String territoryInfo = "";  
    for(Territory t : terriSet){
      Soldiers tempS = t.getOneUnits("Basic Soldiers");
      int SoldierNum = tempS.getCount();
      //Eg: temp = "10 Basic Soldiers in Numbani (next to:"
      String temp = SoldierNum + " " + tempS.getName() + " in " + t.getTerritoryName() + " (next to:";
      String space = " ";
      String tempNeighbor = ""; 
      for(Territory tNeighbor : t.getNeighbours()){
        tempNeighbor += space + tNeighbor.getTerritoryName();
        space = ", ";
      }
      //Eg tempNeihbor = " Elantris, Midkemia"
      territoryInfo += temp + tempNeighbor + ")\n";
    }
    ans += territoryInfo;

    return ans;
  }

  private String createDottedLine(int length){
    String ans = "";
    for(int i = 0; i < length - 1; i++){
      ans += "-";
    }
    ans += "\n";
    return ans;
  }

  /**
  * check if a specific player lose
  * @return true if lose 
  * 
  */
  public Boolean checkSinglePlayerLose(String playerName){
    if(gameBoard.get(playerName).size() == 0){
      return true;
    }
    return false;
  }

  public String checkGameEnd(){
    int endPlayer = 0;
    String winner = null;
    for(String s : gameBoard.keySet()) {
      if(gameBoard.get(s).size() == 0) {
        endPlayer++;
      } else {
        winner = s;
      }
    }
    if (endPlayer + 1 == playerNum) {
      return winner;
    }
    return "";
  }

  public void spawnOneUnitForPlayer(String name) {
    for (Territory t : gameBoard.get(name)) {
      Soldiers temp = t.getOneUnits("Basic Soldiers");
      temp.updateCount(temp.getCount()+1);
    }
  }
}
