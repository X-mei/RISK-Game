package shared;

import java.util.LinkedHashSet;

import com.google.common.base.Function;

import org.checkerframework.checker.units.qual.s;

import java.util.HashMap;
import java.util.*; 

public class Board {
  protected int playerNum;  //number of player
  protected int totalUnitsNum;  //number of units for each player; 
  protected HashMap<String, LinkedHashSet<Territory>> gameBoard;  //the map
  protected MapFactory mapF;  //map factory to create the map
  protected LinkedHashMap<String, Territory> allTerritory;  //a map to store all territories on the map, key is it's name
  protected UnitsFactory UnitsF;  //units factory to create different kinds of units
  protected HashMap<String, Function<Integer, Soldiers>> unitsCreateFunction;   
  private final RuleChecker moveRuleChecker;
  private final RuleChecker attackRuleChecker;
  private HashMap<String, Integer> tempCount;
  private LinkedHashSet<String> UnitName;
  private ArrayList<Player> playerSet;

  /**
   * Constrcut a board
   * @param num number of players
   * @param mapFac  map factory
   * @param UnitsFac  unit factory
   */
  public Board(int num, MapFactory mapFac, UnitsFactory UnitsFac){
    this.playerNum = num;
    this.totalUnitsNum = 20;
    this.mapF = mapFac;
    this.gameBoard = mapF.getMap(num);
    this.UnitsF = UnitsFac;
    this.playerSet = createPlayer(gameBoard, new ActionFactory());
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
    this.tempCount = new HashMap<String, Integer>();
    
  }

/**
 * create the player list, used in board conctructor
 * @param gameBoard
 * @param actF
 * @return
 */
  private ArrayList<Player> createPlayer(HashMap<String, LinkedHashSet<Territory>> gameBoard, ActionFactory actF){
    ArrayList<Player> playerList = new ArrayList<>();
    int i = 1;
    for(String s : gameBoard.keySet()){
      Player p = new Player(s, i, actF);
      p.addTerritory(gameBoard.get(s));
      playerList.add(p);
      i++;
    }
    return playerList;
  }

  public ArrayList<Player> getPlayers() {
    return playerSet;
  }

  public HashMap<String, LinkedHashSet<Territory>> getBoard(){
    return gameBoard;
  }

  public int getPlayerNum(){
    return playerNum;
  }

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

  public void refreshTemp(){
    for(String tN : allTerritory.keySet()){
      Soldiers sol = allTerritory.get(tN).getOneUnits("Basic Soldiers");
      tempCount.put(tN, sol.getCount());
    }
  }

  public Integer getTerritoryUnitsCount(String name){
    return tempCount.get(name);
  }

  public void updateTempCount(String name, Integer cnt){
    tempCount.put(name, tempCount.get(name) - cnt);
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
   * identify the kind of one single attack and process it
   * @param basicAct the attack object
   */
  public void processSingleBasicAction(BasicAction basicAct){
    if(basicAct.getActionName() == "M"){
      processSingleBasicMove(basicAct);
    }
    else if(basicAct.getActionName() == "A"){
      processSingleBasicAttack(basicAct);
    }
  }

  /**
   * process single move action
   * @param basicAct
   */
  public void processSingleBasicMove(BasicAction basicAct){
    String src = basicAct.getSource();
    String dest = basicAct.getDestination();
    Soldiers srcBasicSoldier = getSoldiers("Basic Soldiers", src);
    Soldiers destBasicSoldier = getSoldiers("Basic Soldiers", dest);
    int count = basicAct.getCount();
    srcBasicSoldier.updateCount(srcBasicSoldier.getCount() - count);
    destBasicSoldier.updateCount(destBasicSoldier.getCount() - count);
  }

  /**
   * process single attack action
   * @param basicAct
   */
  public void processSingleBasicAttack(BasicAction basicAct){
    String src = basicAct.getSource();
    String dest = basicAct.getDestination();
    Soldiers srcBasicSoldier = getSoldiers("Basic Soldiers", src);
    Soldiers destBasicSoldier = getSoldiers("Basic Soldiers", dest);;
    int srcBasicSoldierNum = srcBasicSoldier.getCount();
    int destBasicSoldierNum = destBasicSoldier.getCount();
    while(srcBasicSoldierNum > 0 && destBasicSoldierNum > 0){
      int srcRandom = srcBasicSoldier.randomNum();
      int destRandom = destBasicSoldier.randomNum();
      if(srcRandom > destRandom){
        destBasicSoldier.updateCount(-1);
      }
      else if(srcRandom < destRandom){
        srcBasicSoldier.updateCount(-1);
      }
      else{
        continue;
      }
    }
  }

  public Boolean checkIfActionBoolean(HashSet<BasicAction> actions, String type) {
    for (BasicAction action : actions) {
      if (type == "Move") {
        if (moveRuleChecker.checkMyRule(action, this) == null) {
          continue;
        }
        else {
          return false;
        }
      }
      else {
        if (attackRuleChecker.checkMyRule(action, this) == null) {
          continue;
        }
        else {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * get soldier's number on one territory
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
}










