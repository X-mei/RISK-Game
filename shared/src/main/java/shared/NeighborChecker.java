package shared;

public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next, Territory src, Territory dest){
    super(next, src, dest);
  }
  @Override
  protected String checkMyRule(BasicAction thisAct, Territory src, Territory dest) {
    if(src.isNeighbor(dest)){
      return null;
    }
    else{
      return "Destination is not the nerghbour of the source!";
    }
  }
}








