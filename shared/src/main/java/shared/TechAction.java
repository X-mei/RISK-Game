package shared;
/**
 * This class contains all fields and actions of a tech upgrade action.
 */
public class TechAction {
  protected final String owner;
  protected final String actionName;

  /**
   * Constructor of this class
   * @param owner is the owner of the action
   */
  public TechAction(String owner){
    this.owner = owner;
    this.actionName = "T";
  }

  /**
   * This function returns the owner name of the action
   * @return the name of the action owner
   */
  public String getActionOwner() {
    return owner;
  }

  /**
   * This function returns the name of the action
   * @return the name of the action
   */
  public String getActionName() {
    return actionName;
  }
}
