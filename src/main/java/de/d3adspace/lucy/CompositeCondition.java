package de.d3adspace.lucy;

public final class CompositeCondition extends Condition {
  private CompositeCondition(Condition left, Operator operator, Condition right) {
    super(left, operator, right);
  }

  private static Condition of(Condition left, Operator operator, Condition right) {
    return new CompositeCondition(left, operator, right);
  }

  public static Condition and(Condition left, Condition right) {
    return of(left, Operator.AND, right);
  }

  public static Condition or(Condition left, Condition right) {
    return of(left, Operator.OR, right);
  }

  public static Condition xor(Condition left, Condition right) {
    return of(left, Operator.XOR, right);
  }

  @Override
  public String toString() {
    return "(" + left() + ") " + operator().toString() + " (" + right() + ")";
  }
}
