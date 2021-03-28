package shared;

public abstract class SpecialRuleChecker {
  private final SpecialRuleChecker next;
  public SpecialRuleChecker(SpecialRuleChecker next){
    this.next = next;
  }
  protected abstract String checkMyRule(UpgradeAction thisAct, Board theBoard);
  public String checkAction(UpgradeAction thisAct, Board theBoard){
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












