package shared;

import java.util.HashMap;
import java.util.HashSet;


public class UnitChecker extends RuleChecker{
    public UnitChecker(RuleChecker next){
        super(next);
    }

  @Override
  public String checkMyRule (BasicAction thisAct, Board theBoard){
      Integer num = thisAct.getCount();
      Integer sumUnits = 0;
      for(Units un : src.getUnits()){
        sumUnits = sumUnits + un.getCount();
      }
      if(num > sumUnits){
        return "You don't have enough soldiers to move from the source";
        }
      return null;
    }

}







