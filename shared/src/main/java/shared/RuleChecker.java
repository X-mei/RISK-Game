package shared;

/**
 * This is the base class for all the rule checker applicable for basic actions.
 */
public abstract class RuleChecker {
  private final RuleChecker next;
  public RuleChecker(RuleChecker next){
    this.next = next;
  }

  /**
   * This is the virtual method that is implemented in the subclass so we can have polymorphisism.
   * @param thisAct is the action to check
   * @param theBoard is the borad correlated with the action
   * @return the string of the error message or null is check passes
   */
  protected abstract String checkMyRule(BasicAction thisAct, Board theBoard);

  /**
   * This is the function to chain all the checker together
   * @param thisAct is the action to check
   * @param theBoard is the borad correlated with the action
   * @return the string of the error message or null is check passes
   */
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

