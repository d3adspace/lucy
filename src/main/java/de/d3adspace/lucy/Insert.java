package de.d3adspace.lucy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Insert {
  private final String table;
  private final Map<String, Object> values;

  public Insert(String table, Map<String, Object> values) {
    this.table = table;
    this.values = values;
  }

  public static Insert into(String table) {
    return new Insert(table, new LinkedHashMap<>());
  }

  public Insert value(String column, Object value) {
    values.put(column, value);
    return this;
  }

  public String toString() {
    return "INSERT INTO %s (%s) VALUES (%s)".formatted(
        table,
        String.join(", ", values.keySet()),
        values.values().stream().map(value -> {
          if (value instanceof String) {
            return "'" + value + "'";
          } else {
            return value.toString();
          }
        }).collect(Collectors.joining(", "))
    );
  }
}
