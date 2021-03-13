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



  // @Test
  // public voido test_refreshTemp(){
  //   Board b = getTestBoard();
    
  // }
}












