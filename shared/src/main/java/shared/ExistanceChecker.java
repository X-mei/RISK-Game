package shared;

/**
 * This is the subclass that checks whether the src and dest specified in the action exist.
 */
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
