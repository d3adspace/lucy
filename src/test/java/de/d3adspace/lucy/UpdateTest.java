package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UpdateTest {
  @Test
  void testUpdate() {
    assertEquals("UPDATE table SET col1 = 'value'",
        Update.into("table").set("col1", "value").toString());
  }

  @Test
  void testUpdateMultiple() {
    assertEquals("UPDATE table SET col1 = 'value', col2 = 'value'",
        Update.into("table").set("col1", "value").set("col2", "value").toString());
  }

  @Test
  void testUpdateMultipleNumber() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2",
        Update.into("table").set("col1", 1).set("col2", 2).toString());
  }

  @Test
  void testUpdateMultipleNumberWithCondition() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE col3 = 3",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3)).toString());
  }

  @Test
  void testUpdateWithAnd() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE col3 = 3 AND col4 = 4",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3).and(Condition.equal("col4", 4))).toString());
  }

  @Test
  void testUpdateWithOr() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE col3 = 3 OR col4 = 4",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3).or(Condition.equal("col4", 4))).toString());
  }

  @Test
  void testUpdateWithXor() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE col3 = 3 XOR col4 = 4",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3).xor(Condition.equal("col4", 4))).toString());
  }

  @Test
  void testUpdateWithNot() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE NOT col3 = 3",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.not(Condition.equal("col3", 3))).toString());
  }

  @Test
  void testUpdateWithLike() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE col3 LIKE 'value'",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.like("col3", "value")).toString());
  }

  @Test
  void testUpdateWithAndWhere() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE (col3 = 3) AND (col4 = 4)",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3)).andWhere(Condition.equal("col4", 4)).toString());
  }

  @Test
  void testUpdateWithOrWhere() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE (col3 = 3) OR (col4 = 4)",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3)).orWhere(Condition.equal("col4", 4)).toString());
  }

  @Test
  void testUpdateWithXorWhere() {
    assertEquals("UPDATE table SET col1 = 1, col2 = 2 WHERE (col3 = 3) XOR (col4 = 4)",
        Update.into("table").set("col1", 1).set("col2", 2).where(Condition.equal("col3", 3)).xorWhere(Condition.equal("col4", 4)).toString());
  }
}
