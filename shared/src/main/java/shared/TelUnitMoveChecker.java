package shared;

public class TelUnitMoveChecker extends RuleChecker{
    public TelUnitMoveChecker(RuleChecker next){super(next);}

    @Override
    public String checkMyRule(BasicAction thisAct, Board theBoard){
        thisAct.setCost(thisAct.getCount()*20);
        return null;
    }
}
