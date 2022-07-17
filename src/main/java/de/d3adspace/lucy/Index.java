package de.d3adspace.lucy;

import java.util.Arrays;
import java.util.Collection;

public final class Index {
  public static Create create(String name, String table, String... columns) {
    return Create.index(name, table, Arrays.asList(columns));
  }

  public static Create createUnique(String name, String table, String... columns) {
    return Create.uniqueIndex(name, table, Arrays.asList(columns));
  }

  public static final class Create {
    private final String name;
    private final String table;
    private final Collection<String> columns;
    private final boolean unique;

    private Create(String name, String table, Collection<String> columns, boolean unique) {
      this.name = name;
      this.table = table;
      this.columns = columns;
      this.unique = unique;
    }

    public static Create index(String name, String table, Collection<String> columns) {
      return new Create(name, table, columns, false);
    }

    public static Create uniqueIndex(String name, String table, Collection<String> columns) {
      return new Create(name, table, columns, true);
    }

    @Override
    public String toString() {
      return "CREATE " + (unique ? "UNIQUE " : "") + "INDEX " + name + " ON " + table + " (" + String.join(", ", columns) + ")";
    }
  }
}
