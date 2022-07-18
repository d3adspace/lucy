package de.d3adspace.lucy;

public final class View {
  private final String name;
  private boolean replaceIfExists;
  private Select select;

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

  public View createAs(Select select) {
    this.select = select;
    return this;
  }

  public View replaceIfExists() {
    return replaceIfExists(true);
  }

  public View replaceIfExists(boolean replaceIfExists) {
    this.replaceIfExists = replaceIfExists;
    return this;
  }

  @Override
  public String toString() {
    return "CREATE VIEW " + (replaceIfExists ? "OR REPLACE " : "") + name + " AS " + select;
  }

  public static final class Drop {
    private final String name;

    public Drop(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "DROP VIEW " + name;
    }
  }
}
