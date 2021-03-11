package server;

import java.util.HashMap;

import javax.swing.Action;

import shared.Territory;

public class RouteChecker extends RuleChecker{
  RouteChecker(RuleChecker next){
    super(next);
  }
  @Override
  public String checkMyRule(Action thisAct){
    if(checkRoute(thisAct.getDest,thisAct.getSrc)){
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
    for(Territory neighbor : src.neighbors){
      //src.neighbors:ArrayList for neighbors
      if(!visited.containsKey(neighbor) && checkRoute(neighbor,dest)){
        return true;
      }
  }
  return false;
  }
}





