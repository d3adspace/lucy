package de.d3adspace.lucy;

public final class View {
  private final String name;
  private Select select;

  private View(String name, Select select) {
    this.name = name;
    this.select = select;
  }

  public static View named(String name) {
    return new View(name, null);
  }

  public View as(Select select) {
    this.select = select;
    return this;
  }

  @Override
  public String toString() {
    return "CREATE VIEW " + name + " AS " + select;
  }
}
