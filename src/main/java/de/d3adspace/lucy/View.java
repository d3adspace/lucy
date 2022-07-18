package de.d3adspace.lucy;

import java.util.Objects;

public final class View {
  private final String name;
  private final boolean replaceIfExists;
  private final Select select;

  private View(String name, boolean replaceIfExists, Select select) {
    this.name = name;
    this.replaceIfExists = replaceIfExists;
    this.select = select;
  }

  public Drop drop() {
    return new Drop(name);
  }

  public static Drop drop(String name) {
    return new Drop(name);
  }

  public static View named(String name) {
    return new View(name, false, null);
  }

  public View replaceIfExists() {
    return replaceIfExists(true);
  }

  public View replaceIfExists(boolean replaceIfExists) {
    return new View(name, replaceIfExists, select);
  }

  public View as(Select select) {
    return new View(name, replaceIfExists, select);
  }

  @Override
  public String toString() {
    Objects.requireNonNull(name);
    Objects.requireNonNull(select);
    return "CREATE VIEW " + (replaceIfExists ? "OR REPLACE " : "") + name + " AS " + select;
  }

  public static final class Drop {
    private final String name;

    private Drop(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "DROP VIEW " + name;
    }
  }
}
