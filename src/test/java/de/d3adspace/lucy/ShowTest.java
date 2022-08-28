package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShowTest {
  @Test
  void tables() {
    var show = Show.tables("database");
    assertEquals("SHOW TABLES FROM database", show.toString());
  }

  @Test
  void tesTablesWithAlternativeName() {
    var show = Show.tables("database2");
    assertEquals("SHOW TABLES FROM database2", show.toString());
  }

  @Test
  void databases() {
    var show = Show.databases();
    assertEquals("SHOW DATABASES", show.toString());
  }
}
