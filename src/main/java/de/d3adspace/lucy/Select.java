package de.d3adspace.lucy;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class Select {
  private Collection<String> select;
  private Collection<String> from;
  private Condition where;
  private Condition having;
  private int limit;
  private int offset;
  private Order order;
  private Collection<String> groupBy;

  public Select(Collection<String> select, Collection<String> from, Condition where, int limit, int offset, Order order, Collection<String> groupBy) {
    this.select = select;
    this.from = from;
    this.where = where;
    this.limit = limit;
    this.offset = offset;
    this.order = order;
    this.groupBy = groupBy;
  }

  public static Select all() {
    return new Select(null, null, null, 0, 0, null, null);
  }

  public static Select select(String... columns) {
    return new Select(List.of(columns), null, null, 0, 0, null, null);
  }

  public static Select select(Collection<String> select) {
    return new Select(select, null, null, 0, 0, null, null);
  }

  public Select columns(String... columns) {
    Objects.requireNonNull(columns);
    this.select = List.of(columns);
    return this;
  }

  public static Select from(String... table) {
    return new Select(null, List.of(table), null, 0, 0, null, null);
  }

  public Select tables(String... tables) {
    Objects.requireNonNull(tables);
    this.from = List.of(tables);
    return this;
  }

  public Select limit(int limit) {
    this.limit = limit;
    return this;
  }

  public Select offset(int offset) {
    this.offset = offset;
    return this;
  }

  public Select where(Condition where) {
    this.where = where;
    return this;
  }

  public Select orWhere(Condition where) {
    this.where = this.where == null ? where : this.where.or(where);
    return this;
  }

  public Select andWhere(Condition where) {
    this.where = this.where == null ? where : this.where.and(where);
    return this;
  }

  public Select groupBy(String... columns) {
    Objects.requireNonNull(columns);
    this.groupBy = List.of(columns);
    return this;
  }

  public Select orderBy(String column) {
    return orderBy(column, true);
  }

  public Select orderBy(String column, boolean ascending) {
    Objects.requireNonNull(column);
    this.order = Order.by(column, ascending);
    return orderBy(order);
  }

  public Select orderBy(Order order) {
    this.order = order;
    return this;
  }

  public Select having(Condition having) {
    this.having = having;
    return this;
  }

  public String build() {
    var select = this.select == null ? "*" : String.join(", ", this.select);
    var from = this.from == null ? "" : String.join(", ", this.from);
    var where = this.where == null ? "" : " WHERE " + this.where.toString();
    var having = this.having == null ? "" : " HAVING " + this.having.toString();
    var groupBy = this.groupBy == null ? "" : " GROUP BY " + String.join(", ", this.groupBy);
    var orderBy = this.order == null ? "" : " ORDER BY " + this.order.toString();
    var limit = this.limit == 0 ? "" : " LIMIT " + this.limit;
    var offset = this.offset == 0 ? "" : " OFFSET " + this.offset;
    return String.format("SELECT %s FROM %s%s%s%s%s%s%s", select, from, where, having, groupBy, orderBy, limit, offset);
  }
}
