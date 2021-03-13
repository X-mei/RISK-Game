package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class MapFactoryTest {
  @Test
  public void test_getMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map2 = f.getMap(2);
    assertEquals(2, map2.size());
    LinkedHashSet<Territory> mapForTwoP1 = map2.get("King");
    assertEquals(3, mapForTwoP1.size());

    HashMap<String, LinkedHashSet<Territory>>  map3 = f.getMap(3);
    assertEquals(3, map3.size());
    LinkedHashSet<Territory> mapForThreeP1 = map3.get("King");
    assertEquals(3, mapForThreeP1.size());

    HashMap<String, LinkedHashSet<Territory>>  map4 = f.getMap(4);
    assertEquals(4, map4.size());
    LinkedHashSet<Territory> mapForFourP1 = map4.get("King");
    assertEquals(2, mapForFourP1.size()); 
    
    HashMap<String, LinkedHashSet<Territory>>  map5 = f.getMap(5);
    assertEquals(5, map5.size());
    LinkedHashSet<Territory> mapForFiveP1 = map5.get("King");
    assertEquals(2, mapForFiveP1.size()); 

    assertThrows(IllegalArgumentException.class, ()->f.getMap(6));
  }

  @Test
  public void test_make2pMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map2 = f.make2pMap();
    LinkedHashSet<Territory> mapForTwoP1 = map2.get("King");
    LinkedHashSet<Territory> mapForTwoP2 = map2.get("Red");
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

    assertEquals("King", dorado.getOwner());
    assertEquals("King", hollywood.getOwner());
    assertEquals("King", hanamura.getOwner());

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

  @Test
  public void test_make3pMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map3 = f.make3pMap();
    LinkedHashSet<Territory> mapForThreeP1 = map3.get("King");
    LinkedHashSet<Territory> mapForThreeP2 = map3.get("Red");
    LinkedHashSet<Territory> mapForThreeP3 = map3.get("Pink");
    assertEquals(3, mapForThreeP1.size());
    assertEquals(3, mapForThreeP2.size());
    assertEquals(3, mapForThreeP3.size());

    Iterator<Territory> iter = mapForThreeP1.iterator();
    Territory dorado = iter.next();
    assertEquals("Dorado", dorado.getTerritoryName());
    LinkedHashSet<Territory> doradoNeighs = dorado.getNeighbours();
    assertEquals(3, doradoNeighs.size());

    Territory hanamura = iter.next();
    assertEquals("Hanamura", hanamura.getTerritoryName());
    LinkedHashSet<Territory> hanamuraNeighs = hanamura.getNeighbours();
    assertEquals(4, hanamuraNeighs.size());

    Territory hollywood = iter.next();
    assertEquals("Hollywood", hollywood.getTerritoryName());
    LinkedHashSet<Territory> hollywoodNeighs = hollywood.getNeighbours();
    assertEquals(6, hollywoodNeighs.size());

    iter = mapForThreeP2.iterator();
    Territory volskaya = iter.next();
    assertEquals("Volskaya", volskaya.getTerritoryName());
    LinkedHashSet<Territory> volskayaNeighs = volskaya.getNeighbours();
    assertEquals(3, volskayaNeighs.size());

    Territory ilios = iter.next();
    assertEquals("Ilios", ilios.getTerritoryName());
    LinkedHashSet<Territory> iliosNeighs = ilios.getNeighbours();
    assertEquals(4, iliosNeighs.size());

    Territory junkertown = iter.next();
    assertEquals("Junkertown", junkertown.getTerritoryName());
    LinkedHashSet<Territory> junkertownNeighs = junkertown.getNeighbours();
    assertEquals(4, junkertownNeighs.size());

    iter = mapForThreeP3.iterator();
    Territory nepal = iter.next();
    assertEquals("Nepal", nepal.getTerritoryName());
    LinkedHashSet<Territory> nepalNeighs = nepal.getNeighbours();
    assertEquals(3, nepalNeighs.size());

    Territory oasis = iter.next();
    assertEquals("Oasis", oasis.getTerritoryName());
    LinkedHashSet<Territory> oasisNeighs = oasis.getNeighbours();
    assertEquals(5, oasisNeighs.size());

    Territory numbani = iter.next();
    assertEquals("Numbani", numbani.getTerritoryName());
    LinkedHashSet<Territory> numbaniNeighs = numbani.getNeighbours();
    assertEquals(4, numbaniNeighs.size());
  }

  @Test
  public void test_make4pMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map4 = f.make4pMap();
    LinkedHashSet<Territory> mapForFourP1 = map4.get("King");
    LinkedHashSet<Territory> mapForFourP2 = map4.get("Red");
    LinkedHashSet<Territory> mapForFourP3 = map4.get("Pink");
    LinkedHashSet<Territory> mapForFourP4 = map4.get("Blue");
    assertEquals(2, mapForFourP1.size());
    assertEquals(2, mapForFourP2.size());
    assertEquals(2, mapForFourP3.size());
    assertEquals(2, mapForFourP4.size());

    Iterator<Territory> iter = mapForFourP1.iterator();
    Territory dorado = iter.next();
    assertEquals("Dorado", dorado.getTerritoryName());
    LinkedHashSet<Territory> doradoNeighs = dorado.getNeighbours();
    assertEquals(3, doradoNeighs.size());

    Territory hanamura = iter.next();
    assertEquals("Hanamura", hanamura.getTerritoryName());
    LinkedHashSet<Territory> hanamuraNeighs = hanamura.getNeighbours();
    assertEquals(4, hanamuraNeighs.size());

    iter = mapForFourP2.iterator();
    Territory hollywood = iter.next();
    assertEquals("Hollywood", hollywood.getTerritoryName());
    LinkedHashSet<Territory> hollywoodNeighs = hollywood.getNeighbours();
    assertEquals(4, hollywoodNeighs.size());

    Territory volskaya = iter.next();
    assertEquals("Volskaya", volskaya.getTerritoryName());
    LinkedHashSet<Territory> volskayaNeighs = volskaya.getNeighbours();
    assertEquals(2, volskayaNeighs.size());

    iter = mapForFourP3.iterator();
    Territory ilios = iter.next();
    assertEquals("Ilios", ilios.getTerritoryName());
    LinkedHashSet<Territory> iliosNeighs = ilios.getNeighbours();
    assertEquals(4, iliosNeighs.size());

    Territory junkertown = iter.next();
    assertEquals("Junkertown", junkertown.getTerritoryName());
    LinkedHashSet<Territory> junkertownNeighs = junkertown.getNeighbours();
    assertEquals(3, junkertownNeighs.size());

    iter = mapForFourP4.iterator();
    Territory nepal = iter.next();
    assertEquals("Nepal", nepal.getTerritoryName());
    LinkedHashSet<Territory> nepalNeighs = nepal.getNeighbours();
    assertEquals(4, nepalNeighs.size());

    Territory oasis = iter.next();
    assertEquals("Oasis", oasis.getTerritoryName());
    LinkedHashSet<Territory> oasisNeighs = oasis.getNeighbours();
    assertEquals(2, oasisNeighs.size());

  }

  @Test
  public void test_make5pMap() {
    MapFactory f = new MapFactory();
    HashMap<String, LinkedHashSet<Territory>>  map5 = f.make5pMap();
    LinkedHashSet<Territory> mapForFiveP1 = map5.get("King");
    LinkedHashSet<Territory> mapForFiveP2 = map5.get("Red");
    LinkedHashSet<Territory> mapForFiveP3 = map5.get("Pink");
    LinkedHashSet<Territory> mapForFiveP4 = map5.get("Blue");
    LinkedHashSet<Territory> mapForFiveP5 = map5.get("Green");
    assertEquals(2, mapForFiveP1.size());
    assertEquals(2, mapForFiveP2.size());
    assertEquals(2, mapForFiveP3.size());
    assertEquals(2, mapForFiveP4.size());
    assertEquals(2, mapForFiveP5.size());

    Iterator<Territory> iter = mapForFiveP1.iterator();
    Territory dorado = iter.next();
    assertEquals("Dorado", dorado.getTerritoryName());
    LinkedHashSet<Territory> doradoNeighs = dorado.getNeighbours();
    assertEquals(3, doradoNeighs.size());

    Territory hanamura = iter.next();
    assertEquals("Hanamura", hanamura.getTerritoryName());
    LinkedHashSet<Territory> hanamuraNeighs = hanamura.getNeighbours();
    assertEquals(4, hanamuraNeighs.size());

    iter = mapForFiveP2.iterator();
    Territory hollywood = iter.next();
    assertEquals("Hollywood", hollywood.getTerritoryName());
    LinkedHashSet<Territory> hollywoodNeighs = hollywood.getNeighbours();
    assertEquals(4, hollywoodNeighs.size());

    Territory volskaya = iter.next();
    assertEquals("Volskaya", volskaya.getTerritoryName());
    LinkedHashSet<Territory> volskayaNeighs = volskaya.getNeighbours();
    assertEquals(6, volskayaNeighs.size());

    iter = mapForFiveP3.iterator();
    Territory ilios = iter.next();
    assertEquals("Ilios", ilios.getTerritoryName());
    LinkedHashSet<Territory> iliosNeighs = ilios.getNeighbours();
    assertEquals(3, iliosNeighs.size());

    Territory junkertown = iter.next();
    assertEquals("Junkertown", junkertown.getTerritoryName());
    LinkedHashSet<Territory> junkertownNeighs = junkertown.getNeighbours();
    assertEquals(5, junkertownNeighs.size());

    iter = mapForFiveP4.iterator();
    Territory nepal = iter.next();
    assertEquals("Nepal", nepal.getTerritoryName());
    LinkedHashSet<Territory> nepalNeighs = nepal.getNeighbours();
    assertEquals(3, nepalNeighs.size());

    Territory oasis = iter.next();
    assertEquals("Oasis", oasis.getTerritoryName());
    LinkedHashSet<Territory> oasisNeighs = oasis.getNeighbours();
    assertEquals(4, oasisNeighs.size());

    iter = mapForFiveP5.iterator();
    Territory numbani = iter.next();
    assertEquals("Numbani", numbani.getTerritoryName());
    LinkedHashSet<Territory> numbaniNeighs = numbani.getNeighbours();
    assertEquals(4, numbaniNeighs.size());

    Territory anubis = iter.next();
    assertEquals("Anubis", anubis.getTerritoryName());
    LinkedHashSet<Territory> anubisNeighs = anubis.getNeighbours();
    assertEquals(2, anubisNeighs.size());

  }
}
