package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import de.d3adspace.lucy.Table.Column;
import org.junit.jupiter.api.Test;

class TableTest {

  @Test
  void testCreateTable() {
    assertEquals("CREATE TABLE table (col1 INTEGER, col2 INTEGER)",
        Table.create("table").column("col1", "INTEGER").column("col2", "INTEGER").toString());
  }

  @Test
  void testCreateTableWithPrimaryKey() {
    assertEquals("CREATE TABLE table (col1 INTEGER PRIMARY KEY)",
        Table.create("table").column(Column.of("col1", "INTEGER").primaryKey()).toString());
  }

  @Test
  void testCreateTableWithPrimaryKeyAndAutoIncrement() {
    assertEquals("CREATE TABLE table (col1 INTEGER AUTO_INCREMENT PRIMARY KEY)",
        Table.create("table").column(Column.of("col1", "INTEGER").primaryKey().autoIncrement()).toString());
  }

  @Test
  void testCreateTableWithPrimaryKeyAndAutoIncrementAndNotNull() {
    assertEquals("CREATE TABLE table (col1 INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY)",
        Table.create("table").column(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull()).toString());
  }

  @Test
  void testCreateTableWithPrimaryKeyAndAutoIncrementAndNotNullAndDefault() {
    assertEquals("CREATE TABLE table (col1 INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 1)",
        Table.create("table").column(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull().defaultValue(1)).toString());
  }

  @Test
  void testCreateTableWithPrimaryKeyAndAutoIncrementAndNotNullAndDefaultAndUnique() {
    assertEquals("CREATE TABLE table (col1 INTEGER NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY DEFAULT 1)",
        Table.create("table").column(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull().defaultValue(1).unique()).toString());
  }

  @Test
  void testForeignKey() {
    assertEquals("CREATE TABLE table (col1 INTEGER, CONSTRAINT FK_TABLE2 FOREIGN KEY (col1) REFERENCES table2 (col1))",
        Table.create("table").column(Column.of("col1", "INTEGER")).foreignKey("table2", "col1", "col1").toString());
  }

  @Test
  void testDrop() {
    assertEquals("DROP TABLE table", Table.drop("table").toString());
  }

  @Test
  void testTruncate() {
    assertEquals("TRUNCATE TABLE table", Table.truncate("table").toString());
  }

  @Test
  void testAlter() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER", Table.alter("table").addColumn(Column.of("col1", "INTEGER")).toString());
  }

  @Test
  void testAlterWithPrimaryKey() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER PRIMARY KEY", Table.alter("table").addColumn(Column.of("col1", "INTEGER").primaryKey()).toString());
  }

  @Test
  void testAlterWithPrimaryKeyAndAutoIncrement() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER AUTO_INCREMENT PRIMARY KEY", Table.alter("table").addColumn(Column.of("col1", "INTEGER").primaryKey().autoIncrement()).toString());
  }

  @Test
  void testAlterWithPrimaryKeyAndAutoIncrementAndNotNull() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY", Table.alter("table").addColumn(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull()).toString());
  }

  @Test
  void testAlterWithPrimaryKeyAndAutoIncrementAndNotNullAndDefault() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY DEFAULT 1", Table.alter("table").addColumn(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull().defaultValue(1)).toString());
  }

  @Test
  void testAlterWithPrimaryKeyAndAutoIncrementAndNotNullAndDefaultAndUnique() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY DEFAULT 1", Table.alter("table").addColumn(Column.of("col1", "INTEGER").primaryKey().autoIncrement().notNull().defaultValue(1).unique()).toString());
  }

  @Test
  void testAlterWithDrop() {
    assertEquals("ALTER TABLE table DROP COLUMN col1", Table.alter("table").dropColumn("col1").toString());
  }

  @Test
  void testAlterWithRename() {
    assertEquals("ALTER TABLE table RENAME COLUMN col1 TO col2", Table.alter("table").renameColumn("col1", "col2").toString());
  }

  @Test
  void testInnerCreate() {
    assertEquals("CREATE TABLE table (col1 INTEGER)", Table.of("table").create().column("col1", "INTEGER").toString());
  }

  @Test
  void testInnerAlter() {
    assertEquals("ALTER TABLE table ADD COLUMN col1 INTEGER", Table.of("table").alter().addColumn("col1", "INTEGER").toString());
  }

  @Test
  void testInnerDrop() {
    assertEquals("DROP TABLE table", Table.of("table").drop().toString());
  }

  @Test
  void testInnerTruncate() {
    assertEquals("TRUNCATE TABLE table", Table.of("table").truncate().toString());
  }

  @Test
  void testRename() {
    assertEquals("RENAME TABLE table TO table2", Table.rename("table", "table2").toString());
  }

  @Test
  void testInnerRename() {
    assertEquals("RENAME TABLE table TO table2", Table.of("table").rename("table2").toString());
  }

  @Test
  void testCreateTableWithDefaultNumber() {
    assertEquals("CREATE TABLE table (col1 INTEGER DEFAULT 1)",
        Table.create("table").column(Column.of("col1", "INTEGER").defaultValue(1)).toString());
  }
}
