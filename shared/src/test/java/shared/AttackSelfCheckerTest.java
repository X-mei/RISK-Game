package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AttackSelfCheckerTest {
  @Test
  public void test_attackSelf() {
    BasicAction a = new Attack("Red", "Ilios Ilios 10 Lv3");
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new AttackSelfChecker(null);
    assertEquals("You cannot attack your own territory.", c.checkAction(a, b));
  }

}







