package shared;

import java.util.LinkedHashSet;
import java.util.HashMap;

public class MapFactory {
  /**
   * Distribute different maps for different player number
   * @param playerNum is the number of player 
   * @return the crresponding map
   */
  public HashMap<String, LinkedHashSet<Territory>> getMap(int playerNum){
    if (playerNum == 2) {
      return make2pMap();
    }
    else if (playerNum == 3) {
      return make3pMap();
    }
    else if (playerNum == 4) {
      return make4pMap();
    }
    else if (playerNum == 5) {
      return make5pMap();
    }
    else {
      throw new IllegalArgumentException("Wrong player for the game.");
    }
  }
  
  /**
   * set two territories as neighbors for each
   * @param t1 is one territory
   * @param t2 is another territory
   */
  private void setNeighborBoth(Territory t1, Territory t2){
    t1.setNeighbor(t2);
    t2.setNeighbor(t1);
  }

  /**
   * Create map for 2 players
   * @return a HashMap which contains the players number and their territories
   */
  public HashMap<String, LinkedHashSet<Territory>> make2pMap(){
    HashMap<String, LinkedHashSet<Territory>> mapForTwo = new HashMap<>();
    LinkedHashSet<Territory> mapForTwoP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForTwoP2 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[6];
    terriSet[0] = new Territory("Dorado", "King");
    terriSet[1] = new Territory("Hanamura", "King");
    terriSet[2] = new Territory("Hollywood", "King");
    terriSet[3] = new Territory("Volskaya", "Red");
    terriSet[4] = new Territory("Ilios", "Red");
    terriSet[5] = new Territory("Junkertown", "Red");
    setNeighborBoth(terriSet[0], terriSet[1]);
    setNeighborBoth(terriSet[0], terriSet[2]);
    setNeighborBoth(terriSet[1], terriSet[2]);
    setNeighborBoth(terriSet[1], terriSet[3]);
    setNeighborBoth(terriSet[1], terriSet[4]);  
    setNeighborBoth(terriSet[2], terriSet[4]);
    setNeighborBoth(terriSet[3], terriSet[4]);
    setNeighborBoth(terriSet[3], terriSet[5]);
    setNeighborBoth(terriSet[4], terriSet[5]);
    mapForTwoP1.add(terriSet[0]);
    mapForTwoP1.add(terriSet[1]);
    mapForTwoP1.add(terriSet[2]);
    mapForTwoP2.add(terriSet[3]);
    mapForTwoP2.add(terriSet[4]);
    mapForTwoP2.add(terriSet[5]);
    mapForTwo.put("King", mapForTwoP1);
    mapForTwo.put("Red", mapForTwoP2);
    return mapForTwo;
  }

