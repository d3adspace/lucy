package de.d3adspace.lucy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Update {
  private final String table;
  private final Map<String, Object> values;
  private Condition where;

  public Update(String table, Map<String, Object> values) {
    this.table = table;
    this.values = values;
  }

  public static Update into(String table) {
    return new Update(table, new LinkedHashMap<>());
  }

  public Update set(String column, Object value) {
    values.put(column, value);
    return this;
  }

  public Update where(Condition where) {
    this.where = where;
    return this;
  }

  public Update orWhere(Condition where) {
    this.where = this.where == null ? where : this.where.orWhere(where);
    return this;
  }

  public Update andWhere(Condition where) {
    this.where = this.where == null ? where : this.where.andWhere(where);
    return this;
  }

  public Update xorWhere(Condition where) {
    this.where = this.where == null ? where : this.where.xorWhere(where);
    return this;
  }

  @Override
  public String toString() {
    return "UPDATE " + table + " SET " + values.keySet().stream().map(column -> {
      if (values.get(column) instanceof String) {
        return column + " = '" + values.get(column) + "'";
      } else {
        return column + " = " + values.get(column);
      }
    }).collect(Collectors.joining(", ")) + (where == null ? "" : " WHERE " + where);
  }
}
