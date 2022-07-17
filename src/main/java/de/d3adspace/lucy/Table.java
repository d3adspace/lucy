package de.d3adspace.lucy;

public final class Table {
  private final String name;

  public Table(String name) {
    this.name = name;
  }

  public static Table of(String name) {
    return new Table(name);
  }

  public String name() {
    return name;
  }
}
