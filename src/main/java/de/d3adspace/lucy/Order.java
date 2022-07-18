package de.d3adspace.lucy;

import java.util.Objects;

public final class Order {
  private final String column;
  private final boolean ascending;

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

  public Order column(String column) {
    return new Order(column, ascending);
  }

  public boolean ascending() {
    return ascending;
  }

  public Order ascending(boolean ascending) {
    return new Order(column, ascending);
  }

  public Order descending() {
    return new Order(column, false);
  }

  @Override
  public String toString() {
    Objects.requireNonNull(column);
    return column + (ascending ? "" : " DESC");
  }
}
