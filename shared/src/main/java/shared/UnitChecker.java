package shared;


public class UnitChecker extends RuleChecker{
    public UnitChecker(RuleChecker next){
        super(next);
    }

  @Override
  public String checkMyRule (BasicAction thisAct, Board theBoard){
      Integer num = thisAct.getCount();
      Integer sumUnits = 0;
      String src_str = thisAct.getSource();
      Territory src = theBoard.getTerritory(src_str);
      for(Units un : src.getUnits()){
        sumUnits = sumUnits + un.getCount();
      }
      if(num > sumUnits){
        return "You don't have enough soldiers to move from the source";
        }
      return null;
    }

}







