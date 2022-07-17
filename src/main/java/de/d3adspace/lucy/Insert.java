package de.d3adspace.lucy;

public final class Insert {
  private final String table;

  public Insert(String table) {
    this.table = table;
  }

  public static Insert into(String table) {
    return new Insert(table);
  }
}
