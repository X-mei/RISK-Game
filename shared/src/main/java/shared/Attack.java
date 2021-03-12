package shared;

public class Attack extends BasicAction{
  public Attack(String owner, String dest, String src, Integer count) {
    super(owner, dest, src, count, "A");
  }

  public Attack(String name, String s) throws IllegalArgumentException{
    super(name, "A", s);
  }
  
}










