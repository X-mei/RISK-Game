package shared;

public class Attack extends BasicAction{
  public Attack(String owner, String dest, String src, Integer count) {
    super(owner, dest, src, count, "A");
  }

  public Attack(String owner, String s) throws IllegalArgumentException{
    super(owner, "A", s);
  }
  
}











