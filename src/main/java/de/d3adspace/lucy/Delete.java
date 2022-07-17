package de.d3adspace.lucy;

import java.util.Objects;

public final class Delete {
  private String table;
  private Condition where;

  public Delete(String table) {
    this.table = table;
  }

  public static Delete from(String table) {
    Objects.requireNonNull(table);
    return new Delete(table);
  }

  public Delete table(String table) {
    this.table = table;
    return this;
  }

  public Delete where(Condition where) {
    this.where = where;
    return this;
  }

  public Delete orWhere(Condition where) {
    this.where = this.where == null ? where : this.where.orWhere(where);
    return this;
  }

  public Delete andWhere(Condition where) {
    this.where = this.where == null ? where : this.where.andWhere(where);
    return this;
  }

  public Delete xorWhere(Condition where) {
    this.where = this.where == null ? where : this.where.xorWhere(where);
    return this;
  }

  @Override
  public String toString() {
    return "DELETE FROM " + table + (where == null ? "" : " WHERE " + where);
  }
}
