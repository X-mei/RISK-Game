package shared;
/**
 * This class contains all fields and actions of a research cloak action.
 */
public class ResearchCloak {
    protected final String owner;
    protected final String actionName;
  
    /**
     * Constructor of this class
     * @param owner is the owner of the action
     */
    public ResearchCloak(String owner){
      this.owner = owner;
      this.actionName = "R";
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
