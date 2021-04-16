package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shared.Board;
import shared.MapFactory;
import shared.UnitsFactory;

import java.util.ArrayList;

public class AIPlayerTest {
  private Board getTestBoard() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    b.singleTerritoryUnitSetup("Dorado", new int[]{10, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hanamura", new int[]{5, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[]{5, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{8, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{10, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Junkertown", new int[]{2, 0, 0, 0, 0, 0, 0, 0});
    return b;
  }

  @Test
  public void test_generateUpgrade() {
    Board board = getTestBoard();
    AIPlayer ai = new AIPlayer("a.b.c.d", 12345, null, 0, "Player 1");
    ai.setPlayername("King");
    ai.addBoard(board);
    board.refreshTemp("King");
    board.refreshTemp("Red");
    ArrayList<String> actions = new ArrayList<>();
    ai.generateUpgradeDecisions(actions, "Hollywood", 3);
    System.out.println(actions);
  }

}











