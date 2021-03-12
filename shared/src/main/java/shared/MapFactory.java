package shared;

import java.util.LinkedHashSet;
import java.util.HashMap;

public class MapFactory {
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
  
  public HashMap<String, LinkedHashSet<Territory>> make2pMap(){
    HashMap<String, LinkedHashSet<Territory>> mapForTwo = new HashMap<>();
    LinkedHashSet<Territory> mapForTwoP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForTwoP2 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[6];
    terriSet[0] = new Territory("Dorado", "Player1");
    terriSet[1] = new Territory("Hanamura", "Player1");
    terriSet[2] = new Territory("Hollywood", "Player1");
    terriSet[3] = new Territory("Volskaya", "Player2");
    terriSet[4] = new Territory("Ilios", "Player2");
    terriSet[5] = new Territory("Junkertown", "Player2");
    terriSet[0].setNeighbor(terriSet[1]);
    terriSet[1].setNeighbor(terriSet[0]);
    terriSet[0].setNeighbor(terriSet[2]);
    terriSet[2].setNeighbor(terriSet[0]);
    terriSet[1].setNeighbor(terriSet[2]);
    terriSet[2].setNeighbor(terriSet[1]);
    terriSet[1].setNeighbor(terriSet[3]);
    terriSet[3].setNeighbor(terriSet[1]);
    terriSet[1].setNeighbor(terriSet[4]);
    terriSet[4].setNeighbor(terriSet[1]);
    terriSet[2].setNeighbor(terriSet[4]);
    terriSet[4].setNeighbor(terriSet[2]);
    terriSet[3].setNeighbor(terriSet[4]);
    terriSet[4].setNeighbor(terriSet[3]);
    terriSet[3].setNeighbor(terriSet[5]);
    terriSet[5].setNeighbor(terriSet[3]);
    terriSet[4].setNeighbor(terriSet[5]);
    terriSet[5].setNeighbor(terriSet[4]);
    mapForTwoP1.add(terriSet[0]);
    mapForTwoP1.add(terriSet[1]);
    mapForTwoP1.add(terriSet[2]);
    mapForTwoP2.add(terriSet[3]);
    mapForTwoP2.add(terriSet[4]);
    mapForTwoP2.add(terriSet[5]);
    mapForTwo.put("Player1", mapForTwoP1);
    mapForTwo.put("Player2", mapForTwoP2);
    return mapForTwo;
  }

