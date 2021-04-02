package shared;

import java.util.HashMap;

public class SoldierReferenceTable {
    private HashMap<String, Soldiers> referenceTable;

    public SoldierReferenceTable(){
        HashMap<String, Soldiers> tempTable = new HashMap<>();
        tempTable.put("Lv1", new Level1Soldiers(1));
        tempTable.put("Lv2", new Level2Soldiers(1));
        tempTable.put("Lv3", new Level3Soldiers(1));
        tempTable.put("Lv4", new Level4Soldiers(1));
        tempTable.put("Lv5", new Level5Soldiers(1));
        tempTable.put("Lv6", new Level6Soldiers(1));
        tempTable.put("Lv7", new Level7Soldiers(1));
        this.referenceTable = tempTable;
    }

    public HashMap<String, Soldiers> getSoldierReferenceTable(){
        return referenceTable;
    }


    public int getCostByName(String name){
        Soldiers s = referenceTable.get(name);
        int cost = s.getCost();
        return cost;
    }

    
}
