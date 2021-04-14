package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shared.Board;
import shared.MapFactory;
import shared.UnitsFactory;

public class AIPlayerTest {
  private Board getTestBoard() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    return b;
  }

  @Test
  public void test_generateUpgrade() {
    Board board = getTestBoard();
    AIPlayer ai = new AIPlayer("a.b.c.d", 12345, null, 0, "Player 1");

  }

}











