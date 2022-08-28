package de.d3adspace.lucy;

public final class Show {
  public static Tables tables(String database) {
    return new Tables(database);
  }

  public static Databases databases() {
    return new Databases();
  }

  public static final class Tables {
    private final String database;

    private Tables(String database) {
      this.database = database;
    }

    @Override
    public String toString() {
      return "SHOW TABLES FROM " + database;
    }
  }

  public static final class Databases {
    private Databases() {
    }

    @Override
    public String toString() {
      return "SHOW DATABASES";
    }
  }
}
