package shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class TerritoryTest {
  @Test
  public void test_getTerritoryName() {
    Territory territory = new Territory("testTerritory", "player1");
    assertEquals("testTerritory", territory.getTerritoryName());
  }

  @Test
  public void test_setNeighbor() {
    Territory territory = new Territory("testTerritory", "player1");
    Territory territory2 = new Territory("testTerritory2", "player1");
    territory.setNeighbor(territory2);
    territory2.setNeighbor(territory);
    assertEquals(true, territory.getNeighbours().contains(territory2));
    assertEquals(true, territory2.getNeighbours().contains(territory));
    assertEquals(true, territory.isNeighbor(territory2));
    assertEquals(true, territory2.isNeighbor(territory));
  }

  @Test
  public void test_getOwner() {
    Territory t1 = new Territory("t1", "player1");
    Territory t2 = new Territory("t2", "player2");
    assertEquals("player1", t1.getOwner());
    assertEquals("player2", t2.getOwner());
  }

  @Test
  public void test_getNeighbors() {  
    Territory t1 = new Territory("t1", "player1");
    Territory t2 = new Territory("t2", "player1");
    Territory t3 = new Territory("t3", "player2");
    t1.setNeighbor(t2);
    t2.setNeighbor(t1);
    t2.setNeighbor(t3);
    t3.setNeighbor(t2);
    LinkedHashSet<Territory> t1Neighbors = new LinkedHashSet<Territory>();
    t1Neighbors.add(t2);
    LinkedHashSet<Territory> t2Neighbors = new LinkedHashSet<Territory>();
    t2Neighbors.add(t1);
    t2Neighbors.add(t3);
    assertEquals(t1Neighbors, t1.getNeighbours());
    assertEquals(t2Neighbors, t2.getNeighbours());
  }

  @Test
  public void test_setUnits() {
    Territory t1 = new Territory("t1", "player1");
    UnitsFactory f = new UnitsFactory();
    Soldiers unit = f.createBasicSoldiers(5);
    t1.setUnits(unit);
    assertEquals(unit, t1.getOneUnits("Basic Soldiers"));
    LinkedHashSet<Units> unitSet = new LinkedHashSet<Units>();
    unitSet.add(unit);
    assertEquals(unitSet, t1.getUnits());
  }
  
  @Test
  public void test_updateOwner() {
    Territory t1 = new Territory("t1", "player1");
    assertEquals("player1", t1.getOwner());
    t1.updateOwner("player2");
    assertEquals("player2", t1.getOwner());
  }

  @Test
  public void test_neighborsByOneOwner() {
    Territory t1 = new Territory("t1", "player1");
    Territory t2 = new Territory("t2", "player1");
    Territory t3 = new Territory("t3", "player2");
    t1.setNeighbor(t2);
    t2.setNeighbor(t1);
    t2.setNeighbor(t3);
    t3.setNeighbor(t2);
    t1.setNeighbor(t3);
    t3.setNeighbor(t1);
    LinkedHashSet<Territory> neighs = new LinkedHashSet<Territory>();
    neighs.add(t2);
    assertEquals(neighs, t1.neighboursByOneOwner());
  }

}
