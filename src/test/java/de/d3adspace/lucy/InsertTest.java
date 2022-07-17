package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InsertTest {
  @Test
  void testInsertValue() {
    assertEquals("INSERT INTO table (col1) VALUES ('value')",
        Insert.into("table").value("col1", "value").toString());
  }

  @Test
  void testInsertValues() {
    assertEquals("INSERT INTO table (col1, col2) VALUES ('value', 'value')",
        Insert.into("table").value("col1", "value").value("col2", "value").toString());
  }

  @Test
  void testInsertMultipleNumber() {
    assertEquals("INSERT INTO table (col1, col2) VALUES (1, 2)",
        Insert.into("table").value("col1", 1).value("col2", 2).toString());
  }
}
