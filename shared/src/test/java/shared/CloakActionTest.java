package shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class CloakActionTest {
  @Test
  public void test_cloak() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> new CloakAction("player1", "start 10 dasda"));
    CloakAction c1 = new CloakAction("player1", "Dorado");
    assertEquals("C", c1.getActionName());
  }

}










