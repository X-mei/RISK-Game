package shared;

public class UpgradeChecker extends SpecialRuleChecker{
  public UpgradeChecker(SpecialRuleChecker next){
    super(next);
  }

  @Override
  public String checkMyRule(UpgradeAction thisAct, Board theBoard){
    if (theBoard.getTerritory(thisAct.getSource()) == null) {
      return "The selected source do not exist.";
    }
    if (thisAct.getActionOwner() != theBoard.getTerritory(thisAct.getSource()).getOwner()) {
      return "You do not own the source territory.";
    }
    Soldiers startL = theBoard.getTerritory(thisAct.getSource()).getOneUnits(thisAct.getsLevel());
    Soldiers endL = theBoard.getTerritory(thisAct.getSource()).getOneUnits(thisAct.getfLevel());
    if (startL == null || endL == null) {
      return "Invalid starting or ending level.";
    }
    if (startL.getCount() < thisAct.getCount()) {
      return "You do not have enough soldier to upgrade.";
    }
    Integer totalCost = thisAct.getCount() * (endL.getCost() - startL.getCost());
    Player p = theBoard.getPlayer(thisAct.getActionOwner());
    if (totalCost > p.getTechResources()) {
      return "You do not enough resources to upgrade.";
    }
    else {
      p.updateTempTechResource(-totalCost);
      theBoard.updateTempTechPoint(totalCost);
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getsLevel(), thisAct.getCount());
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getfLevel(), -thisAct.getCount());
      return null;
    }
  }
}












