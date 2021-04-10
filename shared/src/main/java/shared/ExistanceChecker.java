package shared;

public class ExistanceChecker extends RuleChecker{
    public ExistanceChecker(RuleChecker next){
        super(next);
    }

    @Override
    public String checkMyRule(BasicAction thisAct, Board theBoard) {
        if (theBoard.getTerritory(thisAct.getSource()) == null) {
            return "The selected source do not exist.";
        }
        if(theBoard.getTerritory(thisAct.getDestination()) == null){
            return "The selected destination do not exist.";
        }
        return null;
    }
}
