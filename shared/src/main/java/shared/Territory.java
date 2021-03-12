package shared;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Constructs a Territory with the specified territory name and owner name
 * 
 * @param name is the name of the Territory.
 */
public class Territory {

  private final String territoryName;
  private String ownerName;
  private LinkedHashSet<Territory> neighbours;
  private LinkedHashSet<Units> allUnits;

  /*public Territory(String tname, String oname, LinkedHashSet<Units> units){
    this.territoryName = tname;
    this.ownerName = oname;
    this.neighbours = new LinkedHashSet<Territory>();
    this.units = units;
  }*/

  public Territory(String tname, String oname) {
    this.territoryName = tname;
    this.ownerName = oname;
    this.neighbours = new LinkedHashSet<Territory>();
    this.allUnits = new LinkedHashSet<Units>();
  }

  public boolean isNeighbor(Territory t) {
    return this.neighbours.contains(t);
  }

  public LinkedHashSet<Territory> getNeighbours() {
    return this.neighbours;
  }

  public void setNeighbor(Territory t) {
    neighbours.add(t);
    t.setNeighbor(this);
  }

  public void setUnits(Units units) {
    this.allUnits.add(units);
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
    for(Territory t: neighbours) {
      if (t.getOwner().equals(this.ownerName)) {
        neighboursOneOwner.add(t);
      }
    }
    return neighboursOneOwner;
  }


}
