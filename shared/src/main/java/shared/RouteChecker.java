package shared;

import java.util.HashMap;

public class RouteChecker extends RuleChecker{
  RouteChecker(RuleChecker next){
    super(next);
  }
  @Override
  public String checkMyRule(BasicAction thisAct, Board theBoard){
    Territory src = theBoard.getTerritory(thisAct.getSource());
    Territory dest = theBoard.getTerritory(thisAct.getDestination());
    HashMap<Territory, Boolean> visited = new HashMap<>();
    if(checkRoute(src, dest, visited)){
      return null;
    }
    else{
      return "No existing route bet ween source and destination!";
    }
  }
  
  private boolean checkRoute(Territory dest, Territory src, HashMap<Territory, Boolean> visited){
    if(dest == null || src == null){
      return false;
    }
    if(dest == src){
      return true;
    }
    visited.put(src,true);
    for(Territory neighbor : src.neighboursByOneOwner()){
      //src.neighbors:ArrayList for neighbors
      if(!visited.containsKey(neighbor) && checkRoute(neighbor,dest,visited)){
        return true;
      }
  }
  return false;
  }
}





