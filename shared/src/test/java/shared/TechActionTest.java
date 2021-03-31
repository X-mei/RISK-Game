package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TechActionTest {
  @Test
  public void test_TechActionTest() {
    TechAction ta = new TechAction("Player 1");
    assertEquals("T", ta.getActionName());
    assertEquals("Player 1", ta.getActionOwner());
  }
}
