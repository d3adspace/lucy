package de.d3adspace.lucy;

public final class Database {
  private final String name;

  private Database(String name) {
    this.name = name;
  }

  public static Database named(String name) {
    return new Database(name);
  }

  public Create create() {
    return new Create(name);
  }

  public Drop drop() {
    return new Drop(name);
  }

  public Backup backup() {
    return new Backup(name, null, false);
  }

  public static Create create(String name) {
    return new Create(name);
  }

  public static final class Create {
    private final String name;

    private Create(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "CREATE DATABASE " + name;
    }
  }

  public static Drop drop(String name) {
    return new Drop(name);
  }

  public static final class Drop {
    private final String name;

    private Drop(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "DROP DATABASE " + name;
    }
  }

  public static Backup backup(String name) {
    return new Backup(name, null, false);
  }

  public static final class Backup {
    private final String name;
    private final String toDisk;
    private final boolean withDifferential;

    private Backup(String name, String toDisk, boolean withDifferential) {
      this.name = name;
      this.toDisk = toDisk;
      this.withDifferential = withDifferential;
    }

    public Backup toDisk(String toDisk) {
      return new Backup(name, toDisk, withDifferential);
    }

    public Backup withDifferential() {
      return new Backup(name, toDisk, true);
    }

    @Override
    public String toString() {
      return "BACKUP DATABASE " + name + (toDisk != null ? " TO DISK = '" + toDisk + "'" : "")  + (withDifferential ? " WITH DIFFERENTIAL" : "");
    }
  }
}
