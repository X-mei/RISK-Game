package shared;
/**
 * 
 */
public class UpgradeAction{
  protected final String src;
  protected final String sLevel;
  protected final int count;
  protected final String fLevel;
  protected final String owner;
  protected final String actionName;

  /**
   * 
   * @param owner
   * @param s
   * @throws IllegalArgumentException
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
/**
 * 
 * @return
 */
  public String getSource() {
    return src;
  }

  /**
   * 
   * @return
   */
  public String getsLevel() {
    return sLevel;
  }

  /**
   * 
   * @return
   */
  public String getfLevel() {
    return fLevel;
  }

  /**
   * 
   * @return
   */
  public int getCount() {
    return count;
  }
}










