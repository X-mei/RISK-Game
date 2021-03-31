package shared;

public class ActionFactory {
  public BasicAction createAttack(String name, String s) {
    return new Attack(name, s);
  }

  public BasicAction createMove(String name, String s) {
    return new Move(name, s);
  }

  public UpgradeAction createUpgrade(String name, String s) {
    return new UpgradeAction(name, s);
  }

  public TechAction createTechUpgrade(String name) {
    return new TechAction(name);
  }
}












