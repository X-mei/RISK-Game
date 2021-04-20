package shared;

import java.util.*;
import java.util.function.Function;

public class Board {
  protected int playerNum; //number of player
  protected int remainedPlayerNum;
  protected int totalUnitsNum; //number of units for each player; 
  protected HashMap<String, LinkedHashSet<Territory>> gameBoard; //the map
  protected MapFactory mapF; //map factory to create the map
  protected LinkedHashMap<String, Territory> allTerritory; //a map to store all territories on the map, key is it's name
  protected UnitsFactory UnitsF; //units factory to create different kinds of units
  protected HashMap<String, Function<Integer, Soldiers>> unitsCreateFunction;
  private final RuleChecker moveRuleChecker;
  private final RuleChecker spyRuleChecker;
  private final RuleChecker teleportAttackRuleChecker;
  private final RuleChecker attackRuleChecker;
  private final SpecialRuleChecker upgradeRuleChecker;
  private final SpecialRuleChecker spyUpgradeChecker;
  private HashMap<String, HashMap<String, Integer>> tempCount;
  private LinkedHashSet<String> UnitName;
  private ArrayList<Player> playerList;
  protected final Random boardRandomGenerator;
 

  //Tech Update reference table
  private static HashMap<Integer, Integer> techUpgradetable;
  //Soldier Level Name-Integer reference table
  private HashMap<String, Integer> soldierBonusLevelTable;
  protected SoldierReferenceTable soldierRefTable;

  /**
   * Constrcut a board
   * @param num number of players
   * @param mapFac  map factory
   * @param UnitsFac  unit factory
   */
  public Board(int num, MapFactory mapFac, UnitsFactory UnitsFac) {
    this.playerNum = num;
    this.remainedPlayerNum = num;
    this.totalUnitsNum = 20;
    this.mapF = mapFac;
    this.gameBoard = mapF.getMap(num);
    this.UnitsF = UnitsFac;
    createPlayer(gameBoard, new ActionFactory());
    this.unitsCreateFunction = new HashMap<String, Function<Integer, Soldiers>>();
    setUpUnitsCreationMap();
    this.allTerritory = new LinkedHashMap<String, Territory>();
    for (String s : gameBoard.keySet()) {
      for (Territory t : gameBoard.get(s)) {
        allTerritory.put(t.getTerritoryName(), t);
      }
    }
    this.UnitName = new LinkedHashSet<>();
    unitNameSetup();
    this.attackRuleChecker = new ExistanceChecker(new OwnerChecker(new AttackSelfChecker(new NeighborChecker(new UnitMovingChecker(new ResourceChecker(null))))));
    this.spyRuleChecker = new ExistanceChecker(new NeighborChecker(new SpyUnitMovingChecker(null)));
    this.teleportAttackRuleChecker = new ExistanceChecker(new OwnerChecker(new AttackSelfChecker(new UnitMovingChecker(new TelUnitMoveChecker(new ResourceChecker(null))))));
    this.moveRuleChecker = new ExistanceChecker(new OwnerChecker(new RouteChecker(new UnitMovingChecker(new ResourceChecker(null)))));
    this.upgradeRuleChecker = new UpgradeChecker(null);
    this.spyUpgradeChecker = new SpyUpgradeChecker(null);
    this.tempCount = new HashMap<String, HashMap<String, Integer>>();
    //create a tech upgrade reference table
    techUpgradetable = new HashMap<>();
    for (int i = 2; i < 7; i++) {
      techUpgradetable.put(i, 50 + (i - 2) * (i - 1) / 2 * 25);
    }
    //Soldier Level Name-Integer reference table
    soldierBonusLevelTable = new HashMap<>();
    soldierBonusLevelTable.put("Lv1", 1);
    soldierBonusLevelTable.put("Lv2", 2);
    soldierBonusLevelTable.put("Lv3", 4);
    soldierBonusLevelTable.put("Lv4", 6);
    soldierBonusLevelTable.put("Lv5", 9);
    soldierBonusLevelTable.put("Lv6", 12);
    soldierBonusLevelTable.put("Lv7", 16);
    soldierBonusLevelTable.put("Tel", 7);


    this.boardRandomGenerator = new Random();
    this.soldierRefTable = new SoldierReferenceTable();
  }

  /**
   * create the player list, used in board conctructor
   * @param gameBoard
   * @param actF
   * @return
   */
  private void createPlayer(HashMap<String, LinkedHashSet<Territory>> gameBoard, ActionFactory actF) {
    this.playerList = new ArrayList<>();
    int i = 1;
    for (String s : gameBoard.keySet()) {
      Player p = new Player(s, i, actF);
      p.addTerritory(gameBoard.get(s));
      playerList.add(p);
      i++;
    }
  }

  /**
   * get the list of all players for one game
   * @return the playerList
   */
  public ArrayList<Player> getPlayers() {
    return playerList;
  }

  /**
   * get the whole game board
   * @return the game board
   */
  public HashMap<String, LinkedHashSet<Territory>> getBoard() {
    return gameBoard;
  }

  /**
   * get the number of the player
   * @return the number of players
   */
  public int getPlayerNum() {
    return playerNum;
  }

  /**
   * get the origin total number for one player
   * @return the number of original total soldier nubmers
   */
  public int getTotalUnits() {
    return totalUnitsNum;
  }

