package de.d3adspace.lucy;

public final class Join {
  private final Type type;
  private final String table;
  private final String sourceColumn;
  private final String targetColumn;

  private Join(Type type, String table, String sourceColumn, String targetColumn) {
    this.type = type;
    this.table = table;
    this.sourceColumn = sourceColumn;
    this.targetColumn = targetColumn;
  }

  public static Join join(String table, String sourceColumn, String targetColumn) {
    return new Join(Type.INNER, table, sourceColumn, targetColumn);
  }

  public static Join leftJoin(String table, String sourceColumn, String targetColumn) {
    return new Join(Type.LEFT, table, sourceColumn, targetColumn);
  }

  public static Join rightJoin(String table, String sourceColumn, String targetColumn) {
    return new Join(Type.RIGHT, table, sourceColumn, targetColumn);
  }

  public static Join fullJoin(String table, String sourceColumn, String targetColumn) {
    return new Join(Type.FULL, table, sourceColumn, targetColumn);
  }

  public static Join crossJoin(String table) {
    return new Join(Type.CROSS, table, null, null);
  }

  @Override
  public String toString() {
    return type + " JOIN " + table + (sourceColumn != null ? " ON " + sourceColumn + (
        targetColumn != null ? " = " + targetColumn : "") : "");
  }

  public enum Type {
    INNER("INNER"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    FULL("FULL"),
    CROSS("CROSS");

    private final String sql;

    Type(String sql) {
      this.sql = sql;
    }

    public String sql() {
      return sql;
    }

    @Override
    public String toString() {
      return sql();
    }
  }
}
