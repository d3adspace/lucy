package de.d3adspace.lucy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Select {
  private final boolean distinct;
  private final Collection<String> select;
  private final Collection<String> from;
  private final Collection<Join> joins;
  private final Condition where;
  private final Condition having;
  private final int limit;
  private final int offset;
  private final Order order;
  private final Collection<String> groupBy;

  public Select(boolean distinct, Collection<String> select, Collection<String> from,
      Collection<Join> joins,
      Condition where, Condition having, int limit, int offset, Order order,
      Collection<String> groupBy) {
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

  public static Select from(String... table) {
    return new Select(false, new LinkedList<>(), List.of(table), new LinkedList<>(), null, null, 0,
        0, null, new LinkedList<>());
  }

  public Select columns(String... columns) {
    var select = List.of(columns);
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select distinct() {
    return distinct(true);
  }

  public Select distinct(boolean distinct) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select tables(String... tables) {
    var from = List.of(tables);
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select join(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    joins.add(Join.join(table, source, target));
    return this;
  }

  public Select leftJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    joins.add(Join.leftJoin(table, source, target));
    return this;
  }

  public Select rightJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    joins.add(Join.rightJoin(table, source, target));
    return this;
  }

  public Select fullJoin(String table, String source, String target) {
    Objects.requireNonNull(table);
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    joins.add(Join.fullJoin(table, source, target));
    return this;
  }

  public Select crossJoin(String table) {
    Objects.requireNonNull(table);
    joins.add(Join.crossJoin(table));
    return this;
  }

  public Select limit(int limit) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select offset(int offset) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select where(Condition where) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select orWhere(Condition where) {
    return new Select(distinct, select, from, joins,
        this.where == null ? where : this.where.orWhere(where), having, limit, offset, order,
        groupBy);
  }

  public Select andWhere(Condition where) {
    return new Select(distinct, select, from, joins,
        this.where == null ? where : this.where.andWhere(where), having, limit, offset, order,
        groupBy);
  }

  public Select xorWhere(Condition where) {
    return new Select(distinct, select, from, joins,
        this.where == null ? where : this.where.xorWhere(where), having, limit, offset, order,
        groupBy);
  }

  public Select groupBy(String... columns) {
    var groupBy = List.of(columns);
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public Select orderBy(String column) {
    return orderBy(column, true);
  }

  public Select orderBy(String column, boolean ascending) {
    return orderBy(Order.by(column, ascending));
  }

  public Select orderBy(Order order) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);

  }

  public Select having(Condition having) {
    return new Select(distinct, select, from, joins, where, having, limit, offset, order, groupBy);
  }

  public String build() {
    var distinct = this.distinct ? "DISTINCT " : "";
    var select = this.select.isEmpty() ? "*" : String.join(", ", this.select);
    var from = this.from.isEmpty() ? "" : String.join(", ", this.from);
    var joins = this.joins.isEmpty() ? ""
        : " " + this.joins.stream().map(Join::toString).collect(Collectors.joining(" "));
    var where = this.where == null ? "" : " WHERE " + this.where;
    var having = this.having == null ? "" : " HAVING " + this.having;
    var groupBy = this.groupBy.isEmpty() ? "" : " GROUP BY " + String.join(", ", this.groupBy);
    var orderBy = this.order == null ? "" : " ORDER BY " + this.order;
    var limit = this.limit == 0 ? "" : " LIMIT " + this.limit;
    var offset = this.offset == 0 ? "" : " OFFSET " + this.offset;
    return String.format("SELECT %s%s FROM %s%s%s%s%s%s%s%s", distinct, select, from, joins, where,
        having, groupBy, orderBy, limit, offset);
  }

  @Override
  public String toString() {
    return build();
  }
}
