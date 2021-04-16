package shared;
/**
 * This is the subclass that checks whether the attack or move action have enough unit of the specified level.
 */
public class UnitMovingChecker extends RuleChecker{
  public UnitMovingChecker(RuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule (BasicAction thisAct, Board theBoard){
    Integer cnt = theBoard.getTerritoryUnitsCount(thisAct.getSource(), thisAct.getLevelName());
    if (thisAct.getCount() <= cnt) {
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getLevelName(), thisAct.getCount());
      if (thisAct.getActionName().equals("M")) {
        theBoard.updateTempCount(thisAct.getDestination(), thisAct.getLevelName(), -thisAct.getCount());
      }
      return null;
    }
    else {
      return "You do not have enough unit to move.";
    }
    
    
  }

}











