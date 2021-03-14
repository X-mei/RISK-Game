package shared;

import java.util.Random;

public abstract class Soldiers implements Units{
  protected final String name;
  protected Integer count;
  protected final Random randomGenerator;
  
  public Soldiers(String name, Integer count){
    this.name = name;
    this.count = count;
    this.randomGenerator = new Random();
  }

  @Override
  public Integer getCount(){
    return count;
  }

  public void updateCount(int num){
    count = num;
  }
  
  @Override
  public void modifyCount(Integer modiBy){
    count += modiBy;
  }

  @Override
  public String getName(){
    return this.name;
  }
  
  public Integer randomNum(){
    return randomGenerator.nextInt((20 - 1) + 1) + 1;
  }

}













