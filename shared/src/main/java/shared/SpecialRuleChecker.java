package shared;
/**
 * 
 */
public abstract class SpecialRuleChecker {
  private final SpecialRuleChecker next;
  public SpecialRuleChecker(SpecialRuleChecker next){
    this.next = next;
  }
  /**
   * 
   * @param thisAct
   * @param theBoard
   * @return
   */
  protected abstract String checkMyRule(UpgradeAction thisAct, Board theBoard);

  /**
   * 
   * @param thisAct
   * @param theBoard
   * @return
   */
  public String checkAction(UpgradeAction thisAct, Board theBoard){
    String Msg = checkMyRule(thisAct, theBoard);
    if(Msg != null){
      return Msg;
    }
    else {
      return null;
    }
    /*
    if(next != null){
      return next.checkAction(thisAct, theBoard);
    }
    */
    //return null;
  }
}












