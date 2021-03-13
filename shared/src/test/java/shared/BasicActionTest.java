package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicActionTest {
  @Test
  public void test_getActionOwner() {
    ActionFactory f = new ActionFactory();
    BasicAction a = f.createMove("player1", "A B 2");
    assertEquals("player1", a.getActionOwner());
    BasicAction a1 = f.createAttack("player2", "C D 4");
    assertEquals("player2", a1.getActionOwner());
  }

  @Test
  public void test_getActionName() {
    ActionFactory f = new ActionFactory();
    BasicAction a = f.createMove("player1", "A B 2");
    assertEquals("M", a.getActionName());
    BasicAction a1 = f.createAttack("player2", "C D 4");
    assertEquals("A", a1.getActionName());
  }

  @Test
  public void test_getSrcDest() {
    ActionFactory f = new ActionFactory();
    BasicAction a = f.createMove("player1", "A B 2");
    assertEquals("A", a.getSource());
    assertEquals("B", a.getDestination());
    assertEquals(2, a.getCount());

    BasicAction a1 = f.createMove("player1", "start end 10");
    assertEquals("start", a1.getSource());
    assertEquals("end", a1.getDestination());
    assertEquals(10, a1.getCount());

    assertThrows(IllegalArgumentException.class, ()->f.createMove("player1", "start 10"));
    assertThrows(IllegalArgumentException.class, ()->f.createMove("player1", "start"));
    assertThrows(IllegalArgumentException.class, ()->f.createMove("player1", "start end 10 exit"));
    assertThrows(IllegalArgumentException.class, ()->f.createMove("player1", "start end start"));
  }

}
