package de.d3adspace.lucy;

public class CompositeCondition extends Condition {
  public CompositeCondition(Condition left, Operator operator, Condition value) {
    super(left, operator, value);
  }

  @Override
  public String toString() {
    if (left() instanceof Condition && right() instanceof Condition) {
      return left() + " " + operator() + " " + right();
    } else {
      return "(" + left() + " " + operator() + " " + right() + ")";
    }
  }
}
