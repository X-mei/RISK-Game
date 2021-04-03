package shared;
/**
 * This is the interface class for all unit class
 */
public interface Units {
  /**
   * @return the count of unit
   */
  public Integer getCount();

  /**
   * @param num is the num to add to the current count
   */
  public void updateCount(int num);

  /**
   * @return the name of the units
   */
  public String getName();

  /**
   * @return the bonus when fighting
   */
  public int getBonus();

  /**
   * @return the total cost to upgrade to this level of soldier
   */
  public int getCost();

  /**
   * @return the tech level requirement to upgrade to this level
   */
  public int getTechReq();
}
