package shared;

/**
 * This is the class that contains all the fields and method for the cloaking action.
 */
public class CloakAction {
    protected final String src;
    protected final String owner;
    protected final String actionName;

    /**
     * The constructor for this class
     * @param owner the owner of action
     * @param s he string to parse into a action
     */
    public CloakAction(String owner, String s){
        String[] sections = s.split(" ", 0);
        if (sections.length != 1){
            throw new IllegalArgumentException("Invalid input length.");
        }
        this.owner = owner;
        this.actionName = "C";
        this.src = sections[0];
        
    }


    /**
     * get the target cloaking territory
     * @return the territory name
     */
    public String getSource() {
        return src;
    }

    /**
     * get the owner of the action
     * @return te owner's name
     */
    public String getActionOwner() {
        return owner;
    }

    /**
     * get the action's name
     * @return the first letter of the action name
     */
    public String getActionName() {
        return actionName;
    }

}
