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
    Integer cnt = theBoard.getTerritoryUnitsCount(thisAct.getSource(), thisAct.getsLevel());
    if (startL == null || endL == null) {
      return "Invalid starting or ending level.";
    }
    
    if (cnt < thisAct.getCount()) {
      return "You do not have enough soldier to upgrade.";
    }
    Integer totalCost = thisAct.getCount() * (endL.getCost() - startL.getCost());
    Player p = theBoard.getPlayerByName(thisAct.getActionOwner());
    if (endL.getTechReq()>p.getTechLevel()){
      return "You tech level is not high enough.";
    }
    if (totalCost > p.getTechResource()) {
      return "You do not have enough resources to upgrade.";
    }
    else {
      p.updateTempTechResource(-totalCost);
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getsLevel(), thisAct.getCount());
      theBoard.updateTempCount(thisAct.getSource(), thisAct.getfLevel(), -thisAct.getCount());
      return null;
    }
  }
}












