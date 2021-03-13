package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicSoldiersTest {
  @Test
  public void test_getCount() {
    UnitsFactory f = new UnitsFactory();
    Units u = f.createBasicSoldiers(10);
    assertEquals(10, u.getCount());
  }

  @Test
  public void test_modifyCount() {
    UnitsFactory f = new UnitsFactory();
    Units u = f.createBasicSoldiers(10);
    u.modifyCount(-5);
    assertEquals(5, u.getCount());
    u.modifyCount(2);
    assertEquals(7, u.getCount());
  }

  @Test
  public void test_getName() {
    UnitsFactory f = new UnitsFactory();
    Units u = f.createBasicSoldiers(10);
    assertEquals("Basic Soldiers", u.getName());
  }

  @Test
  public void test_random() {
    BasicSoldiers b = new BasicSoldiers(10);
    assert(b.randomNum() >= 1 && b.randomNum() <= 20);
  }

}