package de.d3adspace.lucy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class Table {
  public static Creation create(String name) {
    return new Creation(name, new ConcurrentHashMap<>());
  }

  public static class Creation {
    private final String name;
    private final Map<String, String> columns;

    private Creation(String name, Map<String, String> columns) {
      this.name = name;
      this.columns = columns;
    }

    public String name() {
      return name;
    }

    public Map<String, String> columns() {
      return Map.copyOf(columns);
    }

    public Creation column(String name, String type) {
      columns.put(name, type);
      return this;
    }

    public String build() {
      var column = columns.entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(", "));
      return "CREATE TABLE %s (%s)".formatted(name, column);
    }
  }
}
