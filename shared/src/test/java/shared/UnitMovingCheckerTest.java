package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnitMovingCheckerTest {
  @Test
  public void test_CheckUnitMoving() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new UnitMovingChecker(null);  
    b.singleTerritoryUnitSetup("Dorado", new int[]{5});
    b.singleTerritoryUnitSetup("Hanamura", new int[]{10});
    b.singleTerritoryUnitSetup("Hollywood", new int[]{5});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{5});
    b.singleTerritoryUnitSetup("Ilios", new int[]{10});
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5});
    BasicAction b1 = new Move("King", "Dorado B 10");
    BasicAction b2 = new Move("King", "Hanamura B 5");
    BasicAction b3 = new Attack("King", "Hanamura B 9");
    BasicAction b4 = new Move("King", "A B 5");
    b.refreshTemp();
    assertEquals("The selected source do not exist.", c.checkMyRule(b4, b));
    assertEquals("You do not have enough unit to move.", c.checkMyRule(b1, b));
    b.refreshTemp();
    assertEquals(null, c.checkMyRule(b2, b));
    assertEquals("You do not have enough unit to move.", c.checkMyRule(b3, b));
  }

}











