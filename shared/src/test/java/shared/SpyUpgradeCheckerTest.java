package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SpyUpgradeCheckerTest {
  @Test
  public void test_spyUpgradeChecker() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    SpecialRuleChecker c = new SpyUpgradeChecker(null);
    b.singleTerritoryUnitSetup("Dorado", new int[]{5, 0, 0, 0, 0, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Hanamura", new int[]{3, 10, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[] {0, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{1, 0, 0, 5, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{0, 0 , 0, 0, 10, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5, 0, 0, 0, 0, 0, 5, 0});
    UpgradeAction a1 = new UpgradeAction("King", "A Lv1 3 Spy");
    UpgradeAction a2 = new UpgradeAction("King", "Ilios Lv1 4 Spy");
    UpgradeAction a3 = new UpgradeAction("King", "Hanamura Lv9 4 Spy");
    UpgradeAction a4 = new UpgradeAction("King", "Hanamura Lv1 4 Spy");
    UpgradeAction a5 = new UpgradeAction("King", "Hanamura Lv1 2 Spy");
    UpgradeAction a6 = new UpgradeAction("King", "Hanamura Lv1 1 Spy");
    Player p = b.getPlayerByName("King");
    b.refreshTemp("King");
    p.updateTechResource(-980);
    p.refreshTempTechResource();
    assertEquals("The selected source do not exist.", c.checkAction(a1, b));
    assertEquals("You do not own the source territory.", c.checkAction(a2, b));
    assertEquals("Invalid starting level.", c.checkAction(a3, b));
    assertEquals("You do not have enough soldier to upgrade.", c.checkAction(a4, b));
    assertEquals("You do not have enough resources to upgrade.", c.checkAction(a5, b));
    assertEquals(null, c.checkAction(a6, b));
  }

}
