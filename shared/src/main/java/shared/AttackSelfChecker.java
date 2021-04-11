package shared;

/**
 * This is the subclass that checks whether the dest territory belongs to action owner.
 */
public class AttackSelfChecker extends RuleChecker{
    public AttackSelfChecker(RuleChecker next){
        super(next);
    }

    @Override
    public String checkMyRule(BasicAction thisAct, Board theBoard) {
        String dest = thisAct.getDestination();
        if (thisAct.getActionOwner().equals(theBoard.getTerritory(dest).getOwner())) {
            return "You cannot attack your own territory.";
        }
        return null;
    }
}
