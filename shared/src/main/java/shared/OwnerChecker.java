package shared;

public class OwnerChecker extends RuleChecker {
  public OwnerChecker(RuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule(BasicAction thisAct, Board theBoard) {
    if (thisAct.getActionOwner() == theBoard.getTerritory(thisAct.getSource()).getOwner()) {
      return null;
    }
    else {
      return "You do not own the source destination.";
    }
  }
}