  /**
   * set up all kinds of soldiers
   */
  public void unitNameSetup() {
    UnitName.add("Lv1");
    UnitName.add("Lv2");
    UnitName.add("Lv3");
    UnitName.add("Lv4");
    UnitName.add("Lv5");
    UnitName.add("Lv6");
    UnitName.add("Lv7");
    UnitName.add("Tel");
  }
  
  
  /**
   * Ask one player how they want to place his units in each of his territory
   * For a player with n territories, he will be asked for n times since each question for a territory
   * @param playerName is the plyaer's name
   * @return a string array which contains all the unit setup questions for one player
   */
  public String[] askUnitSetup(String playerName) {
    LinkedHashSet<Territory> singlePlayerTerritories = gameBoard.get(playerName);
    int territoryNumForOnePlayer = singlePlayerTerritories.size();
    String[] unitSetupStrings = new String[territoryNumForOnePlayer];
    int i = 0;
    for (Territory t : singlePlayerTerritories) {
      unitSetupStrings[i] = "You have " + territoryNumForOnePlayer + " territories, how do you want to place units on "
          + t.getTerritoryName() + " ?\n";
      i++;
    }
    return unitSetupStrings;
  }

  /**
   * create each level soldier objects
   */
  public void setUpUnitsCreationMap() {
    unitsCreateFunction.put("Lv1", (count) -> UnitsF.createLevel1Soldiers(count));
    unitsCreateFunction.put("Lv2", (count) -> UnitsF.createLevel2Soldiers(count));
    unitsCreateFunction.put("Lv3", (count) -> UnitsF.createLevel3Soldiers(count));
    unitsCreateFunction.put("Lv4", (count) -> UnitsF.createLevel4Soldiers(count));
    unitsCreateFunction.put("Lv5", (count) -> UnitsF.createLevel5Soldiers(count));
    unitsCreateFunction.put("Lv6", (count) -> UnitsF.createLevel6Soldiers(count));
    unitsCreateFunction.put("Lv7", (count) -> UnitsF.createLevel7Soldiers(count));
    unitsCreateFunction.put("Tel", (count) -> UnitsF.createTeleportSoldiers(count));
  }

  /**
   * get all territories of the game
   * @return
   */
  public LinkedHashMap<String, Territory> getAllTerritroy() {
    return allTerritory;
  }

  /**
   * get a specific territory by the name of it
   * @param name is the territory name
   * @return the territory object
   */
  public Territory getTerritory(String name) {
    return allTerritory.get(name);
  }

  /**
   * refresh the temp count of unit when doing checking if action is valid
   * @param Tname the name of the owner of the territory
   */
  public void refreshTemp(String Tname) {
    for (Territory t : gameBoard.get(Tname)) {
      LinkedHashMap<String, Soldiers> m = t.getSoldiers();
      HashMap<String, Integer> temp = new HashMap<>();
      for (Map.Entry<String, Soldiers> entry : m.entrySet()) {
        temp.put(entry.getKey(), entry.getValue().getCount());
      }
      tempCount.put(t.getTerritoryName(), temp);
    }
  }

  /**
   * get the count of soldier of certain level in a certain territory
   * @param Tname name of the territory
   * @param Sname name of the particular level
   * @return
   */
  public Integer getTerritoryUnitsCount(String Tname, String Sname) {
    if (tempCount.get(Tname) == null) {
      return null;
    }
    return tempCount.get(Tname).get(Sname);
  }

  /**
   * update the temp count of unit when doing checking if action is valid
   * @param Tname name of the territory
   * @param Sname name of the particular level
   * @param cnt addition or subtraction to count
   */
  public void updateTempCount(String Tname, String Sname, Integer cnt) {
    HashMap<String, Integer> temp = tempCount.get(Tname);
    // if (temp == null){
    //   temp.put(Sname, -cnt);
    // }
    temp.put(Sname, temp.get(Sname) - cnt);
  }

  
  /**
   * Get the count of spy on a given territory by the name of the owner of spy
   * @param OName the name of the owner
   * @param TName the name of the territory
   * @return the count of spy by the given name on a given territory
   */
  public int getSpyCountByName(String OName, String TName){
    Territory t = allTerritory.get(TName);
    return t.checkSpyCount(OName);
  }
  
  

  /**
   * Set all kinds of units on one territory 
   * @param territoryName is the target territory
   * @param count is a array contains all numbers of each kind of unit
   */
  public void singleTerritoryUnitSetup(String territoryName, int[] count) {
    int ind = 0;
    Territory t = allTerritory.get(territoryName);
    for (String s : UnitName) {
      Soldiers u = unitsCreateFunction.get(s).apply(count[ind++]);
      t.setUnits(u);
    }
  }

  /**
   * Set all kinds of units on one territory 
   * @param territoryName is the target territory
   * @param count is a array contains all numbers of each kind of unit
   */
  public void singleTerritoryUnitSetup(String territoryName, int count) {
    Territory t = allTerritory.get(territoryName);
    Soldiers u = unitsCreateFunction.get("Lv1").apply(count);
    t.setUnits(u);
  }
  
  /**
   * process one turn move for eacg player
   * @param actionSet the set of all actions(all same kind)
   */
  public synchronized void processOneTurnMove(LinkedHashSet<BasicAction> actionSet) {
    for (BasicAction a : actionSet) {
      processSingleBasicMove(a);
    }
  }

  /**
   * process one turn soldier upgrades
   * @param actionSet the set of all upgrades for one player in one turn
   */
  public synchronized void processOneTurnUpdateUnits(LinkedHashSet<UpgradeAction> actionSet) {
    for (UpgradeAction a : actionSet) {
      processSingleUpdateUnit(a);
    }
  }

