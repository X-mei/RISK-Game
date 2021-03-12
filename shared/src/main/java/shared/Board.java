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
  protected HashMap<String, LinkedHashSet<Territory>> gameBoard;
  //final LinkedHashSet<Territory> allTerritories;
  protected MapFactory mapF; 
  protected LinkedHashMap<String, Territory> TerritoryList; 
  //protected LinkedHashSet<>
  protected UnitsFactory UnitsF; 
  protected HashMap<String, Function<Integer, Units>> unitsCreateFunction;

  public Board(int num, MapFactory mapFac, UnitsFactory UnitsFac){
    this.playerNum = num;
    this.mapF = mapFac;
    this.gameBoard = mapF.getMap(num);
    this.UnitsF = UnitsFac;
    this.unitsCreateFunction = new HashMap<String, Function<Integer, Units>>();
    for(String s : gameBoard.keySet()){
      for(Territory t : gameBoard.get(s)){
        TerritoryList.put(t.getTerritoryName(), t);
      }
    }
    
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

  public void processUnitSetup(String playerName, int[] count){
    int ind = 0;
    for (Territory t : gameBoard.get(playerName)){
      t.setUnits(new BasicSoldiers(count[ind++]));
    }
  }
  
  public void setUpUnitsCreationMap(){
    for()
  }
}









