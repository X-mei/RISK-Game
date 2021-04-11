package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnitMovingCheckerTest {
  @Test
  public void test_CheckUnitMoving() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new UnitMovingChecker(null);  
    b.singleTerritoryUnitSetup("Dorado", new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hanamura", new int[]{3, 10, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[]{0, 0, 5, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{1, 0, 0, 5, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{0, 0, 0, 0, 10, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5, 0, 0, 0, 0, 0, 5, 0, 0});
    BasicAction b1 = new Move("King", "Dorado B 10 Lv1");
    BasicAction b2 = new Move("King", "Hanamura Hollywood 5 Lv2");
    BasicAction b3 = new Attack("King", "Hollywood B 9 Lv3");
    //BasicAction b4 = new Move("King", "A B 5 Lv4");
    BasicAction b5 = new Move("King", "Hanamura Hollywood 6 Lv2");
    BasicAction b6 = new Attack("King", "Hollywood B 5 Lv3");
    b.refreshTemp("King");
    b.refreshTemp("Red");
    //assertEquals("The selected source do not exist.", c.checkMyRule(b4, b));
    assertEquals(null, c.checkMyRule(b2, b));
    assertEquals("You do not have enough unit to move.", c.checkMyRule(b5, b));
    assertEquals("You do not have enough unit to move.", c.checkMyRule(b1, b));
    assertEquals(null, c.checkMyRule(b6, b));
    assertEquals("You do not have enough unit to move.", c.checkMyRule(b3, b));
  }

}