  /**
   * extract all soldiers from source territories
   * @param actionSet all actions
   */
  public synchronized void processOneTurnAttackPre(LinkedHashSet<BasicAction> actionSet) {
    for (BasicAction a : actionSet) {
      processSingleBasicAttackPre(a);
    }
  }

  /**
   * merge attack Version2
   * we merge one player's all attack actions in to a hashmap,
   * the key of it is each territory the player want to attack,
   * the value is also a hashmap, and the key of value-hashmap is the soldier name
   * the value of the value-hashmap is the number of that level soldier
   * @param actionSet all actions
   * @return the hashmap of a palyer's action
   */
  public synchronized HashMap<String, HashMap<String, BasicAction>> mergeOneTurnAttackV2(
      LinkedHashSet<BasicAction> actionSet) {
    HashMap<String, HashMap<String, BasicAction>> tempMap = new HashMap<>();
    //LinkedHashSet<BasicAction> newAttackset = new LinkedHashSet<>();
    for (BasicAction b : actionSet) {
      String destName = b.getDestination();
      int attackCount = b.getCount();
      String soliderLevelName = b.getLevelName();
      //int soldierLevelNum = soldierBonusLevelTable.get(soliderLevelName);
      if (tempMap.containsKey(destName)) {
        HashMap<String, BasicAction> tempSoldierSetMap = tempMap.get(destName);
        if (tempSoldierSetMap.containsKey(soliderLevelName)) {
          BasicAction tempAct = tempSoldierSetMap.get(soliderLevelName);
          tempAct.modifyCount(attackCount);
        } else {
          tempSoldierSetMap.put(soliderLevelName, b);
        }
      } else {
        HashMap<String, BasicAction> tempAddMap = new HashMap<>();
        tempAddMap.put(soliderLevelName, b);
        tempMap.put(destName, tempAddMap);
      }
    }
    return tempMap;
  }

  /**
  * Version2 attack phase
  * @param actionMap version 2 merged attack set
  */
  public synchronized void processOneTurnAttackNextV2(HashMap<String, HashMap<String, BasicAction>> actionMap) {
    for (String s : actionMap.keySet()) {
      processOneTerritoryAttackNextV2(s, actionMap.get(s));
    }
  }

  // public synchronized void processOneTurnSpyMove(LinkedHashSet<BasicAction> actionSet){
  //   for (BasicAction a : actionSet) {
  //     processOneSpyMoveAction(a);
  //   }
  // }

  // public synchronized void processOneTurnCloak(LinkedHashSet<CloakAction> actionSet){
  //   for (CloakAction a : actionSet) {
  //     processOneCloakAction(a);
  //   }
  // }


  /**
   * a comparator used for attack actions, make the sort() the sort elements ascending
   */
  Comparator<Integer> comparatorIncrease = new Comparator<Integer>() {
    public int compare(Integer a1, Integer a2) {
      return a1 - a2;
    }
  };

  /**
   * generate a random number, used for attack actions
   * @return a random number
   */
  private Integer boardRandomNum(){
    return boardRandomGenerator.nextInt((20 - 1) + 1) + 1;
  }

/**
 * get a soldier's name by the bonus 
 * @param Bonus the bonus of a particular level
 * @return the nmae of the soldier with equal bonus
 */
private String getSoldierNameByBonus(int Bonus){
  String ans = null;
  for(String s : soldierBonusLevelTable.keySet()){
    if(soldierBonusLevelTable.get(s) == Bonus){
      ans = s;
      break;
    }
  }
  return ans;
}

