package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RuleCheckerTest {
  @Test
  public void test_RuleChecker() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new OwnerChecker(new RouteChecker(null));
    BasicAction b1 = new Move("King", "Dorado Hollywood 10");
    BasicAction b2 = new Move("King", "Hanamura Hanamura 5");
    BasicAction b3 = new Move("King", "Dorado Ilios 9");
    BasicAction b4 = new Move("King", "A Hollywood 10");
    BasicAction b5 = new Move("King", "Hanamura B 5");
    assertEquals(null, c.checkAction(b1, b));
    assertEquals(null, c.checkAction(b2, b));
    assertEquals("No existing route between source and destination!", c.checkAction(b3, b));
    assertEquals("The selected source do not exist.", c.checkAction(b4, b));
    assertEquals("The selected destination do not exist.", c.checkAction(b5, b));
  }

}
