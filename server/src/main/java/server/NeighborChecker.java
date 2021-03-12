package server;

import shared.Action;
import shared.Territory;

public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next, Territory src, Territory dest){
    super(next, src, dest);
  }
  @Override
  protected String checkMyRule(Action thisAct, Territory src, Territory dest) {
    if(src.isNeighbor(dest)){
      return null;
    }
    else{
      return "Destination is not the nerghbour of the source!";
    }
  }
}








