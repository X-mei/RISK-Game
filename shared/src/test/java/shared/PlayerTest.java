package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
    BasicAction b1 = player.formAction("M", "A B 10");
    assertThrows(IOException.class, ()->player.formAction("M", null));
    assertThrows(IllegalArgumentException.class, ()->player.formAction("ABC", "A B 10"));
    
    assertEquals("M", b1.getActionName());
    assertEquals("player1", b1.getActionOwner());
    assertEquals(10, b1.getCount());
    assertEquals("A", b1.getSource());
    assertEquals("B", b1.getDestination());
  }

}






