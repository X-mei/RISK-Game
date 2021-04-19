package shared;

public class SpyUpgradeChecker extends SpecialRuleChecker{
    public SpyUpgradeChecker(SpecialRuleChecker next){
        super(next);
    }

    @Override
    public String checkMyRule(UpgradeAction thisAct, Board theBoard){
        if (theBoard.getTerritory(thisAct.getSource()) == null) {
            return "The selected source do not exist.";
        }
        if (!thisAct.getActionOwner().equals(theBoard.getTerritory(thisAct.getSource()).getOwner())) {
            return "You do not own the source territory.";
        }
        Soldiers startL = theBoard.getTerritory(thisAct.getSource()).getOneUnits(thisAct.getsLevel());
        Integer cnt = theBoard.getTerritoryUnitsCount(thisAct.getSource(), thisAct.getsLevel());
        if (startL == null) {
            return "Invalid starting level.";
        }
        if (cnt < thisAct.getCount()) {
            return "You do not have enough soldier to upgrade.";
        }
        Integer totalCost = thisAct.getCount() * 20;
        Player p = theBoard.getPlayerByName(thisAct.getActionOwner());
        if (totalCost > p.getTechResource()) {
            return "You do not have enough resources to upgrade.";
        }
        else {
            p.updateTempTechResource(-totalCost);
            theBoard.updateTempCount(thisAct.getSource(), thisAct.getsLevel(), thisAct.getCount());
            return null;
        }
    }
}
