package shared;
/**
 * 
 */
public class NeighborChecker extends RuleChecker {
  NeighborChecker(RuleChecker next){
    super(next);
  }
  
  @Override
  protected String checkMyRule(BasicAction thisAct, Board theBoard) {
    String src = thisAct.getSource();
    String dest = thisAct.getDestination();
    if(theBoard.getTerritory(src) == null){
      return "The selected source do not exist.";
    }
    if(theBoard.getTerritory(dest) == null){
      return "The selected destination do not exist.";
    }
    // System.out.println(thisAct.getActionOwner());
    // System.out.println(theBoard.getTerritory(dest).getOwner());
    if (thisAct.getActionOwner().equals(theBoard.getTerritory(dest).getOwner())) {
      return "You cannot attack your own territory.";
    }
    Player p = theBoard.getPlayerByName(thisAct.getActionOwner());
    if (thisAct.getCount() > p.getTempFoodResource()) {
      return "No enough food to move those soldiers.";
    }
    if(theBoard.getTerritory(src).isNeighbor(theBoard.getTerritory(dest))){
      p.updateTempFoodResource(-thisAct.getCount());
      return null;
    }
    else{
      return "Destination is not the neighbor of the source!";
    }
  }
}








