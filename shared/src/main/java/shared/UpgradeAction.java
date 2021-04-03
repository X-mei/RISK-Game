package shared;

/**
 * This is the class that contains all the fields and method for the upgrade action.
 */
public class UpgradeAction{
  protected final String src;
  protected final String sLevel;
  protected final int count;
  protected final String fLevel;
  protected final String owner;
  protected final String actionName;

  /**
   * The constructor for this class
   * @param owner the owner of action
   * @param s the string to parse into a action
   * @throws IllegalArgumentException when the input string is not valid for parse
   */
  public UpgradeAction(String owner, String s) throws IllegalArgumentException {
    String[] sections = s.split(" ", 0);
    if (sections.length != 4){
      throw new IllegalArgumentException("Invalid input length.");
    }
    this.owner = owner;
    this.actionName = "U";
    this.src = sections[0];
    this.sLevel = sections[1];
    this.fLevel = sections[3];
    try {
      this.count = Integer.parseInt(sections[2]);
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid unit count.");
    }
  }

  /**
   * @return the owner of the action
   */
  public String getActionOwner() {
    return owner;
  }

  /**
   * @return the name of the action
   */
  public String getActionName() {
    return actionName;
  }

  /**
   * @return the name of the source territory
   */
  public String getSource() {
    return src;
  }

  /**
   * @return the name of the starting level
   */
  public String getsLevel() {
    return sLevel;
  }

  /**
   * @return the name of the final level
   */
  public String getfLevel() {
    return fLevel;
  }

  /**
   * @return the count of unit
   */
  public int getCount() {
    return count;
  }
}










