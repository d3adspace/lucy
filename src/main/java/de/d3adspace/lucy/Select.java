package de.d3adspace.lucy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Select {
  private boolean distinct;
  private Collection<String> select;
  private Collection<String> from;
  private Collection<Join> joins;
  private Condition where;
  private Condition having;
  private int limit;
  private int offset;
  private Order order;
  private Collection<String> groupBy;

  public Select(boolean distinct, Collection<String> select, Collection<String> from, Collection<Join> joins,
      Condition where, Condition having, int limit, int offset, Order order, Collection<String> groupBy) {
    this.distinct = distinct;
    this.select = select;
    this.from = from;
    this.joins = joins;
    this.where = where;
    this.having = having;
    this.limit = limit;
    this.offset = offset;
    this.order = order;
    this.groupBy = groupBy;
  }

  public Select columns(String... columns) {
    Objects.requireNonNull(columns);
    this.select = List.of(columns);
    return this;
  }

  public static Select from(String... table) {
    return new Select(false, null, List.of(table), null, null, null, 0, 0, null, null);
  }

  public Select distinct() {
    return distinct(true);
  }

  public Select distinct(boolean distinct) {
    this.distinct = distinct;
    return this;
  }

  public Select tables(String... tables) {
    Objects.requireNonNull(tables);
    this.from = List.of(tables);
    return this;
  }

  public Select join(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    if (joins == null) {
      joins = new ArrayList<>();
    }
    joins.add(Join.join(table, source, target));
    return this;
  }

  public Select leftJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    if (joins == null) {
      joins = new ArrayList<>();
    }
    joins.add(Join.leftJoin(table, source, target));
    return this;
  }

  public Select rightJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    if (joins == null) {
      joins = new ArrayList<>();
    }
    joins.add(Join.rightJoin(table, source, target));
    return this;
  }

  public Select fullJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    if (joins == null) {
      joins = new ArrayList<>();
    }
    joins.add(Join.fullJoin(table, source, target));
    return this;
  }

  public Select crossJoin(String table) {
    Objects.requireNonNull(table);
    if (joins == null) {
      joins = new ArrayList<>();
    }
    joins.add(Join.crossJoin(table));
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
    this.where = this.where == null ? where : this.where.orWhere(where);
    return this;
  }

  public Select andWhere(Condition where) {
    this.where = this.where == null ? where : this.where.andWhere(where);
    return this;
  }

  public Select xorWhere(Condition where) {
    this.where = this.where == null ? where : this.where.xorWhere(where);
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
    var distinct = this.distinct ? "DISTINCT " : "";
    var select = this.select == null ? "*" : String.join(", ", this.select);
    var from = this.from == null ? "" : String.join(", ", this.from);
    var joins = this.joins == null ? "" : " " + this.joins.stream().map(Join::toString).collect(Collectors.joining(" "));
    var where = this.where == null ? "" : " WHERE " + this.where;
    var having = this.having == null ? "" : " HAVING " + this.having;
    var groupBy = this.groupBy == null ? "" : " GROUP BY " + String.join(", ", this.groupBy);
    var orderBy = this.order == null ? "" : " ORDER BY " + this.order;
    var limit = this.limit == 0 ? "" : " LIMIT " + this.limit;
    var offset = this.offset == 0 ? "" : " OFFSET " + this.offset;
    return String.format("SELECT %s%s FROM %s%s%s%s%s%s%s%s", distinct, select, from, joins, where, having, groupBy, orderBy, limit, offset);
  }

  @Override
  public String toString() {
    return build();
  }
}
