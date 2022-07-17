package de.d3adspace.lucy;

public final class Order {
  private String column;
  private boolean ascending;

  private Order(String column, boolean ascending) {
    this.column = column;
    this.ascending = ascending;
  }

  public static Order by(String column) {
    return new Order(column, true);
  }

  public static Order by(String column, boolean ascending) {
    return new Order(column, ascending);
  }

  public String column() {
    return column;
  }

  public void column(String column) {
    this.column = column;
  }

  public boolean ascending() {
    return ascending;
  }

  public Order ascending(boolean ascending) {
    this.ascending = ascending;
    return this;
  }

  public Order descending() {
    this.ascending = false;
    return this;
  }

  @Override
  public String toString() {
    return column + (ascending ? "" : " DESC");
  }
}
