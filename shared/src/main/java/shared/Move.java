package shared;
/**
 * This class inherit from class basic action. Contains all fields and actions of a attack action. The construction of this class is done by calling the base class's constructor.
 */
public class Move extends BasicAction {
  public Move(String owner, String s) throws IllegalArgumentException{
    super(owner,"M", s);
  }
}












