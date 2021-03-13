package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class MapFactoryTest {
  @Test
  public void test_make2pMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map2 = f.make2pMap();
    LinkedHashSet<Territory> mapForTwoP1 = map2.get("Player1");
    LinkedHashSet<Territory> mapForTwoP2 = map2.get("Player2");
    assertEquals(3, mapForTwoP1.size());
    assertEquals(3, mapForTwoP2.size());

    Iterator<Territory> iter = mapForTwoP1.iterator();
    Territory dorado = iter.next();
    assertEquals("Dorado", dorado.getTerritoryName());
    LinkedHashSet<Territory> doradoNeighs = dorado.getNeighbours();
    assertEquals(2, doradoNeighs.size());
    Iterator<Territory> iter1 = doradoNeighs.iterator();
    Territory hanamuraNeigh = iter1.next();
    assertEquals("Hanamura", hanamuraNeigh.getTerritoryName());
    Territory hollywoodNeigh = iter1.next();
    assertEquals("Hollywood", hollywoodNeigh.getTerritoryName());

    Territory hanamura = iter.next();
    assertEquals("Hanamura", hanamura.getTerritoryName());
    LinkedHashSet<Territory> hanamuraNeighs = hanamura.getNeighbours();
    assertEquals(4, hanamuraNeighs.size());
    Iterator<Territory> iter2 = hanamuraNeighs.iterator();
    assertEquals(dorado, iter2.next());
    assertEquals(hollywoodNeigh, iter2.next());

    Territory hollywood = iter.next();
    assertEquals("Hollywood", hollywood.getTerritoryName());
    LinkedHashSet<Territory> hollywoodNeighs = hollywood.getNeighbours();
    assertEquals(3, hollywoodNeighs.size());
    Iterator<Territory> iter3 = hollywoodNeighs.iterator();
    assertEquals(dorado, iter3.next());
    assertEquals(hanamuraNeigh, iter3.next());

    assertEquals("Player1", dorado.getOwner());
    assertEquals("Player1", hollywood.getOwner());
    assertEquals("Player1", hanamura.getOwner());

    iter = mapForTwoP2.iterator();
    Territory volskaya = iter.next();
    assertEquals("Volskaya", volskaya.getTerritoryName());
    LinkedHashSet<Territory> volskayaNeighs = volskaya.getNeighbours();
    assertEquals(3, volskayaNeighs.size());
    assertEquals(2, volskaya.neighboursByOneOwner().size());

    Territory ilios = iter.next();
    assertEquals("Ilios", ilios.getTerritoryName());
    LinkedHashSet<Territory> iliosNeighs = ilios.getNeighbours();
    assertEquals(4, iliosNeighs.size());
    assertEquals(2, ilios.neighboursByOneOwner().size());

    Territory junkertown = iter.next();
    assertEquals("Junkertown", junkertown.getTerritoryName());
    LinkedHashSet<Territory> junkertownNeighs = junkertown.getNeighbours();
    assertEquals(2, junkertownNeighs.size());
    assertEquals(2, junkertown.neighboursByOneOwner().size());
  }

}
