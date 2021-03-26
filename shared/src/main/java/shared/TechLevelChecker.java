package shared;

public class TechLevelChecker extends RuleChecker {
  public TechLevelChecker(RuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule(UpgradeAction thisAct, Board theBoard) {
    if (theBoard.getTerritory(thisAct.getSource()) == null) {
      return "The selected source do not exist.";
    }
    if (thisAct.getActionOwner() != theBoard.getTerritory(thisAct.getSource()).getOwner()) {
      return "You do not own the source territory.";
    }
    return null;
  }
}



