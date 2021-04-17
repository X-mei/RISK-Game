package shared;
/**
 * This class is the factory to create different type of actions.
 */
public class ActionFactory {
  /**
   * 
   * @param name is the owner of the action
   * @param s is the string to parse to get the fields
   * @return a Attack action as BasicAction
   */
  public BasicAction createAttack(String name, String s) {
    return new Attack(name, s);
  }

  /**
   * 
   * @param name is the owner of the action
   * @param s is the string to parse to get the fields
   * @return a Move action as BasicAction
   */
  public BasicAction createMove(String name, String s) {
    return new Move(name, s);
  }

  /**
   * 
   * @param name is the owner of the action
   * @param s is the string to parse to get the fields
   * @return a UpgradeAction
   */
  public UpgradeAction createUpgrade(String name, String s) {
    return new UpgradeAction(name, s);
  }

  /**
   * 
   * @param name is the owner of the action
   * @return a TechAction
   */
  public TechAction createTechUpgrade(String name) {
    return new TechAction(name);
  }

  /**
   * 
   * @param name is the owner of the action
   * @return a ResearchCloakAction
   */
  public ResearchCloak createResearchCloak(String name) {
    return new ResearchCloak(name);
  }

  /**
   * 
   * @param name is the owner of the action
   * @return a CloakAction
   */
  public CloakAction createCloak(String name, String s) {
    return new CloakAction(name, s);
  }
}












