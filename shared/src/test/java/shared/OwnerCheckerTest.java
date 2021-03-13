package shared;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class OwnerCheckerTest {
  
  @Test
  public void test_OwnerRule() throws IOException {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    BasicAction b1 = player.formAction("M", "A B 10");
  }

}
