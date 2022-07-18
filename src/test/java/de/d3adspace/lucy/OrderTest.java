package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {
  @Test
  void testOrderBy() {
    assertEquals("col1", Order.by("col1").toString());
  }

  @Test
  void testOrderColumn() {
    assertEquals("col1", Order.by("col1").column());
  }

  @Test
  void testOrderColumnChanged() {
    Order order = Order.by("col1").column("col2");
    assertEquals("col2", order.column());
  }

  @Test
  void testOrderAscending() {
    assertTrue(Order.by("col1").ascending());
  }

  @Test
  void testOrderAscendingFalse() {
    assertFalse(Order.by("col1", false).ascending());
  }

  @Test
  void testOrderByAscending() {
    assertEquals("col1", Order.by("col1", true).toString());
  }

  @Test
  void testOrderByDescending() {
    assertEquals("col1 DESC", Order.by("col1", false).toString());
  }

  @Test
  void testOrderByAscendingDescending() {
    assertEquals("col1 DESC", Order.by("col1").descending().toString());
  }

  @Test
  void testOrderByDescendingAscending() {
    assertEquals("col1", Order.by("col1", false).ascending(true).toString());
  }
}
