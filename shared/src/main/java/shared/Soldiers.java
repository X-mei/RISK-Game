package shared;

import java.util.Random;
/**
 * The base type for all normal soldiers
 */
public abstract class Soldiers implements Units{
  protected final String name;
  protected final int bonus;
  protected final int cost;
  protected final int techReq;
  protected int count;
  protected final Random randomGenerator;
  
  /**
   * Constructor of base class Soldiers
   * @param name name of soldiers
   * @param count the amount of unit
   * @param bonus the bonus applied to this level of soldier when battling
   * @param cost the cost to upgrade one unit from lv1 to this level
   * @param techReq the tech level requirement to update to this level
   */
  public Soldiers(String name, int count, int bonus, int cost, int techReq){
    this.name = name;
    this.count = count;
    this.randomGenerator = new Random();
    this.bonus = bonus;
    this.cost = cost;
    this.techReq = techReq;
  }

  @Override
  public Integer getCount(){
    return count;
  }

  @Override
  public void updateCount(int num){
    count = num;
  }

  @Override
  public String getName(){
    return this.name;
  }

  @Override
  public int getBonus() {
    return this.bonus;
  }

  @Override
  public int getCost() {
    return this.cost;
  }

  @Override
  public int getTechReq() {
    return this.techReq;
  }
  
  /**
   * generate a random number used in attack
   * @return the random number
   */
  public Integer randomNum(){
    return randomGenerator.nextInt((20 - 1) + 1) + 1;
  }

}













