package server;

import shared.Territory;

public abstract class RuleChecker {
    private final RuleChecker next;
    public RuleChecker(RuleChecker next){
        this.next = next;
    }
  protected abstract String checkMyRule(Action thisAct);
    
    
    public String checkAction(Action thisAct){
        String Msg = CheckMyRule(thisAct);
        if(Msg != null){
            return Msg;
        }
        if(next != null){
            return next.checkAction(thisAct);
        }
        return null;
    }
}

