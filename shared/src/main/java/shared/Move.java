package shared;

public class Move extends BasicAction {
  public Move(String owner, String dest, String src, Integer count) {
    super(owner, dest, src, count, "M");
  }

  public Move(String name, String s) throws IllegalArgumentException{
    super(name,"M", s);
  }
}












