package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IndexTest {
  @Test
  void testIndex() {
    assertEquals("CREATE INDEX index ON table (col1)",
        Index.create("index", "table", "col1").toString());
  }

  @Test
  void testUniqueIndex() {
    assertEquals("CREATE UNIQUE INDEX index ON table (col1)",
        Index.createUnique("index", "table", "col1").toString());
  }

  @Test
  void testIndexWithColumns() {
    assertEquals("CREATE INDEX index ON table (col1, col2)",
        Index.create("index", "table", "col1", "col2").toString());
  }

  @Test
  void testUniqueIndexWithColumns() {
    assertEquals("CREATE UNIQUE INDEX index ON table (col1, col2)",
        Index.createUnique("index", "table", "col1", "col2").toString());
  }
}
