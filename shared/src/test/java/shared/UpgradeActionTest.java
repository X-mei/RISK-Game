package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UpgradeActionTest {
  @Test
  public void test_UpgradeActionCheck() {
    ActionFactory f = new ActionFactory();
    UpgradeAction ua1 = new UpgradeAction("Player 1", "A Lv1 4 Lv7");
    assertEquals("U", ua1.getActionName());
    assertThrows(IllegalArgumentException.class, () -> f.createUpgrade("Player 2", "A 4 Lv7"));
    assertThrows(IllegalArgumentException.class, () -> f.createUpgrade("Player 3", "A Lv4 4s Lv7"));
  }

}











