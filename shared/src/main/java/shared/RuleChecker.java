package shared;

public abstract class RuleChecker {
  private final RuleChecker next;
  public RuleChecker(RuleChecker next){
    this.next = next;
  }
  protected abstract String checkMyRule(BasicAction thisAct, Board theBoard);
  public String checkAction(BasicAction thisAct, Board theBoard){
    String Msg = checkMyRule(thisAct, theBoard);
    if(Msg != null){
      return Msg;
    }
    if(next != null){
      return next.checkAction(thisAct, theBoard);
    }
    return null;
  }
}

