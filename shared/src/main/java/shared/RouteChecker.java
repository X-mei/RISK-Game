package shared;

import java.util.HashMap;

public class RouteChecker extends RuleChecker{
  RouteChecker(RuleChecker next, Territory srcInit, Territory destInit){
    super(next, srcInit, destInit);
  }
  @Override
  public String checkMyRule(BasicAction thisAct, Territory src, Territory dest){
    if(checkRoute(src,dest)){
      return null;
    }
    else{
       return "No existing route bet ween source and destination!";
    }
  }
  HashMap<Territory, Boolean> visited = new HashMap<>();
  private boolean checkRoute(Territory dest, Territory src){
    if(dest == null || src == null){
      return false;
    }
    if(dest == src){
      return true;
    }
    visited.put(src,true);
    for(Territory neighbor : src.neighboursByOneOwner()){
      //src.neighbors:ArrayList for neighbors
      if(!visited.containsKey(neighbor) && checkRoute(neighbor,dest)){
        return true;
      }
  }
  return false;
  }
}





