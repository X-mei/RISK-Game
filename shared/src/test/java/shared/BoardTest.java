package shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

public class BoardTest {
  private Board getTestBoard(){
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    return b;
  }

  @Test
  public void test_getBoard() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    HashMap<String, LinkedHashSet<Territory>> gameMap = b.getBoard();
    assertEquals(2, gameMap.size());
  }

  
  @Test
  public void test_getPlayerNum() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    assertEquals(2, b.getPlayerNum());
  }

  @Test
  public void test_getPlayer() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    ArrayList<Player> p = b.getPlayers();
    assertEquals("Red", p.get(0).getName());
    assertEquals("King", p.get(1).getName());
  }
  
  @Test
  public void test_askUnitSetup() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    String[] actual = b.askUnitSetup("King");
    String[] expected = new String[3];
    expected[0] = "You have 3 territories, how do you want to place units on Dorado ?\n";
    expected[1] = "You have 3 territories, how do you want to place units on Hanamura ?\n";
    expected[2] = "You have 3 territories, how do you want to place units on Hollywood ?\n";
    assertEquals(expected[0], actual[0]);
    assertEquals(expected[1], actual[1]);
    assertEquals(expected[2], actual[2]);
  }

  @Test
  public void test_getTerritory() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    Territory t = b.getTerritory("Hollywood");
    assertEquals("King", t.getOwner());
    Territory t1 = b.getTerritory("Junkertown");
    assertEquals("Red", t1.getOwner());
  }

  @Test
  public void test_getTotalUnits() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    assertEquals(20, b.getTotalUnits());
  }

  @Test
  public void test_displayAllPlayerAllBoard(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{5});
    }
    String out = b.displayAllPlayerAllBoard();
    assertEquals('R', out.charAt(0));
  }

  @Test
  public void test_processSingleMove(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    BasicAction a1 = new Move("King", "Dorado Hanamura 3");
    //BasicAction a2 = new Move("Red", "Ilios Junkertown 10");
    BasicAction a3 = new Attack("King", "Hanamura Volskaya 1");
    BasicAction a4 = new Attack("Red", "Ilios Hollywood 2");
    BasicAction a5 = new Move("Red", "Ilios Volskaya 10");
    //b.processSingleBasicAction(a1);
    //b.processSingleBasicAction(a2);
    //b.processSingleBasicAction(a5);
    // assertEquals(7, b.getTerritory("Dorado").getOneUnits("Basic Soldiers").getCount());
    // assertEquals(13, b.getTerritory("Hanamura").getOneUnits("Basic Soldiers").getCount());
    //assertEquals(0, b.getTerritory("Ilios").getOneUnits("Basic Soldiers").getCount());
    //assertEquals(20, b.getTerritory("Junkertown").getOneUnits("Basic Soldiers").getCount());
    //assertEquals(0, b.getTerritory("Ilios").getOneUnits("Basic Soldiers").getCount());
  }

  @Test
  public void test_processSingleAttackPre(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    BasicAction a3 = new Attack("King", "Hanamura Volskaya 6");
    BasicAction a4 = new Attack("Red", "Ilios Hollywood 2");
    b.processSingleBasicAttackPre(a3);
    b.processSingleBasicAttackPre(a4);
    assertEquals(4, b.getTerritory("Hanamura").getOneUnits("Basic Soldiers").getCount());
    assertEquals(8, b.getTerritory("Ilios").getOneUnits("Basic Soldiers").getCount());
  }

  @Test
  public void test_processAttackMerge(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    LinkedHashSet<BasicAction> testSet = new LinkedHashSet<>();
    BasicAction a1 = new Attack("King", "Hanamura Ilios 4");
    BasicAction a2 = new Attack("King", "Hanamura Volskaya 1");
    BasicAction a3 = new Attack("King", "Hanamura Ilios 2");
    BasicAction a4 = new Attack("King", "Hollywood Volskaya 3");
    BasicAction a5 = new Attack("King", "Hollywood Volskaya 2");
    testSet.add(a1);
    testSet.add(a2);
    testSet.add(a3);
    testSet.add(a4);
    testSet.add(a5);
    LinkedHashSet<BasicAction> ans = b.mergeOneTurnAttack(testSet);
    assertEquals(2, ans.size());
    for(BasicAction act : ans){
      assertEquals(6, act.getCount());
    }

  }

  @Test
  public void test_processSingleBasicAction(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    BasicAction a1 = new Move("Red", "Ilios Volskaya 1");
    BasicAction a2 = new Move("Red", "Ilios Volskaya 1");
    LinkedHashSet<BasicAction> s1 = new LinkedHashSet<>();
    s1.add(a1);
    s1.add(a2);
    BasicAction a3 = new Attack("King", "Hanamura Ilios 1");
    BasicAction a4 = new Attack("King", "Hanamura Ilios 9");
    LinkedHashSet<BasicAction> s2 = new LinkedHashSet<>();
    s2.add(a3);
    s2.add(a4);
    //BasicAction a4 = new Attack("Red", "Ilios Hollywood 2");
    b.processOneTurnMove(s1);
    assertEquals(12, b.getTerritory("Volskaya").getOneUnits("Basic Soldiers").getCount());
    b.processOneTurnAttackPre(s2);
    b.processOneTurnAttackNext(s2);
    b.processOneTurnAttackNext(b.mergeOneTurnAttack(s2));
    assertEquals(0, b.getTerritory("Hanamura").getOneUnits("Basic Soldiers").getCount());
  }

  @Test
  public void test_checkIfActionBoolean()throws IOException, IllegalArgumentException{
    HashSet<BasicAction> testmove = new HashSet<>();
    HashSet<BasicAction> testattack = new HashSet<>();
    HashSet<BasicAction> testmove1 = new HashSet<>();
    HashSet<BasicAction> testattack1 = new HashSet<>();
    HashSet<BasicAction> testattack2 = new HashSet<>();
    Board b = getTestBoard();
    b.singleTerritoryUnitSetup("Dorado", new int[]{5});
    b.singleTerritoryUnitSetup("Hanamura", new int[]{10});
    b.singleTerritoryUnitSetup("Hollywood", new int[]{5});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{5});
    b.singleTerritoryUnitSetup("Ilios", new int[]{10});
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5});
    BasicAction moveact1 = new Move("King", "Dorado Hollywood 1");
    BasicAction moveact2 = new Move("King", "Hanamura Dorado 2");
    BasicAction moveact3 = new Move("King", "A I 200");
    BasicAction attackact1 = new Attack("King", "Hanamura B 9");
    BasicAction attackact2 = new Attack("King", "A B 5");
    BasicAction attackact3 = new Attack("King", "Dorado Ilios 3");
    BasicAction attackact4 = new Attack("King", "Hollywood Ilios 3");
    b.refreshTemp("King");
    b.refreshTemp("Red");
    testmove.add(moveact1);
    testmove.add(moveact2);
    testmove1.add(moveact3);
    testattack.add(attackact1);
    testattack.add(attackact2);
    testattack1.add(attackact3);
    testattack2.add(attackact4);
    String type1 = "Move";
    String type2 = "Attack";
    Boolean status1 = b.checkIfActionBoolean(testmove, type1);
    Boolean status2 = b.checkIfActionBoolean(testattack, type2);
    Boolean status3 = b.checkIfActionBoolean(testmove1, type1);
    Boolean status4 = b.checkIfActionBoolean(testattack1, type2);
    Boolean status5= b.checkIfActionBoolean(testattack2, type2);
    assertEquals(true, status1);
    assertEquals(true, status5);
    assertEquals(false, status4);
    assertEquals(false, status3);
    assertEquals(false, status2);
  }

  @Test
  public void test_getTerritoryUnitsCount(){
    Board b = getTestBoard();
    Integer unitsNum = b.getTerritoryUnitsCount("Dorado");
    assertEquals(null,unitsNum);
  } 

  @Test
  public void test_updateTempCount(){
    Board b = getTestBoard();
    int[] count = {5};
    for (String s : b.getAllTerritroy().keySet()) {
      b.singleTerritoryUnitSetup(s, count);
    }
    b.refreshTemp("King");
    b.refreshTemp("Red");
    b.updateTempCount("Hanamura", 1);
    Integer unitsNum = b.getTerritoryUnitsCount("Hanamura");
    assertEquals(4,unitsNum);
  }

  
  @Test
  public void test_checkSinglePlayerLose(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    assertEquals("", b.checkGameEnd());
    assertEquals(false, b.checkSinglePlayerLose("King"));
    b.getTerritory("Dorado").updateOwner("Red");
    b.getTerritory("Hanamura").updateOwner("Red");
    b.getTerritory("Hollywood").updateOwner("Red");
    LinkedHashSet<Territory> temp = b.getBoard().get("King");
    temp.clear();
    assertEquals(true, b.checkSinglePlayerLose("King"));
    assertEquals("Red", b.checkGameEnd());
  }

  @Test
  public void spawnOneUnitForPlayer(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    b.spawnOneUnitForPlayer("King");
    assertEquals(11, b.getTerritory("Hanamura").getOneUnits("Basic Soldiers").getCount());
  }
}












