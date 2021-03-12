package shared;

public class ActionFactory {
  public BasicAction createAttack(String name, String s) {
    return new Attack(name, s);
  }

  public BasicAction createMove(String name, String s) {
    return new Move(name, s);
  }
}












