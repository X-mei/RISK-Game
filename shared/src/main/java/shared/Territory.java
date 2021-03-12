package shared;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Constructs a Territory with the specified territory name and owner name
 * 
 * @param name is the name of the Territory.
 */
public class Territory {

  private final String territoryName;
  private String ownerName;
  private LinkedHashMap<String, Territory> neighbours;
  private LinkedHashMap<String, Units> allUnits;

  /*public Territory(String tname, String oname, LinkedHashSet<Units> units){
    this.territoryName = tname;
    this.ownerName = oname;
    this.neighbours = new LinkedHashSet<Territory>();
    this.units = units;
  }*/

  public Territory(String tname, String oname) {
    this.territoryName = tname;
    this.ownerName = oname;
    this.neighbours = new LinkedHashMap<String, Territory>();
    this.allUnits = new LinkedHashMap<String, Units>();
  }

  public boolean isNeighbor(Territory t) {
    return this.neighbours.containsValue(t);
  }

  public LinkedHashSet<Territory> getNeighbours() {
    LinkedHashSet<Territory> neighborsSet = new LinkedHashSet<Territory>();
    for(HashMap.Entry<String,Territory> entry: neighbours.entrySet()) {
      neighborsSet.add(entry.getValue());
    }
    return neighborsSet;
  }

  public void setNeighbor(Territory t) {
    neighbours.put(t.getTerritoryName(), t);
    t.setNeighbor(this);
  }

  public void setUnits(Units units) {
    this.allUnits.put();
  }

  public String getTerritoryName() {
    return this.territoryName;
  }

  public String getOwner() {
    return ownerName;
  }

  public void updateOwner(String oname) {
    this.ownerName = oname;
  }

  public LinkedHashSet<Territory> neighboursByOneOwner() {
    LinkedHashSet<Territory> neighboursOneOwner = new LinkedHashSet<Territory>();
    for(HashMap.Entry<String,Territory> entry: neighbours.entrySet()) {
      if (entry.getValue().getOwner().equals((this.ownerName))) {
        neighboursOneOwner.add(entry.getValue());
      }
    }
    return neighboursOneOwner;
  }


}
