package shared;

import java.util.HashSet;
/**
 * This is the subclass that checks whether the move action is correct. More specifically, is there a path from src to dest. It also update the minimum cost field in the action.
 */
public class RouteChecker extends RuleChecker{
  private int minCost;
  public RouteChecker(RuleChecker next){
    super(next);
  }
  
  @Override
  public String checkMyRule(BasicAction thisAct, Board theBoard){
    minCost = Integer.MAX_VALUE;
    Territory src = theBoard.getTerritory(thisAct.getSource());
    Territory dest = theBoard.getTerritory(thisAct.getDestination());
    HashSet<Territory> visited = new HashSet<>();
    Integer cost = 0;
    checkRoute(src, dest, cost, visited);
    Integer totalCost = thisAct.getCount() * minCost;
    if(minCost != Integer.MAX_VALUE){
      thisAct.setCost(totalCost);
      return null;
    }
    else{
      return "No existing route between source and destination!";
    }
  }
  
  /**
   * This is the helper function to do a recursive check to see if the route exist and what is the minimum cost
   * @param src the source territory
   * @param dest the destination territory to try to get to
   * @param cost the cost of the current path
   * @param visited the set of visited territory
   */
  private void checkRoute(Territory src, Territory dest, Integer cost, HashSet<Territory> visited){
    visited.add(src);
    cost += src.getSize();
    if(src.equals(dest)){
      cost -= src.getSize();
      minCost = Math.min(minCost, cost);
      visited.remove(src);
      return;
    }
    for(Territory neighbor : src.neighboursByOneOwner()){
      if(!visited.contains(neighbor)){
        checkRoute(neighbor, dest, cost, visited);
      }
    }
    cost -= src.getSize();
    visited.remove(src);
  }
}
 






