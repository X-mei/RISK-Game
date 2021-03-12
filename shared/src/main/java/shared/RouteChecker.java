package shared;

import java.util.HashMap;

public class RouteChecker extends RuleChecker{
  HashMap<Territory, Boolean> visited = new HashMap<Territory, Boolean>();
  RouteChecker(RuleChecker next){
    super(next);
  }
  @Override
  public String checkMyRule(BasicAction thisAct, Board board){
    String src_str = thisAct.getSource();
    String dest_str = thisAct.getDestination();
    Territory src = board.getTerritory(src_str);
    Territory dest = board.getTerritory(dest_str);
    if(checkRoute(src,dest)){
      return null;
    }
    else{
       return "No existing route bet ween source and destination!";
    }
  }
  
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





