package shared;

public class SpyUnitMovingChecker extends RuleChecker{
    SpyUnitMovingChecker(RuleChecker next){
        super(next);
    }

    @Override
    protected String checkMyRule(BasicAction thisAct, Board theBoard){
      int SpyCount = theBoard.getSpyCountByName(thisAct.getActionOwner(), thisAct.getSource());
        if (SpyCount < thisAct.getCount()){
            return "You do not have enough spy to move.";
        }
        return null;
    }
}
