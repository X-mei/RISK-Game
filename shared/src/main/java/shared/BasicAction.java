package shared;

public abstract class BasicAction implements Action {
  protected final String dest;
  protected final String src;
  protected int count;
  protected final String owner;
  protected final String actionName;
  protected final String LevelName;
  
  public BasicAction(String owner, String actionName, String s) throws IllegalArgumentException{
    String[] sections = s.split(" ", 0);
    if (sections.length != 4) {
      throw new IllegalArgumentException("Invalid input length.");
    }
    this.owner = owner;
    this.src = sections[0];
    this.dest = sections[1];
    this.LevelName = sections[3];
    this.actionName = actionName;
    try {
      this.count = Integer.parseInt(sections[2]);
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid unit count.");
    }
  }

  @Override
  public String getActionOwner() {
    return owner;
  }

  @Override
  public String getActionName() {
    return actionName;
  }

  public String getSource() {
    return src;
  }

  public String getDestination() {
    return dest;
  }

  public int getCount() {
    return count;
  }

  public void modifyCount(int i){
    count += i;
  }
  
  public String getLevelName() {
    return LevelName;
  }
  
}













