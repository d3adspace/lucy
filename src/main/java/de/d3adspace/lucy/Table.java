package de.d3adspace.lucy;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public final class Table {
  private final String name;

  public Table(String name) {
    this.name = name;
  }

  public static Table of(String name) {
    return new Table(name);
  }

  public static Truncate truncate(String table) {
    return new Truncate(table);
  }

  public static Drop drop(String name) {
    return new Drop(name);
  }

  public static Create create(String name) {
    return new Create(name, new LinkedList<>(), new LinkedList<>());
  }

  public static Alter alter(String name) {
    return new Alter(name, new LinkedList<>(), new LinkedList<>(), new LinkedHashMap<>());
  }

  public static Rename rename(String name, String newName) {
    return new Rename(name, newName);
  }

  public Drop drop() {
    return drop(name);
  }

  public Truncate truncate() {
    return truncate(name);
  }

  public Create create() {
    return create(name);
  }

  public Alter alter() {
    return alter(name);
  }

  public Rename rename(String newName) {
    return new Rename(name, newName);
  }

  public static final class Rename {
    private final String name;
    private final String newName;

    private Rename(String name, String newName) {
      this.name = name;
      this.newName = newName;
    }

    @Override
    public String toString() {
      return "RENAME TABLE " + name + " TO " + newName;
    }
  }

  public static final class Alter {
    private final String name;
    private final Collection<Column> addColumns;
    private final Collection<String> dropColumns;
    private final Map<String, String> renameColumns;

    private Alter(String name, Collection<Column> addColumns, Collection<String> dropColumns,
        Map<String, String> renameColumns) {
      this.name = name;
      this.addColumns = addColumns;
      this.dropColumns = dropColumns;
      this.renameColumns = renameColumns;
    }

    public Alter addColumn(Column column) {
      addColumns.add(column);
      return this;
    }

    public Alter addColumn(String name, String type) {
      return addColumn(new Column(name, type));
    }

    public Alter dropColumn(String name) {
      dropColumns.add(name);
      return this;
    }

    public Alter renameColumn(String oldName, String newName) {
      renameColumns.put(oldName, newName);
      return this;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("ALTER TABLE ").append(name);
      if (!addColumns.isEmpty()) {
        sb.append(" ADD COLUMN ").append(addColumns.stream().map(Column::toString).collect(
            Collectors.joining(", ")));
      }
      if (!renameColumns.isEmpty()) {
        sb.append(" RENAME COLUMN ").append(renameColumns.entrySet().stream()
            .map(entry -> entry.getKey() + " TO " + entry.getValue()).collect(
                Collectors.joining(", ")));
      }
      if (!dropColumns.isEmpty()) {
        sb.append(" DROP COLUMN ").append(String.join(", ", dropColumns));
      }
      return sb.toString();
    }
  }

  public static final class Truncate {
    private final String name;

    private Truncate(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "TRUNCATE TABLE " + name;
    }
  }

  public static final class Drop {
    private final String name;

    private Drop(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "DROP TABLE " + name;
    }
  }

  public static final class Create {
    private final String name;
    private final Collection<Column> columns;
    private final Collection<ForeignKey> foreignKeys;

    public Create(String name, Collection<Column> columns, Collection<ForeignKey> foreignKeys) {
      this.name = name;
      this.columns = columns;
      this.foreignKeys = foreignKeys;
    }

    public Create column(String name, String type) {
      columns.add(new Column(name, type));
      return this;
    }

    public Create column(Column column) {
      this.columns.add(column);
      return this;
    }

    public Create foreignKey(String table, String source, String target) {
      return foreignKey("FK_" + table.toUpperCase(), source, table, target);
    }

    public Create foreignKey(String name, String table, String source, String target) {
      return foreignKey(new ForeignKey(name, table, source, target));
    }

    public Create foreignKey(ForeignKey foreignKey) {
      this.foreignKeys.add(foreignKey);
      return this;
    }

    @Override
    public String toString() {
      return "CREATE TABLE "
          + name
          + " (" + columns.stream().map(Column::toString).collect(Collectors.joining(", "))
          + (foreignKeys.isEmpty() ? ""
          : ", " + foreignKeys.stream().map(ForeignKey::toString).collect(Collectors.joining(", ")))
          + ")";
    }
  }

  public static final class Column {
    private final String name;
    private final String type;
    private boolean autoIncrement;
    private boolean notNull;
    private boolean unique;
    private boolean primaryKey;
    private Object defaultValue;

    private Column(String name, String type) {
      this.name = name;
      this.type = type;
    }

    public static Column of(String name, String type) {
      return new Column(name, type);
    }

    public Column autoIncrement() {
      return autoIncrement(true);
    }

    public Column autoIncrement(boolean autoIncrement) {
      this.autoIncrement = autoIncrement;
      return this;
    }

    public Column notNull() {
      return notNull(true);
    }

    public Column notNull(boolean notNull) {
      this.notNull = notNull;
      return this;
    }

    public Column unique() {
      return unique(true);
    }

    public Column unique(boolean unique) {
      this.unique = unique;
      return this;
    }

    public Column primaryKey() {
      return primaryKey(true);
    }

    public Column primaryKey(boolean primaryKey) {
      this.primaryKey = primaryKey;
      return this;
    }

    public Column defaultValue(Object defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    @Override
    public String toString() {
      return name + " " + type + (notNull ? " NOT NULL" : "") + (autoIncrement ? " AUTO_INCREMENT"
          : "") + (unique ? " UNIQUE" : "") + (primaryKey ? " PRIMARY KEY" : "") + (
          defaultValue != null ? " DEFAULT " + (defaultValue instanceof String ? "'" + defaultValue
              + "'" : defaultValue) : "");
    }
  }

  public static final class ForeignKey {
    private final String name;
    private final String column;
    private final String referenceTable;
    private final String referenceColumn;

    public ForeignKey(String name, String column, String referenceTable, String referenceColumn) {
      this.name = name;
      this.column = column;
      this.referenceTable = referenceTable;
      this.referenceColumn = referenceColumn;
    }

    @Override
    public String toString() {
      return "CONSTRAINT " + name + " FOREIGN KEY (" + column + ") REFERENCES " + referenceTable
          + " (" + referenceColumn + ")";
    }
  }
}
