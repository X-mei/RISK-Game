package shared;

import java.util.Random;
/**
 * 
 */
public abstract class Soldiers implements Units{
  protected final String name;
  protected final int bonus;
  protected final int cost;
  protected final int techReq;
  protected int count;
  protected final Random randomGenerator;
  
  /**
   * 
   * @param name
   * @param count
   * @param bonus
   * @param cost
   * @param techReq
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













