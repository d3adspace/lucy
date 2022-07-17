package de.d3adspace.lucy;

import java.util.Objects;

public final class Delete {
  private String table;

  public Delete(String table) {
    this.table = table;
  }

  public static Delete from(String table) {
    Objects.requireNonNull(table);
    return new Delete(table);
  }
}
