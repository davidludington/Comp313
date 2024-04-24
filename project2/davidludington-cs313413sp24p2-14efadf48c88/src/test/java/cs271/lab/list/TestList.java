package cs271.lab.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestList {

  private List<Integer> list;

  @Before
  public void setUp() throws Exception {
    list = new LinkedList<Integer>();
  }

  @After
  public void tearDown() throws Exception {
    list = null;
  }

  @Test
  public void testSizeEmpty() {
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
    try {
      list.get(0);
      fail("there should not be any items in the list");
    } catch (Exception ex) {
    }
  }

  @Test
  public void testSizeNonEmpty() {
    list.add(77);
    assertEquals(false, list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(77, list.get(0).intValue());
  }

  @Test
  public void testContains() {

    assertFalse(list.contains(77));
    list.add(77);
    assertTrue(list.contains(77));
    // that hold before and after adding 77 to the list
    //fail("Not yet implemented"); // remove this line when done
  }

  @Test
  public void testAddMultiple() {
    list.add(77);
    list.add(77);
    list.add(77);
    assertEquals(3, list.size());
    assertEquals(0, list.indexOf(77));
    assertEquals(77, list.get(1).intValue());
    assertEquals(2, list.lastIndexOf(77));
  }

  @Test
  public void testAddMultiple2() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    assertEquals(7, list.size());
    assertEquals(1, list.indexOf(77));
    assertEquals(5, list.lastIndexOf(77));
    assertEquals(44, list.get(2).intValue());
    assertEquals(77, list.get(3).intValue());
    assertEquals(List.of(33, 77, 44, 77, 55, 77, 66), list);
  }

  @Test
  public void testRemoveObject() {
    list.add(3);
    list.add(77);
    list.add(4);
    list.add(77);
    list.add(5);
    list.add(77);
    list.add(6);
    list.remove(5); // what does this method do? removes 5 from the list
    assertEquals(6, list.size());
    assertEquals(1, list.indexOf(77));
    assertEquals(3, list.lastIndexOf(77));
    assertEquals(4, list.get(2).intValue());
    assertEquals(77, list.get(3).intValue());
    list.remove(Integer.valueOf(5)); // what does this one do?
    assertEquals(5, list.size());
    assertEquals(1, list.indexOf(77));
    assertEquals(3, list.lastIndexOf(77));
    assertEquals(4, list.get(2).intValue());
    assertEquals(77, list.get(3).intValue());
  }

  @Test
  public void testContainsAll() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    // 1) assert that list contains all five different numbers added
    // 2) assert that list does not contain all of 11, 22, and 33

    assertTrue(list.containsAll(List.of(33, 77, 44, 77, 55, 66)));
    assertTrue(list.contains(33));
    assertTrue(list.contains(44));
    assertTrue(list.contains(55));
    assertTrue(list.contains(66));
    assertTrue(list.contains(77));

    assertFalse(list.contains(11));
    assertFalse(list.contains(22));
    //assertFalse(list.contains(33));
    //fail("Not yet implemented"); // remove this line when done
  }

  @Test
  public void testAddAll() {
    /*
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    */

    list.addAll(List.of(33, 77, 44, 77, 55, 77, 66));
    // add items to the list to make the following assertions pass
    // (without touching the assertions themselves)
    assertEquals(7, list.size());
    assertEquals(33, list.get(0).intValue());
    assertEquals(77, list.get(1).intValue());
    assertEquals(44, list.get(2).intValue());
    assertEquals(77, list.get(3).intValue());
    assertEquals(55, list.get(4).intValue());
    assertEquals(77, list.get(5).intValue());
    assertEquals(66, list.get(6).intValue());
  }

  @Test
  public void testRemoveAll() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    list.removeAll(List.of(33,44,55,66));
    // remove items from the list to make the following assertions pass
    // (without touching the assertions themselves)
    assertEquals(3, list.size());
    assertEquals(List.of(77, 77, 77), list);
  }

  @Test
  public void testRetainAll() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    // remove items from the list to make the following assertions pass
    // (without touching the assertions themselves)
    list.retainAll(List.of(77));
    assertEquals(3, list.size());
    assertEquals(List.of(77, 77, 77), list);
  }

  @Test
  public void testSet() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);

    list.set(1, 99);
    list.set(3, 99);
    list.set(5, 99);
    // such that the following assertions pass
    // (without touching the assertions themselves)
    assertEquals(7, list.size());
    assertEquals(33, list.get(0).intValue());
    assertEquals(99, list.get(1).intValue());
    assertEquals(44, list.get(2).intValue());
    assertEquals(99, list.get(3).intValue());
    assertEquals(55, list.get(4).intValue());
    assertEquals(99, list.get(5).intValue());
    assertEquals(66, list.get(6).intValue());
  }

  @Test
  public void testSubList() {
    list.add(33);
    list.add(77);
    list.add(44);
    list.add(77);
    list.add(55);
    list.add(77);
    list.add(66);
    // passes
    assertEquals(List.of(44, 77, 55), list.subList(2, 5));
  }
}
