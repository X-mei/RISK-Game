package shared;
/**
 * 
 */
public class TechAction {
  protected final String owner;
  protected final String actionName;

  /**
   * 
   * @param owner
   */
  public TechAction(String owner){
    this.owner = owner;
    this.actionName = "T";
  }

  /**
   * 
   * @return
   */
  public String getActionOwner() {
    return owner;
  }

  /**
   * 
   * @return
   */
  public String getActionName() {
    return actionName;
  }
}
