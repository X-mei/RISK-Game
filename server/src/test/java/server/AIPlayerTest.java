package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shared.Board;
import shared.Player;
import shared.MapFactory;
import shared.UnitsFactory;

import java.util.ArrayList;

public class AIPlayerTest {
  private Board getTestBoard() {
    MapFactory f = new MapFactory();
    UnitsFactory u = new UnitsFactory();
    Board b = new Board(2, f, u);
    b.singleTerritoryUnitSetup("Dorado", new int[]{0, 5, 10, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hanamura", new int[]{1, 0, 2, 6, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Hollywood", new int[]{2, 10, 5, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Volskaya", new int[]{8, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Ilios", new int[]{10, 0, 0, 0, 0, 0, 0, 0});
    b.singleTerritoryUnitSetup("Junkertown", new int[]{2, 0, 0, 0, 0, 0, 0, 0});
    return b;
  }

  @Test
  public void test_generateUpgrade() {
    Board board = getTestBoard();
    AIPlayer ai = new AIPlayer("a.b.c.d", 12345, null, 0, "Player 1");
    Player p = board.getPlayerByName("King");
    p.setTechLevel(4);
    ai.setPlayername("King");
    ai.addBoard(board);
    board.refreshTemp("King");
    board.refreshTemp("Red");
    ArrayList<String> actions = new ArrayList<>();
    ai.generateUpgradeActions(actions);
    //System.out.println(actions);
  }

  @Test
  public void test_generateMove() {
    Board board = getTestBoard();
    AIPlayer ai = new AIPlayer("a.b.c.d", 12345, null, 0, "Player 1");
    Player p = board.getPlayerByName("King");
    ai.setPlayername("King");
    ai.addBoard(board);
    board.refreshTemp("King");
    board.refreshTemp("Red");
    ArrayList<String> actions = new ArrayList<>();
    ai.generateMoveDecisions(actions, "Hollywood", 20);
    System.out.println(actions);
    //assertEquals(1,2);
  }

}











