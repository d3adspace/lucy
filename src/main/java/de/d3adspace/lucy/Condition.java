package de.d3adspace.lucy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Condition {
  private final Object left;
  private final Operator operator;
  private final Object value;

  public Condition(Object left, Operator operator, Object value) {
    this.left = left;
    this.operator = operator;
    this.value = value;
  }

  public static Condition of(String left, String operator, String right) {
    return new Condition(left, Operator.of(operator), right);
  }

  public static Condition equal(String column, Object value) {
    return new Condition(column, Operator.EQUAL, value);
  }

  public static Condition notEqual(String column, Object value) {
    return new Condition(column, Operator.NOT_EQUAL, value);
  }

  public static Condition between(String column, Object left, Object right) {
    return new Condition(column, Operator.BETWEEN, left.toString() + " AND " + right.toString());
  }

  public static Condition notBetween(String column, Object left, Object right) {
    return new Condition(column, Operator.NOT_BETWEEN,
        left.toString() + " AND " + right.toString());
  }

  public static Condition greaterThan(String column, Object value) {
    return new Condition(column, Operator.GREATER_THAN, value);
  }

  public static Condition greaterThanOrEqual(String column, Object value) {
    return new Condition(column, Operator.GREATER_THAN_OR_EQUAL, value);
  }

  public static Condition lessThan(String column, Object value) {
    return new Condition(column, Operator.LESS_THAN, value);
  }

  public static Condition lessThanOrEqual(String column, Object value) {
    return new Condition(column, Operator.LESS_THAN_OR_EQUAL, value);
  }

  public static Condition like(String column, Object value) {
    return new Condition(column, Operator.LIKE, value);
  }

  public static Condition notLike(String column, Object value) {
    return new Condition(column, Operator.NOT_LIKE, value);
  }

  public static Condition in(String column, Collection<?> values) {
    return new Condition(column, Operator.IN, values);
  }

  public static Condition in(String column, Object... values) {
    return new Condition(column, Operator.IN, List.of(values));
  }

  public static Condition notIn(String column, Collection<?> values) {
    return new Condition(column, Operator.NOT_IN, values);
  }

  public static Condition notIn(String column, Object... values) {
    return new Condition(column, Operator.NOT_IN, List.of(values));
  }

  public static Condition isNull(String column) {
    return new Condition(column, Operator.IS_NULL, null);
  }

  public static Condition isNotNull(String column) {
    return new Condition(column, Operator.IS_NOT_NULL, null);
  }

  public static Condition not(Condition condition) {
    return new Condition(null, Operator.NOT, condition);
  }

  protected Object left() {
    return left;
  }

  protected Object right() {
    return value;
  }

  public Operator operator() {
    return operator;
  }

  public Condition and(Condition right) {
    return new Condition(this, Operator.AND, right);
  }

  public Condition or(Condition right) {
    return new Condition(this, Operator.OR, right);
  }

  public Condition xor(Condition right) {
    return new Condition(this, Operator.XOR, right);
  }

  public Condition andWhere(Condition right) {
    return CompositeCondition.and(this, right);
  }

  public Condition orWhere(Condition right) {
    return CompositeCondition.or(this, right);
  }

  public Condition xorWhere(Condition right) {
    return CompositeCondition.xor(this, right);
  }

  private String compileValue() {
    if (value instanceof String && operator.quote()) {
      return "'" + value + "'";
    } else if (value instanceof Collection<?> values) {
      return "(" + values.stream().map(element -> {
        if (element instanceof String) {
          return "'" + element + "'";
        } else {
          return element.toString();
        }
      }).collect(Collectors.joining(", ")) + ")";
    } else {
      return value.toString();
    }
  }

  @Override
  public String toString() {
    return (left == null ? "" : left + " ") + operator.toString() + (value == null ? ""
        : " " + compileValue());
  }

  public enum Operator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    IN("IN"),
    NOT_IN("NOT IN"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    BETWEEN("BETWEEN", false),
    NOT_BETWEEN("NOT BETWEEN", false),
    AND("AND"),
    OR("OR"),
    XOR("XOR"),
    NOT("NOT", false);

    private final String operatorValue;
    private final boolean quote;

    Operator(String operatorValue) {
      this(operatorValue, true);
    }

    Operator(String operatorValue, boolean quote) {
      this.operatorValue = operatorValue;
      this.quote = quote;
    }

    public static Operator of(String operator) {
      return Arrays.stream(values())
          .filter(op -> op.operatorValue.equalsIgnoreCase(operator))
          .findFirst().orElse(null);
    }

    @Override
    public String toString() {
      return operatorValue;
    }

    public boolean quote() {
      return quote;
    }
  }
}
