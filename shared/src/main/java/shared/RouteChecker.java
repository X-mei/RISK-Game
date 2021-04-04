package shared;

import java.util.HashSet;

public class RouteChecker extends RuleChecker{
  private int minCost;
  RouteChecker(RuleChecker next){
    super(next);
  }
  
  @Override
  public String checkMyRule(BasicAction thisAct, Board theBoard){
    minCost = Integer.MAX_VALUE;
    Territory src = theBoard.getTerritory(thisAct.getSource());
    if (src == null){
      return "The selected source do not exist.";
    }
    Territory dest = theBoard.getTerritory(thisAct.getDestination());
    if (dest == null){
      return "The selected destination do not exist.";
    }
    HashSet<Territory> visited = new HashSet<Territory>();
    Integer cost = 0;
    Player p = theBoard.getPlayerByName(thisAct.getActionOwner());
    checkRoute(src, dest, cost, visited);
    Integer totalCost = thisAct.getCount() * minCost;
    if(minCost != Integer.MAX_VALUE){
      if (totalCost > p.getTempFoodResource()) {
        return "No enough food to move those soldiers.";
      }
      else {
        p.updateTempFoodResource(-totalCost);
        thisAct.setCost(totalCost);
        return null;
      }
    }
    else{
      return "No existing route between source and destination!";
    }
  }
  
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
    return;
  }
}
 






