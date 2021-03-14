package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashSet;

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
}
