package shared;

/**
 * This interface represents any basic type of Action in our RISK game.
 */
public interface Action {
  /**
   * @return the name of the owner of action
   */
  String getActionOwner();

  /**
   * @return the name of the action
   */
  String getActionName();

  /**
   * @return the name of the source
   */
  String getSource();

  /**
   * @return the name of destination
   */
  String getDestination();

  /**
   * @return the count
   */
  int getCount();

  /**
   * @return the name of the level to move
   */
  String getLevelName();

  /**
   * @param i new value as count
   */
  void modifyCount(int i);

  /**
   * @param cost the new value as cost
   */
  void setCost(int cost);

  /**
   * @return the food consumption
   */
  int getFoodConsume();
}













