package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SoldierReferenceTableTest {
  @Test
  public void test_ReferenceTable() {
    SoldierReferenceTable s1 = new SoldierReferenceTable();
    //SoldierReferenceTable s2 = new SoldierReferenceTable();
   s1.getSoldierReferenceTable();
    assertEquals(30, s1.getCostByName("Lv4"));
  }

}
