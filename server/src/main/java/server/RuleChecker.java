package server;
import shared.Action;
import shared.Territory;


public abstract class RuleChecker {
    private final RuleChecker next;
    final Territory src;
    final Territory dest;
    public RuleChecker(RuleChecker next, Territory srcInit, Territory destInit){
        this.next = next;
        this.src = srcInit;
        this.dest = destInit;
    }
  protected abstract String checkMyRule(Action thisAct, Territory src, Territory dest);
    
    
    public String checkAction(Action thisAct){
        String Msg = checkMyRule(thisAct, src, dest);
        if(Msg != null){
            return Msg;
        }
        if(next != null){
            return next.checkAction(thisAct);
        }
        return null;
    }
}

