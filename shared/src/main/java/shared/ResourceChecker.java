package shared;

public class ResourceChecker extends RuleChecker{
    ResourceChecker(RuleChecker next){
        super(next);
    }

    @Override
    protected String checkMyRule(BasicAction thisAct, Board theBoard) {
        Player p = theBoard.getPlayerByName(thisAct.getActionOwner());
        if (thisAct.getFoodConsume() > p.getTempFoodResource()) {
            return "No enough food to move those soldiers.";
        }
        p.updateTempFoodResource(-thisAct.getFoodConsume());
        return null;
    }
}







