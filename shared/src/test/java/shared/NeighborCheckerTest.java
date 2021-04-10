package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NeighborCheckerTest {
  @Test
  public void test_CheckNeighbor() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new NeighborChecker(null);
    //BasicAction b1 = new Attack("King", "Dorado B 10 Lv1");
    //BasicAction b2 = new Attack("King", "Hanamura Hanamura 5 Lv1");
    BasicAction b3 = new Attack("King", "Hanamura Ilios 9 Lv1");
    BasicAction b4 = new Attack("King", "Hanamura Junkertown 1 Lv1");
    //BasicAction b5 = new Attack("King", "A Junkertown 5 Lv1");
    BasicAction b6 = new Attack("King", "Hanamura Ilios 2 Lv1");
    Player p = b.getPlayerByName("King");
    p.updateFoodResource(-990);
    p.refreshTempFoodResource();
    //assertEquals("The selected destination do not exist.", c.checkMyRule(b1, b));
    //assertEquals("You cannot attack your own territory.", c.checkMyRule(b2, b));
    assertEquals(null, c.checkMyRule(b3, b));
    assertEquals("Destination is not the neighbor of the source!", c.checkMyRule(b4, b));
    //assertEquals("The selected source do not exist.", c.checkMyRule(b5, b));
    assertEquals("No enough food to move those soldiers.", c.checkMyRule(b6, b));
  }
}







