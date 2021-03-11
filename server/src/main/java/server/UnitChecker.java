package server;

import java.util.HashMap;

import javax.swing.Action;

import shared.Territory;

public class UnitChecker extends RuleChecker{
    UnitChecker(RuleChecker next){
        super(next);
    }


  @Override
  public String checkMyRule(Action thisAct){
      Integer num = thisAct.getCount();
      Territory src = thisAct.getSrc();
      HashMap<String, Integer> unit = src.getUnit();
      Integer unitNum = unit.get("Soldier");
      if(num > unitNum){
        return "You don't have enough soldiers to move from the source";
        }
      return null;
    }
}







