package shared;

/**
 * This interface represents any type of Action in our RISK game.
 */
public interface Action {
  /**
   * 
   * @return
   */
  public String getActionOwner();

  /**
   * 
   * @return
   */
  public String getActionName();

  /**
   * 
   * @return
   */
  public String getSource();

  /**
   * 
   * @return
   */
  public String getDestination();

  /**
   * 
   * @return
   */
  public int getCount();

  /**
   * 
   * @return
   */
  public int getCost();

  /**
   * 
   * @return
   */
  public String getLevelName();

  /**
   * 
   * @param i
   */
  public void modifyCount(int i);

  /**
   * 
   * @param cost
   */
  public void setCost(int cost);

  /**
   * 
   * @return
   */
  public int getFoodConsume();
}













