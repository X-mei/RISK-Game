package shared;

public class AttackNextObjV2{
    private int attackSoldierLevel;
    private int bonus;

    public AttackNextObjV2(int attackSoldierLevel, int bonus){
      this.attackSoldierLevel = attackSoldierLevel;
      this.bonus = bonus;
    }

    // @Override
    // public int compareTo(AttackNextObjV2 compareAttack) {
    //     int compareLevel=((AttackNextObjV2)compareAttack).getAttackSoldierLevel();
    //     /* For Ascending order*/
    //     return compareLevel - this.attackSoldierLevel;

    //     /* For Descending order do like this */
    //     //return compareage-this.studentage;
    // }

    @Override
    public String toString() {
        return "[ attack soldier level = " + attackSoldierLevel + " ]";
    }

    public int getAttackSoldierLevel() {
        return attackSoldierLevel;
    }

    public void setAttackSoldierLevel(int attackSoldierLevel) {
        this.attackSoldierLevel = attackSoldierLevel;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }


}
