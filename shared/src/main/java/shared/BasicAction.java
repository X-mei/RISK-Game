package shared;

public abstract class BasicAction implements Action {
  protected final String dest;
  protected final String src;
  protected int count;
  protected final String owner;
  protected final String actionName;
  protected final String LevelName;
  protected int cost;
  
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
    this.cost = 0;
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

  @Override
  public String getSource() {
    return src;
  }

  @Override
  public String getDestination() {
    return dest;
  }

  @Override
  public int getCount() {
    return count;
  }


  @Override
  public int getCost() {
    return cost;
  }

  @Override
  public void modifyCount(int i){
    count += i;
  }
  
  @Override
  public String getLevelName() {
    return LevelName;
  }

  @Override
  public void setCost(int cost) {
    this.cost = cost;
  }

  @Override
  public int getFoodConsume(){
    return cost;
  }
}













