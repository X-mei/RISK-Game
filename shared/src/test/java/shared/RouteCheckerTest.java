package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RouteCheckerTest {
  @Test
  public void test_RouteRule() {
    Board b = new Board(2, new MapFactory(), new UnitsFactory());
    RuleChecker c = new NeighborChecker(null);
    BasicAction b1 = new Attack("King", "Dorado B 10");
    BasicAction b2 = new Attack("King", "Hanamura Hanamura 5");
    BasicAction b3 = new Attack("King", "Hanamura Ilios 9");
    BasicAction b4 = new Attack("King", "Hanamura Junkertown 5");
    BasicAction b5 = new Attack("King", "A Junkertown 5");
    
  }

}
