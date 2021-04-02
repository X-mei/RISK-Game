package shared;
/**
 * 
 */
public class ActionFactory {
  /**
   * 
   * @param name
   * @param s
   * @return
   */
  public BasicAction createAttack(String name, String s) {
    return new Attack(name, s);
  }

  /**
   * 
   * @param name
   * @param s
   * @return
   */
  public BasicAction createMove(String name, String s) {
    return new Move(name, s);
  }

  /**
   * 
   * @param name
   * @param s
   * @return
   */
  public UpgradeAction createUpgrade(String name, String s) {
    return new UpgradeAction(name, s);
  }

  /**
   * 
   * @param name
   * @return
   */
  public TechAction createTechUpgrade(String name) {
    return new TechAction(name);
  }
}












