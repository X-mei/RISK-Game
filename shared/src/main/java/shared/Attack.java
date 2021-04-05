package shared;
/**
 * This class inherit from class basic action. Contains all fields and actions of a move action. The construction of this class is done by calling the base class's constructor.
 */
public class Attack extends BasicAction{
  public Attack(String owner, String s) throws IllegalArgumentException{
    super(owner, "A", s);
  }
  
}











