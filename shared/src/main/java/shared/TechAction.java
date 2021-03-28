package shared;

public class TechAction {
  protected final String owner;
  protected final String actionName;

  public TechAction(String owner, String s){
    this.owner = owner;
    this.actionName = "T";
  }

  public String getActionOwner() {
    return owner;
  }

  public String getActionName() {
    return actionName;
  }
}
