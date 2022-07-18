package de.d3adspace.lucy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Update {
  private final String table;
  private final Map<String, Object> values;
  private final Condition where;

  private Update(String table, Map<String, Object> values, Condition where) {
    this.table = table;
    this.values = values;
    this.where = where;
  }

  public static Update into(String table) {
    return new Update(table, new LinkedHashMap<>(), null);
  }

  public Update set(String column, Object value) {
    values.put(column, value);
    return this;
  }

  public Update where(Condition where) {
    return new Update(table, values, where);
  }

  public Update orWhere(Condition where) {
    return new Update(table, values, this.where == null ? where : this.where.orWhere(where));
  }

  public Update andWhere(Condition where) {
    return new Update(table, values, this.where == null ? where : this.where.andWhere(where));
  }

  public Update xorWhere(Condition where) {
    return new Update(table, values, this.where == null ? where : this.where.xorWhere(where));
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
