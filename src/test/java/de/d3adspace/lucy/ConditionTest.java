package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ConditionTest {
  @Test
  void testCondition() {
    assertEquals("col1 = 'value'", Condition.of("col1", "=", "value").toString());
  }

  @Test
  void testConditionEqualString() {
    assertEquals("col1 = 'value'", Condition.equal("col1", "value").toString());
  }

  @Test
  void testConditionEqualNumber() {
    assertEquals("col1 = 1", Condition.equal("col1", 1).toString());
  }

  @Test
  void testConditionEqualNull() {
    assertEquals("col1 IS NULL", Condition.isNull("col1").toString());
  }

  @Test
  void testConditionEqualNotNull() {
    assertEquals("col1 IS NOT NULL", Condition.isNotNull("col1").toString());
  }

  @Test
  void testConditionLike() {
    assertEquals("col1 LIKE 'value'", Condition.like("col1", "value").toString());
  }

  @Test
  void testConditionNotLike() {
    assertEquals("col1 NOT LIKE 'value'", Condition.notLike("col1", "value").toString());
  }

  @Test
  void testConditionIn() {
    assertEquals("col1 IN (1, 2, 3)", Condition.in("col1", 1, 2, 3).toString());
  }

  @Test
  void testConditionInString() {
    assertEquals("col1 IN ('value1', 'value2', 'value3')", Condition.in("col1", "value1", "value2", "value3").toString());
  }

  @Test
  void testConditionNotIn() {
    assertEquals("col1 NOT IN (1, 2, 3)", Condition.notIn("col1", 1, 2, 3).toString());
  }

  @Test
  void testConditionBetween() {
    assertEquals("col1 BETWEEN 1 AND 2", Condition.between("col1", 1, 2).toString());
  }

  @Test
  void testConditionNotBetween() {
    assertEquals("col1 NOT BETWEEN 1 AND 2", Condition.notBetween("col1", 1, 2).toString());
  }

  @Test
  void testConditionGreaterThan() {
    assertEquals("col1 > 1", Condition.greaterThan("col1", 1).toString());
  }

  @Test
  void testConditionGreaterThanOrEqual() {
    assertEquals("col1 >= 1", Condition.greaterThanOrEqual("col1", 1).toString());
  }

  @Test
  void testConditionLessThan() {
    assertEquals("col1 < 1", Condition.lessThan("col1", 1).toString());
  }

  @Test
  void testConditionLessThanOrEqual() {
    assertEquals("col1 <= 1", Condition.lessThanOrEqual("col1", 1).toString());
  }

  @Test
  void testConditionNotEqualString() {
    assertEquals("col1 != 'value'", Condition.notEqual("col1", "value").toString());
  }

  @Test
  void testConditionNotEqualNumber() {
    assertEquals("col1 != 1", Condition.notEqual("col1", 1).toString());
  }

  @Test
  void testConditionInCollection() {
    assertEquals("col1 IN (1, 2, 3)", Condition.in("col1", List.of(1, 2, 3)).toString());
  }

  @Test
  void testConditionNotInCollection() {
    assertEquals("col1 NOT IN (1, 2, 3)", Condition.notIn("col1", List.of(1, 2, 3)).toString());
  }

  @Test
  void testConditionAnd() {
    assertEquals("col1 = 'value' AND col2 = 'value2'", Condition.equal("col1", "value").and(Condition.equal("col2", "value2")).toString());
  }

  @Test
  void testConditionOr() {
    assertEquals("col1 = 'value' OR col2 = 'value2'", Condition.equal("col1", "value").or(Condition.equal("col2", "value2")).toString());
  }

  @Test
  void testConditionXor() {
    assertEquals("col1 = 'value' XOR col2 = 'value2'", Condition.equal("col1", "value").xor(Condition.equal("col2", "value2")).toString());
  }

  @Test
  void testConditionAndOr() {
    assertEquals("col1 = 'value' AND col2 = 'value2' OR col3 = 'value3'", Condition.equal("col1", "value").and(Condition.equal("col2", "value2")).or(Condition.equal("col3", "value3")).toString());
  }

  @Test
  void testConditionNotWhere() {
    assertEquals("NOT col1 = 'value'", Condition.not(Condition.equal("col1", "value")).toString());
  }
}
