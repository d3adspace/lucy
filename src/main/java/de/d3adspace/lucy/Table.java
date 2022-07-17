package de.d3adspace.lucy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Table {
  public static Create create(String name) {
    return new Create(name, new LinkedList<>(), new LinkedList<>());
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
          + (foreignKeys.isEmpty() ? "" : ", " + foreignKeys.stream().map(ForeignKey::toString).collect(Collectors.joining(", ")))
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
      return name + " " + type + (notNull ? " NOT NULL" : "") + (autoIncrement ? " AUTO_INCREMENT" : "") + (unique ? " UNIQUE" : "") + (primaryKey ? " PRIMARY KEY" : "") + (defaultValue != null ? " DEFAULT " + (defaultValue instanceof String ? "'" + defaultValue + "'" : defaultValue) : "");
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
      return "CONSTRAINT " + name + " FOREIGN KEY (" + column + ") REFERENCES " + referenceTable + " (" + referenceColumn + ")";
    }
  }
}
