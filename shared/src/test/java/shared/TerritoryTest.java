package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TerritoryTest {
  @Test
  public void test_getTerritoryName() {
    Territory territory = new Territory("testTerritory", "player1");
    assertEquals("testTerritory", territory.getTerritoryName());
  }

}
