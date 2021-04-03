package shared;
/**
 * This is the base class for all the special rule chekcer.
 */
public abstract class SpecialRuleChecker {
  private final SpecialRuleChecker next;
  public SpecialRuleChecker(SpecialRuleChecker next){
    this.next = next;
  }
  /**
   * This is the virtual method that is implemented in the subclass so we can have polymorphisism.
   * @param thisAct is the action to check
   * @param theBoard is the board correlated with the action
   * @return the string of the error message or null is check passes
   */
  protected abstract String checkMyRule(UpgradeAction thisAct, Board theBoard);

  /**
   * This is the function to chain all the checker together.
   * @param thisAct thisAct is the action to check
   * @param theBoard theBoard is the board correlated with the action
   * @return the string of the error message or null is check passes
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












