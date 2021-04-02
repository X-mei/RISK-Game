package shared;

/**
 * This interface represents any basic type of Action in our RISK game.
 */
public interface Action {
  /**
   * @return the name of the owner of action
   */
  public String getActionOwner();

  /**
   * @return the name of the action
   */
  public String getActionName();

  /**
   * @return the name of the source
   */
  public String getSource();

  /**
   * @return the name of destination
   */
  public String getDestination();

  /**
   * @return the count
   */
  public int getCount();

  /**
   * @return the food cost of action
   */
  public int getCost();

  /**
   * @return the name of the level to move
   */
  public String getLevelName();

  /**
   * @param the new value as count
   */
  public void modifyCount(int i);

  /**
   * @param the new value as cost
   */
  public void setCost(int cost);

  /**
   * @return the food consumption
   */
  public int getFoodConsume();
}













