package shared;

public class UnitMovingChecker extends RuleChecker{
  public UnitMovingChecker(RuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule (BasicAction thisAct, Board theBoard){
    Integer cnt = theBoard.getTerritoryUnitsCount(thisAct.getSource(), thisAct.getLevelName());
    if (cnt == null){
      return "The selected source do not exist.";
    }
    if (thisAct.getCount() <= cnt) {
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getLevelName(), thisAct.getCount());
      if (thisAct.getActionName() == "M") {
        theBoard.updateTempCount(thisAct.getDestination(), thisAct.getLevelName(), -cnt);
      }
      return null;
    }
    else {
      //System.out.println(cnt);
      //System.out.println();
      return "You do not have enough unit to move.";
    }
  }

}











