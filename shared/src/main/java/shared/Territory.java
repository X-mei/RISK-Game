package shared;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Constructs a Territory with the specified territory name and owner name
 * 
 * @param name is the name of the Territory.
 */
public class Territory {

  private final String territoryName;
  private String ownerName;
  private HashSet<Territory> neighbours;
  private HashSet<Units> units;

  public Territory(String tname, String oname) {
    this.territoryName = tname;
    this.ownerName = oname;
    this.neighbours = new HashSet<Territory>();
    this.units = new HashSet<Units>();
  }

  boolean isNeighbour(Territory t) {
    return this.neighbours.contains(t);
  }

  public HashSet<Territory> getNeighbours() {
    return this.neighbours;
  }

  public void setNeighbour(Territory t) {
    neighbours.add(t);
    t.setNeighbour(this);
  }

  public void setUnits(Units units) {
    this.units.add(units);
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

  public HashSet<Territory> neighboursByOneOwner() {
    HashSet<Territory> neighboursOneOwner = new HashSet<Territory>();
    for(Territory t: neighbours) {
      if (t.getOwner().equals(this.ownerName)) {
        neighboursOneOwner.add(t);
      }
    }
    return neighboursOneOwner;
  }


}