  /**
   * process the attack action for one territory
   * abide by the attack rules required for version 2
   * @param TerritoryName the target attacked territory
   * @param oneTerritoryAttackMap all the attack soldiers and their names
   */
  public synchronized void processOneTerritoryAttackNextV2(String TerritoryName, HashMap<String, BasicAction> oneTerritoryAttackMap){
  //HashMap<String, BasicAction> outMap = oneTerritoryAttackMap;
  ArrayList<Integer> attackList = new ArrayList<>();
  ArrayList<Integer> defenceList = new ArrayList<>();
  Territory destTerri = allTerritory.get(TerritoryName);
  LinkedHashMap<String, Soldiers> allSoldiersInDestTerri = destTerri.getSoldiers();
  //add defence list
  for(String s : allSoldiersInDestTerri.keySet()){
    Soldiers tempSoldier = allSoldiersInDestTerri.get(s);
    int tempCount = tempSoldier.getCount();
    int tempBonus = tempSoldier.getBonus();
    for(int i = 0; i < tempCount; i ++){
      defenceList.add(tempBonus);
    }
  }
  //add attack list
  for(String s : oneTerritoryAttackMap.keySet()){
    int attackTempBonus = soldierBonusLevelTable.get(s);
    for(int i = 0; i < oneTerritoryAttackMap.get(s).getCount(); i ++){
      attackList.add(attackTempBonus);
    }
  }
  //sorted ascending
  Collections.sort(defenceList, comparatorIncrease);
  Collections.sort(attackList, comparatorIncrease);
  int marker = 1; //1 for attacker->defender, 0 for defender->attacker
  //process attack
  while(defenceList.size() > 0 && attackList.size() > 0){
    if(marker == 1){
      int attack = attackList.get(attackList.size() - 1);
      int defender = defenceList.get(0);
      int attackRandom = boardRandomNum() + attack;
      int defendRandom = boardRandomNum() + defender;
      if(attackRandom > defendRandom){
        defenceList.remove(0);
        String defenderSoldierName = getSoldierNameByBonus(defender);
        // System.out.println(attack);
        // System.out.println("lv4: " + defenderSoldierName);
        Soldiers defenderLoseSoldier = getSoldiersByName(defenderSoldierName, TerritoryName);
        defenderLoseSoldier.updateCount(defenderLoseSoldier.getCount() - 1);
        marker = 0;
      }
      else if(attackRandom < defendRandom){
        attackList.remove(attackList.size() - 1);
        // System.out.println(attack);
        String attackSoldierName = getSoldierNameByBonus(attack);
        // System.out.println("lv4: " + attackSoldierName);
        BasicAction attackLoseAction = oneTerritoryAttackMap.get(attackSoldierName);
        attackLoseAction.modifyCount(-1);
      }
      continue;
    }
    else{ //marker = 0
      int attack = attackList.get(0);
      int defender = defenceList.get(defenceList.size() - 1);
      int attackRandom = boardRandomNum() + attack;
      int defendRandom = boardRandomNum() + defender;
      if(attackRandom > defendRandom){
        defenceList.remove(defenceList.size() - 1);
        String defenderSoldierName = getSoldierNameByBonus(defender);
        // System.out.println(attack);
        // System.out.println("lv4: " + defenderSoldierName);
        //System.out.println("defender :" + defender);
        Soldiers defenderLoseSoldier = getSoldiersByName(defenderSoldierName, TerritoryName);
        defenderLoseSoldier.updateCount(defenderLoseSoldier.getCount() - 1);

      }
      else if(attackRandom < defendRandom){
        attackList.remove(0);
        String attackSoldierName = getSoldierNameByBonus(attack);
        // System.out.println(attack);
        // System.out.println("lv4: " + attackSoldierName);
        BasicAction attackLoseAction = oneTerritoryAttackMap.get(attackSoldierName);
        attackLoseAction.modifyCount(-1);
        marker = 1;
      }
      continue;
    }
  }
  //When defender lose, update territory owner and soldiers 
  if(defenceList.size() == 0){
    String defenderName = destTerri.getOwner();
    String attackerName = null;
    for(String s : oneTerritoryAttackMap.keySet()){
      attackerName = oneTerritoryAttackMap.get(s).getActionOwner();
      break;
    }
    //if(attackerName != null){ //update owner
      destTerri.updateOwner(attackerName);
      LinkedHashSet<Territory> attackerTerriSet = gameBoard.get(attackerName);
      LinkedHashSet<Territory> defenderTerriSet = gameBoard.get(defenderName);
      attackerTerriSet.add(destTerri);
      defenderTerriSet.remove(destTerri);
    //}
    //update soldier
    for(String s : oneTerritoryAttackMap.keySet()){
      BasicAction bTemp = oneTerritoryAttackMap.get(s);
      Soldiers newSoldiers = getSoldiersByName(bTemp.getLevelName(), TerritoryName);
      
      System.out.println(newSoldiers.getName());
      newSoldiers.updateCount(bTemp.getCount());
    }
  }
}

  /**
   * create a new soldier object by its name
   * @param name is the soldier you want to create
   * @return the new soldier object
   */
  public Soldiers createDiffSoldiersByName(String name){
  if(name.equals("Lv1")){
    return new Level1Soldiers(1);
  }
  else if(name.equals("Lv2")){
    return new Level2Soldiers(1);
  }
  else if(name.equals("Lv3")){
    return new Level3Soldiers(1);
  }
  else if(name.equals("Lv4")){
    return new Level4Soldiers(1);
  }
  else if(name.equals("Lv5")){
    return new Level5Soldiers(1);
  }
  else if(name.equals("Lv6")){
    return new Level6Soldiers(1);
  }
  else if(name.equals("Lv7")){
    return new Level7Soldiers(1);
  }
  else{
    return new TeleportSoldiers(1);
  }
}

/**
   * process one spy move action
   * @param spyAct is the spy action
   */
  public synchronized void processOneSpyMoveAction(BasicAction spyAct){
    String src = spyAct.getSource();
    Territory srcT = getTerritory(src);
    String dest = spyAct.getDestination();
    Territory destT = getTerritory(dest);
    String spyActOwner = spyAct.getActionOwner();
    Player spyActPlayer = getPlayerByName(spyActOwner);
    int foodConsume = spyAct.getFoodConsume();
    spyActPlayer.updateFoodResource(-foodConsume);  //update player's food by minus the spy move cost
    srcT.deleteSpy(spyActOwner);  //delete spy on src
    destT.addSpy(spyActOwner);  //add spy on dest
    spyActPlayer.updateSpyLocation(src, dest); //update spy location
  }


