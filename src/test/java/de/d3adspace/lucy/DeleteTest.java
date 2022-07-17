package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeleteTest {
  @Test
  void testDelete() {
    assertEquals("DELETE FROM table", Delete.from("table").toString());
  }

  @Test
  void testDeleteTable() {
    assertEquals("DELETE FROM table2", Delete.from("table").table("table2").toString());
  }

  @Test
  void testDeleteWhere() {
    assertEquals("DELETE FROM table WHERE col1 = 'value'",
        Delete.from("table").where(Condition.equal("col1", "value")).toString());
  }

  @Test
  void testDeleteWhereOr() {
    assertEquals("DELETE FROM table WHERE col1 = 'value' OR col2 = 'value'",
        Delete.from("table").where(Condition.equal("col1", "value").or(Condition.equal("col2", "value"))).toString());
  }

  @Test
  void testDeleteWhereAnd() {
    assertEquals("DELETE FROM table WHERE col1 = 'value' AND col2 = 'value'",
        Delete.from("table").where(Condition.equal("col1", "value").and(Condition.equal("col2", "value"))).toString());
  }

  @Test
  void testDeleteWhereXor() {
    assertEquals("DELETE FROM table WHERE col1 = 'value' XOR col2 = 'value'",
        Delete.from("table").where(Condition.equal("col1", "value").xor(Condition.equal("col2", "value"))).toString());
  }

  @Test
  void testDeleteWhereIsNull() {
    assertEquals("DELETE FROM table WHERE col1 IS NULL",
        Delete.from("table").where(Condition.isNull("col1")).toString());
  }

  @Test
  void testDeleteWhereIsNotNull() {
    assertEquals("DELETE FROM table WHERE col1 IS NOT NULL",
        Delete.from("table").where(Condition.isNotNull("col1")).toString());
  }

  @Test
  void testDeleteWhereLike() {
    assertEquals("DELETE FROM table WHERE col1 LIKE 'value'",
        Delete.from("table").where(Condition.like("col1", "value")).toString());
  }

  @Test
  void testDeleteWhereNotLike() {
    assertEquals("DELETE FROM table WHERE col1 NOT LIKE 'value'",
        Delete.from("table").where(Condition.notLike("col1", "value")).toString());
  }

  @Test
  void testDeleteWhereIn() {
    assertEquals("DELETE FROM table WHERE col1 IN (1, 2, 3)",
        Delete.from("table").where(Condition.in("col1", 1, 2, 3)).toString());
  }

  @Test
  void testDeleteWhereInString() {
    assertEquals("DELETE FROM table WHERE col1 IN ('value1', 'value2', 'value3')",
        Delete.from("table").where(Condition.in("col1", "value1", "value2", "value3")).toString());
  }

  @Test
  void testDeleteWhereNotIn() {
    assertEquals("DELETE FROM table WHERE col1 NOT IN (1, 2, 3)",
        Delete.from("table").where(Condition.notIn("col1", 1, 2, 3)).toString());
  }

  @Test
  void testDeleteWhereBetween() {
    assertEquals("DELETE FROM table WHERE col1 BETWEEN 1 AND 2",
        Delete.from("table").where(Condition.between("col1", 1, 2)).toString());
  }

  @Test
  void testDeleteWhereNotBetween() {
    assertEquals("DELETE FROM table WHERE col1 NOT BETWEEN 1 AND 2",
        Delete.from("table").where(Condition.notBetween("col1", 1, 2)).toString());
  }

  @Test
  void testDeleteWhereGreaterThan() {
    assertEquals("DELETE FROM table WHERE col1 > 1",
        Delete.from("table").where(Condition.greaterThan("col1", 1)).toString());
  }

  @Test
  void testDeleteWhereGreaterThanOrEqual() {
    assertEquals("DELETE FROM table WHERE col1 >= 1",
        Delete.from("table").where(Condition.greaterThanOrEqual("col1", 1)).toString());
  }

  @Test
  void testDeleteWhereLessThan() {
    assertEquals("DELETE FROM table WHERE col1 < 1",
        Delete.from("table").where(Condition.lessThan("col1", 1)).toString());
  }

  @Test
  void testDeleteWhereLessThanOrEqual() {
    assertEquals("DELETE FROM table WHERE col1 <= 1",
        Delete.from("table").where(Condition.lessThanOrEqual("col1", 1)).toString());
  }

  @Test
  void testDeleteWhereBetweenAnd() {
    assertEquals("DELETE FROM table WHERE col1 BETWEEN 1 AND 2 AND col2 = 'value'",
        Delete.from("table").where(Condition.between("col1", 1, 2).and(Condition.equal("col2", "value"))).toString());
  }

  @Test
  void testDeleteWhereBetweenOr() {
    assertEquals("DELETE FROM table WHERE col1 BETWEEN 1 AND 2 OR col2 = 'value'",
        Delete.from("table").where(Condition.between("col1", 1, 2).or(Condition.equal("col2", "value"))).toString());
  }

  @Test
  void testDeleteAndWhere() {
    assertEquals("DELETE FROM table WHERE (col1 = 'value') AND (col2 = 'value')",
        Delete.from("table").andWhere(Condition.equal("col1", "value")).andWhere(Condition.equal("col2", "value")).toString());
  }

  @Test
  void testDeleteOrWhere() {
    assertEquals("DELETE FROM table WHERE (col1 = 'value') OR (col2 = 'value')",
        Delete.from("table").orWhere(Condition.equal("col1", "value")).orWhere(Condition.equal("col2", "value")).toString());
  }

  @Test
  void testDeleteXorWhere() {
    assertEquals("DELETE FROM table WHERE (col1 = 'value') XOR (col2 = 'value')",
        Delete.from("table").xorWhere(Condition.equal("col1", "value")).xorWhere(Condition.equal("col2", "value")).toString());
  }
}