  public HashMap<String, LinkedHashSet<Territory>> make3pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForThree = new HashMap<>();
    LinkedHashSet<Territory> mapForThreeP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForThreeP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForThreeP3 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[9];
    terriSet[0] = new Territory("Dorado", "Player1");
    terriSet[1] = new Territory("Hanamura", "Player1");
    terriSet[2] = new Territory("Hollywood", "Player1");
    terriSet[3] = new Territory("Volskaya", "Player2");
    terriSet[4] = new Territory("Ilios", "Player2");
    terriSet[5] = new Territory("Junkertown", "Player2");
    terriSet[6] = new Territory("Nepal", "Player3");
    terriSet[7] = new Territory("Oasis", "Player3");
    terriSet[8] = new Territory("Numbani", "Player3");
    terriSet[0].setNeighbor(terriSet[1]);
    terriSet[0].setNeighbor(terriSet[2]);
    terriSet[0].setNeighbor(terriSet[3]);
    terriSet[1].setNeighbor(terriSet[2]);
    terriSet[1].setNeighbor(terriSet[7]);
    terriSet[1].setNeighbor(terriSet[6]);
    terriSet[2].setNeighbor(terriSet[3]);
    terriSet[2].setNeighbor(terriSet[4]);
    terriSet[2].setNeighbor(terriSet[5]);
    terriSet[2].setNeighbor(terriSet[7]);
    terriSet[3].setNeighbor(terriSet[5]);
    terriSet[4].setNeighbor(terriSet[5]);
    terriSet[4].setNeighbor(terriSet[7]);
    terriSet[4].setNeighbor(terriSet[8]);
    terriSet[5].setNeighbor(terriSet[8]);
    terriSet[6].setNeighbor(terriSet[7]);
    terriSet[6].setNeighbor(terriSet[8]);
    terriSet[7].setNeighbor(terriSet[8]);    
    mapForThreeP1.add(terriSet[0]);
    mapForThreeP1.add(terriSet[1]);
    mapForThreeP1.add(terriSet[2]);
    mapForThreeP2.add(terriSet[3]);
    mapForThreeP2.add(terriSet[4]);
    mapForThreeP2.add(terriSet[5]);
    mapForThreeP3.add(terriSet[6]);
    mapForThreeP3.add(terriSet[7]);
    mapForThreeP3.add(terriSet[8]);
    mapForThree.put("Player1", mapForThreeP1);
    mapForThree.put("Player2", mapForThreeP2);
    mapForThree.put("Player3",mapForThreeP3);
    return mapForThree;
  }
  
  public HashMap<String, LinkedHashSet<Territory>> make4pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForFour = new HashMap<>();
    LinkedHashSet<Territory> mapForFourP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP3 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFourP4 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[8];
    terriSet[0] = new Territory("Dorado", "Player1");
    terriSet[1] = new Territory("Hanamura", "Player1");
    terriSet[2] = new Territory("Hollywood", "Player2");
    terriSet[3] = new Territory("Volskaya", "Player2");
    terriSet[4] = new Territory("Ilios", "Player3");
    terriSet[5] = new Territory("Junkertown", "Player3");
    terriSet[6] = new Territory("Nepal", "Player4");
    terriSet[7] = new Territory("Oasis", "Player4");
    terriSet[0].setNeighbor(terriSet[1]);
    terriSet[0].setNeighbor(terriSet[6]);
    terriSet[0].setNeighbor(terriSet[7]);
    terriSet[1].setNeighbor(terriSet[2]);
    terriSet[1].setNeighbor(terriSet[4]);
    terriSet[1].setNeighbor(terriSet[6]);
    terriSet[2].setNeighbor(terriSet[3]);
    terriSet[2].setNeighbor(terriSet[4]);
    terriSet[2].setNeighbor(terriSet[5]);
    terriSet[3].setNeighbor(terriSet[5]);
    terriSet[4].setNeighbor(terriSet[6]);
    terriSet[4].setNeighbor(terriSet[5]);
    terriSet[6].setNeighbor(terriSet[7]);    
    mapForFourP1.add(terriSet[0]);
    mapForFourP1.add(terriSet[1]);
    mapForFourP2.add(terriSet[2]);
    mapForFourP2.add(terriSet[3]);
    mapForFourP3.add(terriSet[4]);
    mapForFourP3.add(terriSet[5]);
    mapForFourP4.add(terriSet[6]);
    mapForFourP4.add(terriSet[7]);
    mapForFour.put("Player1", mapForFourP1);
    mapForFour.put("Player2", mapForFourP2);
    mapForFour.put("Player3", mapForFourP3);
    mapForFour.put("Player4", mapForFourP4);
    return mapForFour;
  }

  public HashMap<String, LinkedHashSet<Territory>> make5pMap() {
    HashMap<String, LinkedHashSet<Territory>> mapForFive = new HashMap<>();
    LinkedHashSet<Territory> mapForFiveP1 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP2 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP3 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP4 = new LinkedHashSet<>();
    LinkedHashSet<Territory> mapForFiveP5 = new LinkedHashSet<>();
    Territory[] terriSet = new Territory[10];
    terriSet[0] = new Territory("Dorado", "Player1");
    terriSet[1] = new Territory("Hanamura", "Player1");
    terriSet[2] = new Territory("Hollywood", "Player2");
    terriSet[3] = new Territory("Volskaya", "Player2");
    terriSet[4] = new Territory("Ilios", "Player3");
    terriSet[5] = new Territory("Junkertown", "Player3");
    terriSet[6] = new Territory("Nepal", "Player4");
    terriSet[7] = new Territory("Oasis", "Player4");
    terriSet[8] = new Territory("Numbani", "Player5");
    terriSet[9] = new Territory("Anubis", "Player5");
    terriSet[0].setNeighbor(terriSet[1]);
    terriSet[0].setNeighbor(terriSet[2]);
    terriSet[0].setNeighbor(terriSet[3]);
    terriSet[1].setNeighbor(terriSet[3]);
    terriSet[1].setNeighbor(terriSet[8]);
    terriSet[1].setNeighbor(terriSet[9]);
    terriSet[2].setNeighbor(terriSet[3]);
    terriSet[2].setNeighbor(terriSet[4]);
    terriSet[2].setNeighbor(terriSet[5]);
    terriSet[3].setNeighbor(terriSet[5]);
    terriSet[3].setNeighbor(terriSet[7]);
    terriSet[3].setNeighbor(terriSet[8]);
    terriSet[4].setNeighbor(terriSet[6]);
    terriSet[4].setNeighbor(terriSet[5]);
    terriSet[5].setNeighbor(terriSet[6]);
    terriSet[5].setNeighbor(terriSet[7]);
    terriSet[6].setNeighbor(terriSet[7]);
    terriSet[7].setNeighbor(terriSet[8]);
    terriSet[8].setNeighbor(terriSet[9]);
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
    mapForFive.put("Player1", mapForFiveP1);
    mapForFive.put("Player2", mapForFiveP2);
    mapForFive.put("Player3", mapForFiveP3);
    mapForFive.put("Player4", mapForFiveP4);
    mapForFive.put("Player5", mapForFiveP5);
    return mapForFive;
  }
}









