package shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;


public class Territory {

  private final String territoryName;
  private String ownerName;
  private Integer size;
  private HashMap<String, Integer> spyList;
  private LinkedHashMap<String, Territory> neighbours;
  private LinkedHashMap<String, Soldiers> allUnits;
  private boolean isCloaked;

  /**
   * Constructs a Territory with the specified territory name and owner name
   * @param tname is the territory name
   * @param oname is the owner name
   */
  public Territory(String tname, String oname) {
    this.territoryName = tname;
    this.ownerName = oname;
    this.size = 10;
    this.neighbours = new LinkedHashMap<String, Territory>();
    this.allUnits = new LinkedHashMap<String, Soldiers>();
    spyList = new HashMap<>();
    isCloaked = false;
  }

  /**
   * check if a territory is neighbor
   * @param t
   * @return
   */
  public boolean isNeighbor(Territory t) {
    return this.neighbours.containsValue(t);
  }

  /**
   * get all neighbor territories
   * @return the set of neighbor territories
   */
  public LinkedHashSet<Territory> getNeighbours() {
    LinkedHashSet<Territory> neighborsSet = new LinkedHashSet<Territory>();
    for(HashMap.Entry<String,Territory> entry: neighbours.entrySet()) {
      neighborsSet.add(entry.getValue());
    }
    return neighborsSet;
  }

  /**
   * set a territory as its neighbor
   * @param t is the territory you want to add as a neighbor
   */
  public void setNeighbor(Territory t) {
    neighbours.put(t.getTerritoryName(), t);
  }

  /**
   * set units on the territory
   * @param units you want put on that territory
   */
  public void setUnits(Soldiers units) {
    allUnits.put(units.getName(), units);
  }

  /**
   * get territory's name
   * @return the name of territory
   */
  public String getTerritoryName() {
    return this.territoryName;
  }

  /**
   * get the owner of the territory
   * @return the name of the owner
   */
  public String getOwner() {
    return ownerName;
  }

  /**
   * get the size of the territory
   * @return the size number
   */
  public Integer getSize() {
    return size;
  }

  /**
   * get all units on this territory
   * @return the units set
   */
  public LinkedHashSet<Soldiers> getUnits() {
    LinkedHashSet<Soldiers> unitsSet = new LinkedHashSet<Soldiers>();
    for(HashMap.Entry<String, Soldiers> entry: allUnits.entrySet()) {
      unitsSet.add(entry.getValue());
    }
    return unitsSet;
  }

  /**
   * get one specific unit from the territory by name
   * @param name is the unit's name
   * @return the specific unit object
   */
  public Soldiers getOneUnits(String name) {
    return allUnits.get(name);
  }

  /**
   * get all units from the territory
   * @return
   */
  public LinkedHashMap<String, Soldiers> getSoldiers() {
    return allUnits;
  }

  /**
   * change the owner of the territory
   * @param oname is the name of the new owner
   */
  public void updateOwner(String oname) {
    this.ownerName = oname;
  }

  /**
   * get all the neighboring territory that is owned by the player
   * @return a set of all territory conforming to requirement
   */
  public LinkedHashSet<Territory> neighboursByOneOwner() {
    LinkedHashSet<Territory> neighboursOneOwner = new LinkedHashSet<Territory>();
    for(HashMap.Entry<String,Territory> entry: neighbours.entrySet()) {
      if (entry.getValue().getOwner().equals((this.ownerName))) {
        neighboursOneOwner.add(entry.getValue());
      }
    }
    return neighboursOneOwner;
  }

  /**
   * clear all units on the territory
   */
  public void clearAllUnits(){
    allUnits.clear();
  }

  /**
   * check if one's spy is at this territory
   * @return
   */
  public int checkSpyCount(String playerName){
    for(String s : spyList.keySet()){
      if(s.equals(playerName)){
        if(spyList.get(s) > 0){
          return spyList.get(s);
        }
      }
    }
    return 0;
  }

  /**
   * add a spy in the territory
   * @param name is the owner of the spy
   */
  public void addSpy(String name){
    spyList.put(name, spyList.getOrDefault(name, 0) + 1);
  }

  /**
   * delete a spy in the territory
   * @param name is the spy owner
   */
  public void deleteSpy(String name){
    spyList.put(name, spyList.get(name) - 1);
  }

  /**
   * check if the territory is clocked
   * @return true if it is cloaked
   * else, not cloaked
   */
  public boolean checkIsCloaked(){
    return isCloaked;
  }

  /**
   * set the territory cloak state
   * @param b is to set whether this territory should be cloaked
   */
  public void setCloakStatus(boolean b){
    isCloaked = true;
  }

  public int calculateUnitsPower(){
    int result = 0;
    for (Soldiers u: allUnits.values()){
      result += u.getBonus()*u.getCount();
    }
    return result;
  }
}
