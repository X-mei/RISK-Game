package shared;

/**
 * This is the subclass that checks whether the attack action is correct. More
 * specifically, is the dest the direct neighbor of src. 
 */
public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next){
    super(next);
  }
  
  @Override
  protected String checkMyRule(BasicAction thisAct, Board theBoard) {
    String src = thisAct.getSource();
    String dest = thisAct.getDestination();
    if(theBoard.getTerritory(src).isNeighbor(theBoard.getTerritory(dest))){
      thisAct.setCost(thisAct.getCount());
      return null;
    }
    else{
      return "Destination is not the neighbor of the source!";
    }
  }
}








