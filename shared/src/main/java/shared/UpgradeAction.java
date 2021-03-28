package shared;

public class UpgradeAction{
  protected final String src;
  protected final String sLevel;
  protected final int count;
  protected final String fLevel;
  protected final String owner;
  protected final String actionName;

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

  public String getActionOwner() {
    return owner;
  }

  public String getActionName() {
    return actionName;
  }

  public String getSource() {
    return src;
  }

  public String getsLevel() {
    return sLevel;
  }

  public String getfLevel() {
    return fLevel;
  }

  public int getCount() {
    return count;
  }
}