  /**
   * Create map for 2 players
   * @return a HashMap which contains the players number and their territories
   */
  public HashMap<String, LinkedHashSet<Territory>> make3pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForThree = new HashMap<>();
    LinkedHashSet<Territory> mapForThreeP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForThreeP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForThreeP3 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[9];
    terriSet[0] = new Territory("Dorado", "King");
    terriSet[1] = new Territory("Hanamura", "King");
    terriSet[2] = new Territory("Hollywood", "King");
    terriSet[3] = new Territory("Volskaya", "Red");
    terriSet[4] = new Territory("Ilios", "Red");
    terriSet[5] = new Territory("Junkertown", "Red");
    terriSet[6] = new Territory("Nepal", "Pink");
    terriSet[7] = new Territory("Oasis", "Pink");
    terriSet[8] = new Territory("Numbani", "Pink");
    setNeighborBoth(terriSet[0], terriSet[1]);
    setNeighborBoth(terriSet[0], terriSet[2]);
    setNeighborBoth(terriSet[0], terriSet[3]);
    setNeighborBoth(terriSet[1], terriSet[2]);
    setNeighborBoth(terriSet[1], terriSet[7]);
    setNeighborBoth(terriSet[1], terriSet[6]);
    setNeighborBoth(terriSet[2], terriSet[3]);
    setNeighborBoth(terriSet[2], terriSet[4]);
    setNeighborBoth(terriSet[2], terriSet[5]);
    setNeighborBoth(terriSet[2], terriSet[7]);
    setNeighborBoth(terriSet[3], terriSet[5]);
    setNeighborBoth(terriSet[4], terriSet[5]);
    setNeighborBoth(terriSet[4], terriSet[7]);
    setNeighborBoth(terriSet[4], terriSet[8]);
    setNeighborBoth(terriSet[5], terriSet[8]);
    setNeighborBoth(terriSet[6], terriSet[7]);
    setNeighborBoth(terriSet[6], terriSet[8]);
    setNeighborBoth(terriSet[7], terriSet[8]);    
    mapForThreeP1.add(terriSet[0]);
    mapForThreeP1.add(terriSet[1]);
    mapForThreeP1.add(terriSet[2]);
    mapForThreeP2.add(terriSet[3]);
    mapForThreeP2.add(terriSet[4]);
    mapForThreeP2.add(terriSet[5]);
    mapForThreeP3.add(terriSet[6]);
    mapForThreeP3.add(terriSet[7]);
    mapForThreeP3.add(terriSet[8]);
    mapForThree.put("King", mapForThreeP1);
    mapForThree.put("Red", mapForThreeP2);
    mapForThree.put("Pink",mapForThreeP3);
    return mapForThree;
  }
  
  /**
   * Create map for 4 players
   * @return a HashMap which contains the players number and their territories
   */
  public HashMap<String, LinkedHashSet<Territory>> make4pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForFour = new HashMap<>();
    LinkedHashSet<Territory> mapForFourP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP3 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP4 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[8];
    terriSet[0] = new Territory("Dorado", "King");
    terriSet[1] = new Territory("Hanamura", "King");
    terriSet[2] = new Territory("Hollywood", "Red");
    terriSet[3] = new Territory("Volskaya", "Red");
    terriSet[4] = new Territory("Ilios", "Pink");
    terriSet[5] = new Territory("Junkertown", "Pink");
    terriSet[6] = new Territory("Nepal", "Blue");
    terriSet[7] = new Territory("Oasis", "Blue");
    setNeighborBoth(terriSet[0], terriSet[1]);
    setNeighborBoth(terriSet[0], terriSet[6]);
    setNeighborBoth(terriSet[0], terriSet[7]);
    setNeighborBoth(terriSet[1], terriSet[2]);
    setNeighborBoth(terriSet[1], terriSet[4]);
    setNeighborBoth(terriSet[1], terriSet[6]);
    setNeighborBoth(terriSet[2], terriSet[3]);
    setNeighborBoth(terriSet[2], terriSet[4]);
    setNeighborBoth(terriSet[2], terriSet[5]);
    setNeighborBoth(terriSet[3], terriSet[5]);
    setNeighborBoth(terriSet[4], terriSet[6]);
    setNeighborBoth(terriSet[4], terriSet[5]);
    setNeighborBoth(terriSet[6], terriSet[7]);    
    mapForFourP1.add(terriSet[0]);
    mapForFourP1.add(terriSet[1]);
    mapForFourP2.add(terriSet[2]);
    mapForFourP2.add(terriSet[3]);
    mapForFourP3.add(terriSet[4]);
    mapForFourP3.add(terriSet[5]);
    mapForFourP4.add(terriSet[6]);
    mapForFourP4.add(terriSet[7]);
    mapForFour.put("King", mapForFourP1);
    mapForFour.put("Red", mapForFourP2);
    mapForFour.put("Pink", mapForFourP3);
    mapForFour.put("Blue", mapForFourP4);
    return mapForFour;
  }

  /**
   * Create map for 5 players
   * @return a HashMap which contains the players number and their territories
   */
  public HashMap<String, LinkedHashSet<Territory>> make5pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForFive = new HashMap<>();
    LinkedHashSet<Territory> mapForFiveP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP3 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP4 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP5 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[10];
    terriSet[0] = new Territory("Dorado", "King");
    terriSet[1] = new Territory("Hanamura", "King");
    terriSet[2] = new Territory("Hollywood", "Red");
    terriSet[3] = new Territory("Volskaya", "Red");
    terriSet[4] = new Territory("Ilios", "Pink");
    terriSet[5] = new Territory("Junkertown", "Pink");
    terriSet[6] = new Territory("Nepal", "Blue");
    terriSet[7] = new Territory("Oasis", "Blue");
    terriSet[8] = new Territory("Numbani", "Green");
    terriSet[9] = new Territory("Anubis", "Green");
    setNeighborBoth(terriSet[0], terriSet[1]);
    setNeighborBoth(terriSet[0], terriSet[2]);
    setNeighborBoth(terriSet[0], terriSet[3]);
    setNeighborBoth(terriSet[1], terriSet[3]);
    setNeighborBoth(terriSet[1], terriSet[8]);
    setNeighborBoth(terriSet[1], terriSet[9]);
    setNeighborBoth(terriSet[2], terriSet[3]);
    setNeighborBoth(terriSet[2], terriSet[4]);
    setNeighborBoth(terriSet[2], terriSet[5]);
    setNeighborBoth(terriSet[3], terriSet[5]);
    setNeighborBoth(terriSet[3], terriSet[7]);
    setNeighborBoth(terriSet[3], terriSet[8]);
    setNeighborBoth(terriSet[4], terriSet[6]);
    setNeighborBoth(terriSet[4], terriSet[5]);
    setNeighborBoth(terriSet[5], terriSet[6]);
    setNeighborBoth(terriSet[5], terriSet[7]);
    setNeighborBoth(terriSet[6], terriSet[7]);
    setNeighborBoth(terriSet[7], terriSet[8]);
    setNeighborBoth(terriSet[8], terriSet[9]);
    mapForFiveP1.add(terriSet[0]);
    mapForFiveP1.add(terriSet[1]);
    mapForFiveP2.add(terriSet[2]);
    mapForFiveP2.add(terriSet[3]);
    mapForFiveP3.add(terriSet[4]);
    mapForFiveP3.add(terriSet[5]);
    mapForFiveP4.add(terriSet[6]);
    mapForFiveP4.add(terriSet[7]);
    mapForFiveP5.add(terriSet[8]);
    mapForFiveP5.add(terriSet[9]);
    mapForFive.put("King", mapForFiveP1);
    mapForFive.put("Red", mapForFiveP2);
    mapForFive.put("Pink", mapForFiveP3);
    mapForFive.put("Blue", mapForFiveP4);
    mapForFive.put("Green", mapForFiveP5);
    return mapForFive;
  }
}









