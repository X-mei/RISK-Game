package shared;

import java.util.LinkedHashSet;

import com.google.common.base.Function;

import java.util.HashMap;
import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*;

public class Board {
  protected int playerNum;
  protected int totalUnitsNum;
  protected HashMap<String, LinkedHashSet<Territory>> gameBoard;
  //final LinkedHashSet<Territory> allTerritories;
  protected MapFactory mapF; 
  protected LinkedHashMap<String, Territory> allTerritory; 
  //protected LinkedHashSet<>
  protected UnitsFactory UnitsF; 
  protected HashMap<String, Function<Integer, Soldiers>> unitsCreateFunction;
  private final RuleChecker moveRuleChecker;
  private final RuleChecker attackRuleChecker;
  private HashMap<String, Integer> tempCount;
  private LinkedHashSet<String> UnitName;

  public Board(int num, MapFactory mapFac, UnitsFactory UnitsFac){
    this.playerNum = num;
    this.totalUnitsNum = 20;
    this.mapF = mapFac;
    this.gameBoard = mapF.getMap(num);
    this.UnitsF = UnitsFac;
    this.unitsCreateFunction = new HashMap<String, Function<Integer, Soldiers>>();
    for(String s : gameBoard.keySet()){
      for(Territory t : gameBoard.get(s)){
        allTerritory.put(t.getTerritoryName(), t);
      }
    }
    this.UnitName = new LinkedHashSet<>();
    UnitName.add("Basic Soldiers");
    this.attackRuleChecker = new OwnerChecker(new NeighborChecker(new UnitMovingChecker(null)));
    this.moveRuleChecker = new 
  }

  public HashMap<String, LinkedHashSet<Territory>> getBoard(){
    return gameBoard;
  }

  public int getPlayerNum(){
    return playerNum;
  }

  public String[] askUnitSetup(String playerName){
    LinkedHashSet<Territory> singlePlayerTerritories = gameBoard.get(playerName);
    int territoryNumForOnePlayer = singlePlayerTerritories.size();
    String[] unitSetupStrings = new String[territoryNumForOnePlayer];
    int i = 0;
    for(Territory t : singlePlayerTerritories){
      unitSetupStrings[i] = "You have " + territoryNumForOnePlayer + " territories, how do you want to place units on " + t.getTerritoryName() + " ?\n";
      i ++;
    }
    return unitSetupStrings;
  }

  public void setUpUnitsCreationMap(){
    unitsCreateFunction.put("Basic Soldiers", (count) -> UnitsF.createBasicSoldiers(count));
  }

  public Territory getTerritory(String name){
    return allTerritory.get(name);
  }

  public void refreshTemp(){
    for(String s : allTerritory.keySet()){
      tempCount.put(s, allTerritory.get(s).getOneUnits("Basic Soldiers").getCount());
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
  public void singleTerrotoryUnitSetup(String territoryName, int[] count){
    int ind = 0;
    Territory t = allTerritory.get(territoryName);
    for (String s : UnitName){
      Soldiers u = unitsCreateFunction.get(s).apply(count[ind ++]); 
      t.setUnits(u);
    }
  }

  public void processSingleBasicAction(BasicAction basicAct){
    if(basicAct.getActionName() == "M"){
      processSingleBasicMove(basicAct);
    }
  }

  public void processSingleBasicMove(BasicAction basicAct){
    String src = basicAct.getSource();
    String dest = basicAct.getDestination();
    Soldiers srcBasicSoldier = getSoldiers("Basic Soldiers", src);
    Soldiers destBasicSoldier = getSoldiers("Basic Soldiers", dest);;
    int count = basicAct.getCount();
    srcBasicSoldier.updateCount(srcBasicSoldier.getCount() - count);
    destBasicSoldier.updateCount(destBasicSoldier.getCount() - count);
  }

  public void processSingleBasicAttack(BasicAction basicAct){
    String src = basicAct.getSource();
    String dest = basicAct.getDestination();
    Soldiers srcBasicSoldier = getSoldiers("Basic Soldiers", src);
    Soldiers destBasicSoldier = getSoldiers("Basic Soldiers", dest);;
    int srcBasicSoldierNum = srcBasicSoldier.getCount();
    int destBasicSoldierNum = destBasicSoldier.getCount();
    //int count = basicAct.getCount();
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

  private Soldiers getSoldiers(String UnitsName, String territoryName){
    Territory terr = allTerritory.get(territoryName);
    LinkedHashSet<Soldiers> terrAllUnits = terr.getUnits();
    Soldiers terrBasicSoldier = null;
    for(Soldiers u : terrAllUnits){
      if(u.getName().equals("Basic Soldiers")){
        terrBasicSoldier = u;
        break;
      }
    }
    return terrBasicSoldier;
  }

}










