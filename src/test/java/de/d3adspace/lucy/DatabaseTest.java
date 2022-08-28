package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DatabaseTest {
  @Test
  void testCreate() {
    var create = Database.create("test");
    assertEquals("CREATE DATABASE test", create.toString());
  }

  @Test
  void testDrop() {
    var drop = Database.drop("test");
    assertEquals("DROP DATABASE test", drop.toString());
  }

  @Test
  void testInnerCreate() {
    var create = Database.named("test").create();
    assertEquals("CREATE DATABASE test", create.toString());
  }

  @Test
  void testInnerDrop() {
    var drop = Database.named("test").drop();
    assertEquals("DROP DATABASE test", drop.toString());
  }

  @Test
  void testBackup() {
    var backup = Database.backup("test");
    assertEquals("BACKUP DATABASE test", backup.toString());
  }

  @Test
  void testInnerBackup() {
    var backup = Database.named("test").backup();
    assertEquals("BACKUP DATABASE test", backup.toString());
  }

  @Test
  void testBackupToDisk() {
    var backup = Database.backup("test").toDisk("/tmp/test.db");
    assertEquals("BACKUP DATABASE test TO DISK = '/tmp/test.db'", backup.toString());
  }

  @Test
  void testInnerBackupToDisk() {
    var backup = Database.named("test").backup().toDisk("/tmp/test.db");
    assertEquals("BACKUP DATABASE test TO DISK = '/tmp/test.db'", backup.toString());
  }

  @Test
  void testWithDifferential() {
    var backup = Database.backup("test").withDifferential();
    assertEquals("BACKUP DATABASE test WITH DIFFERENTIAL", backup.toString());
  }

  @Test
  void testInnerWithDifferential() {
    var backup = Database.named("test").backup().withDifferential();
    assertEquals("BACKUP DATABASE test WITH DIFFERENTIAL", backup.toString());
  }

  @Test
  void testBackupToDiskWithDifferential() {
    var backup = Database.backup("test").toDisk("/tmp/test.db").withDifferential();
    assertEquals("BACKUP DATABASE test TO DISK = '/tmp/test.db' WITH DIFFERENTIAL", backup.toString());
  }

  @Test
  void testInnerBackupToDiskWithDifferential() {
    var backup = Database.named("test").backup().toDisk("/tmp/test.db").withDifferential();
    assertEquals("BACKUP DATABASE test TO DISK = '/tmp/test.db' WITH DIFFERENTIAL", backup.toString());
  }

  @Test
  void testShowTables() {
    var show = Database.showTables("test");
    assertEquals("SHOW TABLES FROM test", show.toString());
  }
}
