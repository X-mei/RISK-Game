package shared;

/**
 * This interface represents any type of Action in our RISK game.
 */
public interface Action {
  
  public String getActionOwner();

  public String getActionName();

  public String getSource();

  public String getDestination();

  public int getCount();

  public int getCost();

  public String getLevelName();

  public void modifyCount(int i);

  public void setCost(int cost);

  public int getFoodConsume();
}













