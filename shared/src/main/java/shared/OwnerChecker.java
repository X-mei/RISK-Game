package shared;
/**
 * This is the subclass that checks whether the move or attack action is indeed owned by the issuing player.
 */
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
      return "You do not own the source territory.";
    }
  }
}














