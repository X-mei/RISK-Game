package shared;

public abstract class BasicAction implements Action {
  protected final String dest;
  protected final String src;
  protected final Integer count;
  protected final String owner;

  public BasicAction(String owner, String dest, String src, Integer count){
    this.dest = dest;
    this.src = src;
    this.owner = owner;
    this.count = count;
  }

  public BasicAction(String owner, String s) throws IllegalArgumentException{
    String[] sections = s.split(" ", 0);
    if (sections.length != 3) {
      throw new IllegalArgumentException("Invalid input length.");
    }
    this.owner = owner;
    this.src = sections[0];
    this.dest = sections[1];
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
}