  /**
   * process single move action
   * @param basicAct is the moove action
   */
  public synchronized void processSingleBasicMove(BasicAction basicAct) {
    String src = basicAct.getSource(); //get src territory name
    String dest = basicAct.getDestination(); //get dest territory name
    String soldierName = basicAct.getLevelName(); //get moved soldier name
    if(soldierName.equals("Spy")){
      processOneSpyMoveAction(basicAct);
      return;
    }
    Soldiers srcSoldier = getSoldiersByName(soldierName, src); //get moved soldier object in src
    Soldiers destSoldier = getSoldiersByName(soldierName, dest); //get moved soldier object in dest
    if(destSoldier == null){
      destSoldier = createDiffSoldiersByName(soldierName);
      destSoldier.updateCount(0);
      Territory t = allTerritory.get(dest);
      t.setUnits(destSoldier);
    }
    int foodConsumed = basicAct.getFoodConsume(); //get move consumed food
    int count = basicAct.getCount(); //get move number
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
   * @param playerName the name of the player
   * @return the found player object
   * if no found, return null
   */
  public Player getPlayerByName(String playerName) {
    Player pFind = null;
    for (Player p : playerList) {
      if (p.getName().equals(playerName)) {
        pFind = p;
        break;
      }
    }
    return pFind;
  }


  /**
   * if the upgrade action is to create a spy, do this function
   * a spy is updated from a Soldier
   * @param upAct
   */
  public synchronized void processSpyUpdate(UpgradeAction upAct) {
    String actionOwner = upAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    String src = upAct.getSource();
    Territory tSrc = allTerritory.get(src);
    String soldierSLevel = upAct.getsLevel();
    Soldiers sLevelSoldier = getSoldiersByName(soldierSLevel, src);
    int updateSoldierNum = upAct.getCount();
    sLevelSoldier.updateCount(sLevelSoldier.getCount() - updateSoldierNum);
    actionPlayer.addnewSpyLocation(src, updateSoldierNum);  //add the location of the new spy
    tSrc.addSpy(actionOwner); //add spy in the born territory
    actionPlayer.updateTechResource(-20 * updateSoldierNum);
  }

  /**
   * upgrade single soldier action
   * @param upAct sis the upgrade action object
   */
  public synchronized void processSingleUpdateUnit(UpgradeAction upAct) {
    String soldierFLevel = upAct.getfLevel();
    if(soldierFLevel.equals("Spy")){  //if to create a spy
      processSpyUpdate(upAct);
      return;
    }
    String src = upAct.getSource();
    Territory tSrc = allTerritory.get(src);
    String soldierSLevel = upAct.getsLevel();
    int updateSoldierNum = upAct.getCount();
    Soldiers sLevelSoldier = getSoldiersByName(soldierSLevel, src);
    Soldiers fLevelSoldier = getSoldiersByName(soldierFLevel, src);
    if(fLevelSoldier == null){
      fLevelSoldier = createDiffSoldiersByName(soldierFLevel);
      fLevelSoldier.updateCount(0);
      tSrc.setUnits(fLevelSoldier);
    }
    //update number
    sLevelSoldier.updateCount(sLevelSoldier.getCount() - updateSoldierNum);
    fLevelSoldier.updateCount(fLevelSoldier.getCount() + updateSoldierNum);
    //consume tech resource
    String actionOwner = upAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    // if(soldierFLevel.equals("Spy")){    //if the action is to create a spy
    //   actionPlayer.updateSpyLocation(src);  //add the location of the new spy
    //   tSrc.addSpy(actionOwner); //add spy in the born territory
    //   actionPlayer.updateTechResource(-20);
    // }
    // else{ //normal upgrade 
    int sLevelSoldierCost = sLevelSoldier.getCost();
    int fLevelSoldierCost = fLevelSoldier.getCost();
    int totalTechCost = updateSoldierNum * (fLevelSoldierCost - sLevelSoldierCost);
    actionPlayer.updateTechResource(-totalTechCost);
    // }    
  }

  /**
   * Upgrade tech level, only once per round
   * Also check if the tech upgrade is valid
   * @param techUpAct tech action object to process
   */
  public synchronized void processUpdateTech(TechAction techUpAct) {
    if (techUpAct == null){
      return;
    }
    String techUpOwner = techUpAct.getActionOwner();
    Player actionPlayer = getPlayerByName(techUpOwner);
    int currTechLevel = actionPlayer.getTechLevel();
    // if (currTechLevel >= 6) {
    //   System.out.println("Reached Highest Tech Level 6, no more upgrade");
    //   return;
    // }
    int techUpgradeCost = techUpgradetable.get(currTechLevel + 1);
    // if (techUpgradeCost > actionPlayer.getTechResource()) {
    //   System.out.println("Not enough tech resource to upgrade tech");
    //   return;
    // }

    actionPlayer.updateTechResource(-techUpgradeCost);
    actionPlayer.updateTechLevel();
    System.out.println("in tech update");
  }

  public boolean checkUpdateTech(TechAction techUpAct){
    if (techUpAct == null){
      return true;
    }
    String techUpOwner = techUpAct.getActionOwner();
    Player actionPlayer = getPlayerByName(techUpOwner);
    int currTechLevel = actionPlayer.getTechLevel();
    if (currTechLevel >= 6) {
      System.out.println("Reached Highest Tech Level 6, no more upgrade");
      return false;
    }
    int techUpgradeCost = techUpgradetable.get(currTechLevel + 1);
    if (techUpgradeCost > actionPlayer.getTechResource()) {
      System.out.println("Not enough tech resource to upgrade tech");
      return false;
    }
    // actionPlayer.updateTechResource(-techUpgradeCost);
    // actionPlayer.updateTechLevel();
    System.out.println("checking tech update");
    return true;
  }


  /**
   * research cloak, only once per game
   * should at least Level 3 to do it
   * @param researchAct research cloak action object to process
   */
  public synchronized void processResearchCloak(ResearchCloak researchAct) {
    if (researchAct == null){
      return;
    }
    String techUpOwner = researchAct.getActionOwner();
    Player actionPlayer = getPlayerByName(techUpOwner);
    //int currTechLevel = actionPlayer.getTechLevel();
    // if (currTechLevel < 3) {
    //   System.out.println("You should reach at least tech level 3 to research cloak");
    //   return;
    // }
    // if (actionPlayer.getTechResource() < 100) {
    //   System.out.println("Not enough tech resource to research cloak");
    //   return;
    // }
    actionPlayer.updateTechResource(-100);
    actionPlayer.changeCloakState(true);
    //System.out.println("in tech update");
  }


  public synchronized boolean checkResearchCloak(ResearchCloak researchAct) {
    if (researchAct == null){
      return true;
    }
    String techUpOwner = researchAct.getActionOwner();
    Player actionPlayer = getPlayerByName(techUpOwner);
    int currTechLevel = actionPlayer.getTechLevel();
    if (currTechLevel < 3) {
      System.out.println("You should reach at least tech level 3 to research cloak");
      return false;
    }
    if (actionPlayer.getTechResource() < 100) {
      System.out.println("Not enough tech resource to research cloak");
      return false;
    }
    return true;
    // actionPlayer.updateTechResource(-100);
    // actionPlayer.changeCloakState(true);
    //System.out.println("in tech update");
  }


  /**
   * process one cloak action for one player
   * @param cloakAcion
   */
  public synchronized void processOneCloakAction(CloakAction cloakAcion){
    if (cloakAcion == null){
      return;
    }
    String cloakActOwner = cloakAcion.getActionOwner();
    Player cloakActPlayer = getPlayerByName(cloakActOwner);
    // if(!cloakActPlayer.checkPlayerCanCloak()){
    //   System.out.println("You cannot cloak yet, research it first!");
    //   return;
    // }
    // if(cloakActPlayer.getTechResource() < 20){
    //   System.out.println("Not enough tech resource to cloak a territory");
    //   return;
    // }
    String src = cloakAcion.getSource();
    Territory srcT = getTerritory(src);
    srcT.updateCloakStatus(3); //update target terrotory cloak state
    cloakActPlayer.updateTechResource(-20);  //update tech source
  }


  public synchronized boolean checkOneCloakAction(CloakAction cloakAcion){
    if (cloakAcion == null){
      return true;
    }
    String cloakActOwner = cloakAcion.getActionOwner();
    Player cloakActPlayer = getPlayerByName(cloakActOwner);
    if(!cloakActPlayer.checkPlayerCanCloak()){
      System.out.println("You cannot cloak yet, research it first!");
      return false;
    }
    if(cloakActPlayer.getTechResource() < 20){
      System.out.println("Not enough tech resource to cloak a territory");
      return false;
    }
    //String src = cloakAcion.getSource();
    //Territory srcT = getTerritory(src);
    // srcT.setCloakStatus(true); //update target terrotory cloak state
    // cloakActPlayer.updateTechResource(-20);  //update tech source
    return true;
  }



  /**
   * before the attack, extract specific numbers of soldiers from source territory
   * @param basicAct basic action object to process
   */
  public synchronized void processSingleBasicAttackPre(BasicAction basicAct) {
    String src = basicAct.getSource();
    String soldierName = basicAct.getLevelName(); //get moved soldier name
    Soldiers srcSoldier = getSoldiersByName(soldierName, src);
    int count = basicAct.getCount();
    srcSoldier.updateCount(srcSoldier.getCount() - count);
    String actionOwner = basicAct.getActionOwner();
    Player actionPlayer = getPlayerByName(actionOwner);
    //update player's food, food consumed, count * 1
    actionPlayer.updateFoodResource(-count);
  }


  public Boolean checkIfActionBoolean(LinkedHashSet<BasicAction> actions) {
    for (BasicAction action : actions) {
      String output;
      if (action.getActionName().equals("M")) {
        if (action.getLevelName().equals("Spy")){
          output = spyRuleChecker.checkAction(action, this);
        }
        else {
          output = moveRuleChecker.checkAction(action, this);
        }
        if (output == null) {
          continue;
        } else {
          return false;
        }
      } else if (action.getActionName().equals("A")) {
        if (action.getLevelName().equals("Tel")){
          output = teleportAttackRuleChecker.checkAction(action, this);
        }
        else {
          output = attackRuleChecker.checkAction(action, this);
        }
        if (output == null) {
          continue;
        } else {
          return false;
        }
      }

    }
    return true;
  }

  public Boolean checkIfUpgradeBoolean(LinkedHashSet<UpgradeAction> actions) {
    String output;
    for (UpgradeAction action : actions) {
      if (action.getfLevel().equals("Spy")){
        output = spyUpgradeChecker.checkAction(action, this);
      }
      else {
        output = upgradeRuleChecker.checkAction(action, this);
      }
      if (output == null) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * get specific kind of soldier's number on one territory
   * @param UnitsName the name of the soldier we want 
   * @param territoryName which territory we want to find
   * @return the soldier object 
   */
  private Soldiers getSoldiersByName(String UnitsName, String territoryName) {
    Territory terr = allTerritory.get(territoryName);
    LinkedHashSet<Soldiers> terrAllUnits = terr.getUnits();
    Soldiers terrBasicSoldier = null;
    for (Soldiers u : terrAllUnits) {
      if (u.getName().equals(UnitsName)) {
        terrBasicSoldier = u;
        break;
      }
    }
    return terrBasicSoldier;

  }

  /**
   * display all text describing all information for a game
   * combined with all player's own information
   * @return the string, which can be seen for all player
   */
  public String displayAllPlayerAllBoard() {
    String ans = "";
    for (String s : gameBoard.keySet()) {
      ans += displaySinlgePlayerBoard(s);
    }
    return ans;
  }

  /**
   * display the information for one single player
   * @param playerName name of the player
   * @return information for that single player
   */
  private String displaySinlgePlayerBoard(String playerName) {
    Player p = getPlayerByName(playerName);
    int pFoodResource = p.getFoodResource();
    int pTechResource = p.getTechResource();
    int pTechLevel = p.getTechLevel();
    LinkedHashSet<Territory> terriSet = gameBoard.get(playerName);
    String s1 = playerName + " player:\n";
    String s2 = "Tech Level: " + pTechLevel + ", Food: " + pFoodResource + ", Tech: " + pTechResource + "\n";
    // Eg: ans = "King player:
    //            ------------
    //           "
    String ans = s1 + createDottedLine(s1.length()) + s2 + "\n";
    String territoryInfo = "";  
    for(Territory t : terriSet){
      String soldierNameString = "";
      //Eg: temp = "10 Lv1 in Numbani (next to:"
      String comma = "";
      for(String s : t.getSoldiers().keySet()){
        Soldiers tempS = t.getSoldiers().get(s);
        int SoldierNum = tempS.getCount();
        if(SoldierNum == 0){
          continue;
        }
        soldierNameString += comma + SoldierNum + " " + tempS.getName();
        comma = ", ";
      }
      String temp = soldierNameString +  " Soldiers in " + t.getTerritoryName() + " (next to:";
      String space = " ";
      String tempNeighbor = "";
      for (Territory tNeighbor : t.getNeighbours()) {
        tempNeighbor += space + tNeighbor.getTerritoryName();
        space = ", ";
      }
      //Eg tempNeihbor = " Elantris, Midkemia"
      territoryInfo += temp + tempNeighbor + ")\n";
    }
    ans += territoryInfo;

    return ans;
  }

  /**
   * create a dot line,
   * the line of it is equal to the player's name
   * @param length specify the length of dotted line
   * @return the dot line string
   */
  private String createDottedLine(int length) {
    String ans = "";
    for (int i = 0; i < length - 1; i++) {
      ans += "-";
    }
    ans += "\n";
    return ans;
  }

  public String infoToFormMap(String playerName) {
    HashMap<String, String> thisRoundCanSee = new HashMap<>();  //Owned territories visible for this round 
    //HashMap<String, String> thisRoundCanSeeEnemy = new HashMap<>();  //Enemy's territories visible for this round 
    Player p = getPlayerByName(playerName);
    LinkedHashSet<Territory> terriSet = gameBoard.get(playerName);
    String s1 = playerName + "\n";
    String territoryInfo = ""; 
    HashSet<Territory> neighborSet = new HashSet<>(); 
    for(Territory t : terriSet){
      for(Territory temp : t.getNeighbours()){
        if(temp.checkIsCloaked() <= 0 && !p.getTerritoryList().contains(temp)){
          neighborSet.add(temp);
        }
      }
      territoryInfo += displaySingleTerriInfoWithNameFirst(t) + "\n";
      p.addPreviousRoundCanSee(t.getTerritoryName(), t.getOwner() + ":" + displaySingleTerriInfoWithNameFirst(t));  //update lastRoundCanSee own territroy
      thisRoundCanSee.put(t.getTerritoryName(), t.getOwner() + ":" + displaySingleTerriInfoWithNameFirst(t));
    }
    
    s1 += territoryInfo + "\nenemy can see:\n";
    HashMap<String, Integer> spyLocation = p.getSpyLocation();
    for(String s : spyLocation.keySet()){
      if(spyLocation.get(s) > 0 && !p.getTerritoryList().contains(allTerritory.get(s))){
        Territory spyAt = allTerritory.get(s);
        neighborSet.add(spyAt);
      }
    }
    for(Territory t : neighborSet){
      //System.out.println(t.getTerritoryName()+"haha");
      s1 += t.getOwner() + ":" + displaySingleTerriInfoWithNameFirst(t) + "\n";
      p.addPreviousRoundCanSee(t.getTerritoryName(), t.getOwner() + ":" + displaySingleTerriInfoWithNameFirst(t));  //update lstRoundCanSee enemy territroy
      thisRoundCanSee.put(t.getTerritoryName(), t.getOwner() + ":" + displaySingleTerriInfoWithNameFirst(t));
    }

    //display previous territories info
    s1 += "\nprevious can-see territory info:\n";
    //if an owned territory is in lastRoundCanSee(own) but not in thisRoundCanSee(all), display its previous info
    for(String TerrName : p.getPreviousRoundCanSee().keySet()){
      if(!thisRoundCanSee.keySet().contains(TerrName)){ 
        s1 += p.getPreviousRoundCanSee().get(TerrName) + "\n";
      }
    }


    // s1 += "\nPrevious info test:\n";
    // for(String TerrName : p.getPreviousRoundCanSee().keySet()){
    //     s1 += p.getPreviousRoundCanSee().get(TerrName) + "\n";
    // }

    return s1;
  }


  public String displaySinlgePlayerBoardV3(String playerName) {
    Player p = getPlayerByName(playerName);
    int pFoodResource = p.getFoodResource();
    int pTechResource = p.getTechResource();
    int pTechLevel = p.getTechLevel();
    LinkedHashSet<Territory> terriSet = gameBoard.get(playerName);
    String s1 = "You are " + playerName + " player\n";
    String s2 = "Tech Level: " + pTechLevel + "\nFood: " + pFoodResource + "\nTech: " + pTechResource + "\n";
    // Eg: ans = "King player:
    //            ------------
    //           "
    //String ans = s1 + createDottedLine(s1.length()) + s2 + "\n";
    String ans = s1 + s2 + "\n";
    String territoryInfo = "";  
    for(Territory t : terriSet){
      territoryInfo += displaySingleTerriInfo(t) + "\n";
    }
    ans += territoryInfo + "\n";
    //add spy information
    ans += spyInfoDisplay(p) + "\n";
    //add adjacent enemy territories info
    ans += adjacentEnemyTerrInfo(p) + "\n";
    ans += cloakInfoDisplay(p);
    return ans;
  }

  public String displaySingleTerriInfo(Territory t){
    String soldierNameString = "";
      //Eg: temp = "10 Lv1 in Numbani (next to:"
      String comma = "";
      for(String s : t.getSoldiers().keySet()){
        Soldiers tempS = t.getSoldiers().get(s);
        int SoldierNum = tempS.getCount();
        if(SoldierNum == 0){
          continue;
        }
        soldierNameString += comma + SoldierNum + " " + tempS.getName();
        comma = ", ";
      }
      String temp = soldierNameString +  " Soldiers in " + t.getTerritoryName() + " (next to:";
      String space = " ";
      String tempNeighbor = "";
      for (Territory tNeighbor : t.getNeighbours()) {
        tempNeighbor += space + tNeighbor.getTerritoryName();
        space = ", ";
      }
      //Eg tempNeihbor = " Elantris, Midkemia" 
      String out = temp + tempNeighbor + ")";
      return out;
  }

  public String displaySingleTerriInfoWithNameFirst(Territory t){
    String soldierNameString = "";
      String comma = "";
      for(String s : t.getSoldiers().keySet()){
        Soldiers tempS = t.getSoldiers().get(s);
        int SoldierNum = tempS.getCount();
        if(SoldierNum == 0){
          continue;
        }
        soldierNameString += comma + SoldierNum + " " + tempS.getName();
        comma = ",";
      }
      String temp = t.getTerritoryName() + ":" + soldierNameString;
      return temp;
  }



  /**
   * display location of one player's spy 
   * @param p is the player
   * @return the string information
   */
  public String spyInfoDisplay(Player p){
    boolean hasSpy = false;
    HashMap<String, Integer> spyLocation = p.getSpyLocation();  
    String has = "Your spies are at:\n";
    String temp1 = "";
    //String temp2 = "";  
    for(String s : spyLocation.keySet()){
      if(spyLocation.get(s) > 0){
        hasSpy = true;
        if(!p.getTerritoryList().contains(allTerritory.get(s))){      
          Territory sT = allTerritory.get(s);
          temp1 +=  s + "(" + spyLocation.get(s) + "): " + displaySingleTerriInfo(sT) + "(owned by " + sT.getOwner() + ")"+ "\n";
        //temp2 = ", ";
        }
        else{
          temp1 += s + "(" + spyLocation.get(s) + "): Your own\n";
        }
      }
      else{
        continue;
      }
    }
    if(hasSpy == false){
      return "You have no Spy, try to create one!\n";
    }
    else{
      return has + temp1;
    }
  }

  public String cloakInfoDisplay(Player p){
    if(!p.checkPlayerCanCloak()){
      return "You cannot cloak, research it first\n";
    }
    String out = "cloak Info:\n";
    for(Territory t : p.getTerritoryList()){
      out += t.getTerritoryName() + ": cloak number is " + t.checkIsCloaked() + "\n";
    }
    return out;
  }

  /**
   * display adjacnet enemy's territory info
   * @param p is the player
   * @return enemy territory information
   */
  public String adjacentEnemyTerrInfo(Player p){
    String playerName= p.getName();
    HashSet<Territory> adjacentEnemyTerr = new HashSet<>();
    for(Territory t : p.getTerritoryList()){
      for(Territory terr : t.getNeighbours()){
        if(!terr.getOwner().equals(playerName) && terr.checkIsCloaked() <= 0){
          adjacentEnemyTerr.add(terr);
        }
      }
    }
    String space = "Adjacent Enemy Territories:\n";
    if(adjacentEnemyTerr.size() == 0){
      return space + "No territory Visible\n";
    }
    for(Territory t : adjacentEnemyTerr){
      space += displaySingleTerriInfo(t) + "(owned by " + t.getOwner() + ")\n";
    }
    return space;
  }

  /**
  * check if a specific player lose
  * @return true if lose 
  * 
  */
  public Boolean checkSinglePlayerLose(String playerName) {
    if (gameBoard.get(playerName).size() == 0) {
      return true;
    }
    return false;
  }

  /**
   * check if game end
   * @return the winner name if the game end, else return ""
   */
  public String checkGameEnd() {
    int endPlayer = 0;
    String winner = null;
    for (String s : gameBoard.keySet()) {
      if (gameBoard.get(s).size() == 0) {
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

  /**
   * add 1 basic soldier for one player on his territories after one game round
   * @param name is the player's name
   */
  public void spawnOneUnitForPlayer(String name) {
    for (Territory t : gameBoard.get(name)) {
      Soldiers temp = t.getOneUnits("Lv1");
      temp.updateCount(temp.getCount()+1);
      //update cloak status
      t.updateCloakStatus(t.checkIsCloaked() - 1);
    }
  }

  /**
   * add resource for one player
   * the amount of a player's adding resource depends one how many territories he has
   * resource amount += (owned territories number) * a specific number
   * @param name name of player
   */
  public void spawnResourceForPlayer(String name){
    Player p = getPlayerByName(name);
    int i = p.getTerritoryList().size();
    p.updateFoodResource(i * 100);
    p.updateTechResource(i * 100);
  }
}
