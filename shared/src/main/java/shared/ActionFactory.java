package shared;

public class ActionFactory {
  public Action createAttack(String name, String s) {
    return new Attack(name, s);
  }

  public Action createMove(String name, String s) {
    return new Move(name, s);
  }
}












