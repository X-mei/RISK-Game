package shared;

public class Move extends BasicAction {
  public Move(String owner, String dest, String src, Integer count) {
    super(owner, dest, src, count);
  }

  public Move(String name, String s) throws IllegalArgumentException{
    super(name, s);
  }
}







