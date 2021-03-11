package server;

public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next){
    super(next);
  }
  @Override
  public String checkMyRule(Action thisAct){
    Territory src = thisAct.getSrc();
    Territory dest = thisAct.getDest();
    if(src.isNeighbour(dest)){
      return null;
    }
    else{
      return "Destination is not the nerghbour of the source!";
    }
  }
}








