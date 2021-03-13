package shared;

public class UnitMovingChecker extends RuleChecker{
  public UnitMovingChecker(RuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule (BasicAction thisAct, Board theBoard){
    Integer cnt = theBoard.getTerritoryUnitsCount(thisAct.getSource());
    if (cnt == null){
      return "The selected source do not exist.";
    }
    if (thisAct.getCount() < cnt) {
      theBoard.updateTempCount(thisAct.getSource(), cnt);
      return null;
    }
    else {
      return "You do not have enough unit to move.";
    }
  }

}











