package shared;

import java.util.HashMap;

public class RouteChecker extends RuleChecker{
  RouteChecker(RuleChecker next){
    super(next);
  }
  @Override
  public String checkMyRule(BasicAction thisAct, Board theBoard){
    Territory src = theBoard.getTerritory(thisAct.getSource());
    if (src == null){
      return "The selected source do not exist.";
    }
    Territory dest = theBoard.getTerritory(thisAct.getDestination());
    if (dest == null){
      return "The selected destination do not exist.";
    }
    HashMap<Territory, Boolean> visited = new HashMap<>();
    Integer cost = 0;
    if(checkRoute(src, dest, cost, visited)){
      if (cost > theBoard.getFoodAmount()) {
        return "No enough food to move those soldiers.";
      }
      else {
        return null;
      }
    }
    else{
      return "No existing route between source and destination!";
    }
  }
  
  private boolean checkRoute(Territory src, Territory dest, Integer cost, HashMap<Territory, Boolean> visited){
    if(src == dest){
      return true;
    }
    visited.put(src,true);
    cost += src.getSize();
    for(Territory neighbor : src.neighboursByOneOwner()){
      //src.neighbors:ArrayList for neighbors
      if(!visited.containsKey(neighbor) && checkRoute(neighbor,dest,cost,visited)){
        return true;
      }
    }
    cost -= src.getSize();
    visited.put(src,false);
    return false;
  }
}
 






