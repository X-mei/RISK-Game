package shared;
/**
 * This is the factory to create all level of unit.
 */
public class UnitsFactory {
  public Soldiers createLevel1Soldiers(int count) {
    return new Level1Soldiers(count);
  }

  public Soldiers createLevel2Soldiers(int count) {
    return new Level2Soldiers(count);
  }

  public Soldiers createLevel3Soldiers(int count) {
    return new Level3Soldiers(count);
  }

  public Soldiers createLevel4Soldiers(int count) {
    return new Level4Soldiers(count);
  }

  public Soldiers createLevel5Soldiers(int count) {
    return new Level5Soldiers(count);
  }

  public Soldiers createLevel6Soldiers(int count) {
    return new Level6Soldiers(count);
  }

  public Soldiers createLevel7Soldiers(int count) {
    return new Level7Soldiers(count);
  }

  public Soldiers createSpySoldiers(int count) { return new SpySoldiers(count); }

  public Soldiers createTeleportSoldiers(int count) { return new TeleportSoldiers(count); }

}












