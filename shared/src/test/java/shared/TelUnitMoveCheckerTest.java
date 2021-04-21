package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TelUnitMoveCheckerTest {
  @Test
  public void test_telUnitMoving() {
    BasicAction a = new Attack("King", "Dorado Ilios 10 Tel");
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker r = new TelUnitMoveChecker(null);
    assertEquals(null, r.checkAction(a, b));
  }

}
