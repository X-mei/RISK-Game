package shared;

public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next){
    super(next);
  }
  @Override
  protected String checkMyRule(BasicAction thisAct, Board theBoard) {
    String src = thisAct.getSource();
    String dest = thisAct.getDestination();
    
    if(theBoard.getTerritory(src).isNeighbor(theBoard.getTerritory(dest))){
      return null;
    }
    else{
      return "Destination is not the nerghbour of the source!";
    }
  }
}








