package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OwnerCheckerTest {
  @Test
  public void test_OwnerRule() {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    BasicAction b1 = player.formAction("M", "A B 10");
  }

}
