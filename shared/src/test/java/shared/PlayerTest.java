package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class PlayerTest {
  @Test
  public void test_getNameCodeStatus() {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    assertEquals("player1", player.getName());
    assertEquals(1, player.getCode());
    assertEquals("In-game", player.getStatus());
  }

  @Test
  public void test_formAction() throws IOException {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    BasicAction b1 = player.formAction("M", "A B 10 Lv2");
    assertThrows(IOException.class, ()->player.formAction("M", null));
    assertThrows(IllegalArgumentException.class, ()->player.formAction("ABC", "A B 10"));
    assertEquals("M", b1.getActionName());
    assertEquals("player1", b1.getActionOwner());
    assertEquals(10, b1.getCount());
    assertEquals("A", b1.getSource());
    assertEquals("B", b1.getDestination());
    assertEquals(null, player.formAction("D", ""));
  }

  @Test
  public void test_getTerritoryList() {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    Territory t = new Territory("test", "player1");
    LinkedHashSet<Territory> tSet = new LinkedHashSet<Territory>();
    tSet.add(t);
    player.addTerritory(tSet);
    assertEquals(tSet, player.getTerritoryList());
  }

  @Test
  public void test_formUpgrade() throws IOException {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    UpgradeAction b1 = player.formUpgradeAction("A Lv1 10 Lv2");
    assertThrows(IOException.class, ()->player.formUpgradeAction(null));
    assertThrows(IllegalArgumentException.class, ()->player.formUpgradeAction("A B 10"));
    assertEquals("U", b1.getActionName());
    assertEquals("player1", b1.getActionOwner());
    assertEquals(10, b1.getCount());
    assertEquals("A", b1.getSource());
    assertEquals("Lv1", b1.getsLevel());
    assertEquals("Lv2", b1.getfLevel());
  }

  @Test
  public void test_formTechUpgrade() throws IOException {
    ActionFactory f = new ActionFactory();
    Player player = new Player("player1", 1, f);
    TechAction b1 = player.formTechAction();
    assertEquals("T", b1.getActionName());
    assertEquals("player1", b1.getActionOwner());
  }
}












