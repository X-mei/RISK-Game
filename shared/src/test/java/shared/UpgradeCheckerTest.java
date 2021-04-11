package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UpgradeCheckerTest {
  
  @Test
  public void test_UpgradeAction() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    SpecialRuleChecker c = new UpgradeChecker(null);
    b.singleTerritoryUnitSetup("Dorado", new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Hanamura", new int[]{3, 10, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[] {0, 0, 5, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{1, 0, 0, 5, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{0, 0 ,  0, 0, 10, 0, 0, 0, 0}); 
    b.singleTerritoryUnitSetup("Junkertown", new int[]{5, 0, 0, 0, 0, 0, 5, 0, 0});
    UpgradeAction a1 = new UpgradeAction("King", "Hanamura Lv1 3 Lv3");
    UpgradeAction a2 = new UpgradeAction("King", "Hanamura Lv1 4 Lv7");
    UpgradeAction a3 = new UpgradeAction("King", "Hanamura Lv9 4 Lv7");
    UpgradeAction a4 = new UpgradeAction("King", "Ilios Lv3 4 Lv7");
    UpgradeAction a5 = new UpgradeAction("King", "Hanamura Lv1 1 Lv7");
    UpgradeAction a6 = new UpgradeAction("King", "Hanamura Lv3 3 Lv7");
    Player p = b.getPlayerByName("King");
    b.refreshTemp("King");
    p.updateTechResource(-900);
    p.refreshTempTechResource();
    assertEquals("Your tech level is not high enough.", c.checkAction(a5, b));
    p.setTechLevel(6);
    assertEquals("You do not own the source territory.", c.checkAction(a4, b));
    assertEquals("Invalid starting or ending level.", c.checkAction(a3, b));
    assertEquals("You do not have enough soldier to upgrade.", c.checkAction(a2, b));
    assertEquals(null, c.checkAction(a1, b));
    assertEquals("You do not have enough resources to upgrade.", c.checkAction(a6, b));
    
  }
}






