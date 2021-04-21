package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SpyUnitMovingCheckerTest {
  @Test
  public void test_spyMoveChecker() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    SpecialRuleChecker c1 = new SpyUpgradeChecker(null);
    RuleChecker c2 = new SpyUnitMovingChecker(null);
    b.singleTerritoryUnitSetup("Dorado", new int[]{5, 0, 0, 0, 0, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Hanamura", new int[]{3, 10, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[] {0, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{1, 0, 0, 5, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{0, 0 , 0, 0, 10, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5, 0, 0, 0, 0, 0, 5, 0});
    Player p = b.getPlayerByName("King");
    b.refreshTemp("King");
    UpgradeAction a1 = new UpgradeAction("King", "Hanamura Lv1 1 Spy");
    b.processSingleUpdateUnit(a1);
    BasicAction a2 = new Move("King", "Hanamura Dorado 1 Spy");
    BasicAction a3 = new Move("King", "Hanamura Dorado 2 Spy");
    assertEquals(null, c1.checkAction(a1, b));
    assertEquals("You do not have enough spy to move.", c2.checkAction(a3, b));
    assertEquals(null, c2.checkAction(a2, b));
  }

}
