package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher;

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
    b.processSingleBasicAction(a1);
    //b.processSingleBasicAction(a2);
    b.processSingleBasicAction(a5);
    assertEquals(7, b.getTerritory("Dorado").getOneUnits("Basic Soldiers").getCount());
    assertEquals(13, b.getTerritory("Hanamura").getOneUnits("Basic Soldiers").getCount());
    //assertEquals(0, b.getTerritory("Ilios").getOneUnits("Basic Soldiers").getCount());
    //assertEquals(20, b.getTerritory("Junkertown").getOneUnits("Basic Soldiers").getCount());
    assertEquals(0, b.getTerritory("Ilios").getOneUnits("Basic Soldiers").getCount());
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
  public void test_processSingleBasicAction(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    BasicAction a1 = new Move("Red", "Ilios Volskaya 7");
    BasicAction a3 = new Attack("King", "Hanamura Ilios 9");
    //BasicAction a4 = new Attack("Red", "Ilios Hollywood 2");
    b.processSingleBasicAction(a1);
    b.processSingleBasicAction(a3);
    //assertEquals("King", b.getTerritory("Volskaya").getOwner());
  }

  @Test
  public void test_checkIfActionBoolean(){
    Board b = getTestBoard();
    for(String s : b.getAllTerritroy().keySet()){
      b.singleTerritoryUnitSetup(s, new int[]{10});
    }
    BasicAction a1 = new Move("King", "Dorado Hanamura 3");
    BasicAction a2 = new Move("King", "Dorado Hollywood 3");
    BasicAction a3 = new Attack("King", "Hanamura Volskaya 1");
    BasicAction a4 = new Attack("King", "Hanamura Ilios 1");
    HashSet<BasicAction> actions1 = new HashSet<>();
    actions1.add(a1);
    actions1.add(a2);
    HashSet<BasicAction> actions2 = new HashSet<>();
    actions2.add(a3);
    actions2.add(a4);
    assertEquals(true, b.checkIfActionBoolean(actions1, "Move"));
    assertEquals(true, b.checkIfActionBoolean(actions2, "Attack"));
    // BasicAction a5 = new Move("King", "Dorado Hanamura 312");
    // BasicAction a6 = new Attack("King", "Hanamura Volskaya 177");
    // HashSet<BasicAction> actions3 = new HashSet<>();
    // actions3.add(a5);
    // HashSet<BasicAction> actions4= new HashSet<>();
    // actions4.add(a6);
    // assertEquals(false, b.checkIfActionBoolean(actions3, "Move"));
    // assertEquals(false, b.checkIfActionBoolean(actions4, "Attack"));

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
    b.refreshTemp();
    b.updateTempCount("Hanamura", 1);
    Integer unitsNum = b.getTerritoryUnitsCount("Hanamura");
    assertEquals(4,unitsNum);
  }

}












