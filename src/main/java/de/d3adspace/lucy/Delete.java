package de.d3adspace.lucy;

import java.util.Objects;

public final class Delete {
  private final String table;
  private final Condition where;

  private Delete(String table, Condition where) {
    this.table = table;
    this.where = where;
  }

  public static Delete from(String table) {
    return new Delete(table, null);
  }

  public Delete table(String table) {
    return new Delete(table, where);
  }

  public Delete where(Condition where) {
    return new Delete(table, where);

  }

  public Delete orWhere(Condition where) {
    return new Delete(table, this.where == null ? where : this.where.orWhere(where));
  }

  public Delete andWhere(Condition where) {
    return new Delete(table, this.where == null ? where : this.where.andWhere(where));
  }

  public Delete xorWhere(Condition where) {
    return new Delete(table, this.where == null ? where : this.where.xorWhere(where));
  }

  @Override
  public String toString() {
    Objects.requireNonNull(table);
    return "DELETE FROM " + table + (where == null ? "" : " WHERE " + where);
  }
}
