package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RouteCheckerTest {
  
  @Test
  public void test_RouteRule() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new RouteChecker(new ResourceChecker(null));
    BasicAction b1 = new Move("King", "Dorado Hollywood 6 Lv1");
    BasicAction b2 = new Move("King", "Hanamura Dorado 4 Lv1");
    BasicAction b3 = new Move("King", "Dorado Ilios 9 Lv1");
    //BasicAction b4 = new Move("King", "A Hollywood 10 Lv1");
    //BasicAction b5 = new Move("King", "Hanamura B 5 Lv1");
    BasicAction b6 = new Move("King", "Hanamura Hollywood 1 Lv1");
    Player p = b.getPlayerByName("King");
    p.updateFoodResource(-900);
    p.refreshTempFoodResource();
    assertNull(null, c.checkAction(b1, b));
    assertEquals(60, b1.getFoodConsume());
    //System.out.println(p.getTempFoodResource());
    assertNull(null, c.checkAction(b2, b));
    assertEquals(40, b2.getFoodConsume());
    //System.out.println(p.getTempFoodResource());
    assertEquals("No enough food to move those soldiers.", c.checkAction(b6, b));
    assertEquals("No existing route between source and destination!", c.checkAction(b3, b));
    //assertEquals("The selected source do not exist.", c.checkMyRule(b4, b));
    //assertEquals("The selected destination do not exist.", c.checkMyRule(b5, b));
  }

}










