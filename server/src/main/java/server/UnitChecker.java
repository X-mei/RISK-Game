package server;

import java.util.HashMap;

import shared.Action;
import shared.BasicAction;
import shared.Territory;

public class UnitChecker extends RuleChecker{
    UnitChecker(RuleChecker next, Territory src, Territory dest){
        super(next, src, dest);
    }

  @Override
  public String checkMyRule (Action thisAct, Territory src, Territory dest){
      Integer num = thisAct.getCount();
      HashMap<String,Integer> unit = src.getUnit();
      Integer unitNum = unit.get("Soldier");
      if(num > unitNum){
        return "You don't have enough soldiers to move from the source";
        }
      return null;
    }

}







