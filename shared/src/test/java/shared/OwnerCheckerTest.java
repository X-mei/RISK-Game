package shared;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OwnerCheckerTest {
  
  @Test
  public void test_OwnerRule() throws IOException {
    BasicAction b1 = new Move("King", "Dorado B 10 Lv1");
    BasicAction b2 = new Move("King", "Hanamura B 10 Lv2");
    BasicAction b4 = new Attack("King", "Ilios B 10 Lv3");
    BasicAction b3 = new Attack("Red", "Ilios B 10 Lv4");
    BasicAction b5 = new Attack("Red", "A B 10 4");
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    OwnerChecker c = new OwnerChecker(null);
    assertEquals("The selected source do not exist.", c.checkMyRule(b5, b));
    assertEquals("You do not own the source territory.", c.checkMyRule(b4, b));
    assertEquals(null, c.checkMyRule(b3, b));
    assertEquals(null, c.checkMyRule(b2, b));
    assertEquals(null, c.checkMyRule(b1, b));
  }
}











