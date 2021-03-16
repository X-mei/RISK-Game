package shared;

import java.util.Random;

public abstract class Soldiers implements Units{
  protected final String name;
  protected int count;
  protected final Random randomGenerator;
  
  public Soldiers(String name, int count){
    this.name = name;
    this.count = count;
    this.randomGenerator = new Random();
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
  
  public Integer randomNum(){
    return randomGenerator.nextInt((20 - 1) + 1) + 1;
  }

}













