package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TerritoryTest {
  @Test
  public void test_getName() {
    Territory territory = new Territory();
    assertEquals("territory", territory.getName());
  }

}
