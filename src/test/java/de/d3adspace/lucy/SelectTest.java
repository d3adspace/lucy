package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectTest {

  @Test
  void testSelect() {
    assertEquals("SELECT * FROM table", Select.from("table").build());
  }

  @Test
  void testSelectMultiple() {
    assertEquals("SELECT * FROM table, table2", Select.from("table", "table2").build());
  }

  @Test
  void testSelectMultipleWithAlias() {
    assertEquals("SELECT * FROM table AS t1, table2 AS t2", Select.from("table AS t1", "table2 AS t2").build());
  }

  @Test
  void testSelectColumns() {
    assertEquals("SELECT col1, col2 FROM table", Select.from("table").columns("col1", "col2").build());
  }

  @Test
  void testSelectMultipleWithAliasAndColumns() {
    assertEquals("SELECT col1, col2 FROM table AS t1, table2 AS t2", Select.from("table AS t1", "table2 AS t2").columns("col1", "col2").build());
  }

  @Test
  void testSelectWithLimit() {
    assertEquals("SELECT * FROM table LIMIT 10", Select.from("table").limit(10).build());
  }

  @Test
  void testSelectWithOffset() {
    assertEquals("SELECT * FROM table OFFSET 10", Select.from("table").offset(10).build());
  }

  @Test
  void testSelectWithLimitAndOffset() {
    assertEquals("SELECT * FROM table LIMIT 10 OFFSET 10", Select.from("table").limit(10).offset(10).build());
  }

  @Test
  void testSelectWithOrder() {
    assertEquals("SELECT * FROM table ORDER BY col1 ASC", Select.from("table").orderBy("col1").build());
  }

  @Test
  void testSelectWithOrderAscending() {
    assertEquals("SELECT * FROM table ORDER BY col1 ASC", Select.from("table").orderBy("col1", true).build());
  }

  @Test
  void testSelectWithOrderDescending() {
    assertEquals("SELECT * FROM table ORDER BY col1 DESC", Select.from("table").orderBy("col1", false).build());
  }

  @Test
  void testSelectWithOrderAndLimit() {
    assertEquals("SELECT * FROM table ORDER BY col1 ASC LIMIT 10", Select.from("table").orderBy("col1").limit(10).build());
  }

  @Test
  void testSelectWithOrderAndOffset() {
    assertEquals("SELECT * FROM table ORDER BY col1 ASC OFFSET 10", Select.from("table").orderBy("col1").offset(10).build());
  }

  @Test
  void testSelectWithOrderAndLimitAndOffset() {
    assertEquals("SELECT * FROM table ORDER BY col1 ASC LIMIT 10 OFFSET 10", Select.from("table").orderBy("col1").limit(10).offset(10).build());
  }

  @Test
  void testSelectWithOrderAndLimitAndOffsetAndWhere() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' ORDER BY col1 ASC LIMIT 10 OFFSET 10", Select.from("table").where(Condition.equal("col1", "test")).orderBy("col1").limit(10).offset(10).build());
  }

  @Test
  void testSelectWithOrderAndLimitAndOffsetAndWhereAndGroupBy() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' GROUP BY col1 ORDER BY col1 ASC LIMIT 10 OFFSET 10", Select.from("table").where(Condition.equal("col1", "test")).groupBy("col1").orderBy("col1").limit(10).offset(10).build());
  }

  @Test
  void testSelectWithOrderAndLimitAndOffsetAndWhereAndGroupByAndHaving() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' HAVING COUNT(*) > 0 GROUP BY col1 ORDER BY col1 ASC LIMIT 10 OFFSET 10", Select.from("table").where(Condition.equal("col1", "test")).having(Condition.greaterThan("COUNT(*)", 0)).groupBy("col1").orderBy("col1").limit(10).offset(10).build());
  }

  @Test
  void testSelectWithWhere() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test'", Select.from("table").where(Condition.equal("col1", "test")).build());
  }

  @Test
  void testSelectWithWhereCompositeAnd() {
    assertEquals(
        "SELECT * FROM table WHERE col1 = 'test' AND col2 = 1",
        Select.from("table")
            .where(Condition.equal("col1", "test").and(Condition.equal("col2", 1)))
            .build()
    );
  }

  @Test
  void testSelectWithWhereCompositeOr() {
    assertEquals(
        "SELECT * FROM table WHERE col1 = 'test' OR col2 = 1",
        Select.from("table")
            .where(Condition.equal("col1", "test").or(Condition.equal("col2", 1)))
            .build()
    );
  }

  @Test
  void testSelectWithWhereCompositeAndOr() {
    assertEquals(
        "SELECT * FROM table WHERE col1 = 'test' AND col2 = 1 OR col3 = 2",
        Select.from("table")
            .where(Condition.equal("col1", "test").and(Condition.equal("col2", 1)).or(Condition.equal("col3", 2)))
            .build()
    );
  }

  @Test
  void testSelectWithWhereCompositeAndOrAndNot() {
    assertEquals(
        "SELECT * FROM table WHERE col1 = 'test' AND col2 = 1 OR col3 = 2 AND NOT col4 = 3",
        Select.from("table")
            .where(Condition.equal("col1", "test").and(Condition.equal("col2", 1)).or(Condition.equal("col3", 2)).and(Condition.not(Condition.equal("col4", 3))))
            .build()
    );
  }

  @Test
  void testSelectWithWhereCompositeAndOrAndNotAndIn() {
    assertEquals(
        "SELECT * FROM table WHERE col1 = 'test' AND col2 = 1 OR col3 = 2 AND NOT col4 = 3 AND col5 IN (1, 2, 3)",
        Select.from("table")
            .where(Condition.equal("col1", "test").and(Condition.equal("col2", 1)).or(Condition.equal("col3", 2)).and(Condition.not(Condition.equal("col4", 3))).and(Condition.in("col5", 1, 2, 3)))
            .build()
    );
  }

  @Test
  void testSelectWithWhereAndGroupBy() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' GROUP BY col1", Select.from("table").where(Condition.equal("col1", "test")).groupBy("col1").build());
  }

  @Test
  void testSelectWithWhereAndGroupByAndHaving() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' HAVING COUNT(*) > 0 GROUP BY col1", Select.from("table").where(Condition.equal("col1", "test")).groupBy("col1").having(Condition.greaterThan("COUNT(*)", 0)).build());
  }

  @Test
  void testSelectWithWhereAndGroupByAndHavingAndOrder() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' HAVING COUNT(*) > 0 GROUP BY col1 ORDER BY col1 ASC", Select.from("table").where(Condition.equal("col1", "test")).groupBy("col1").having(Condition.greaterThan("COUNT(*)", 0)).orderBy("col1").build());
  }

  @Test
  void testSelectWithWhereAndGroupByAndHavingAndOrderAndLimit() {
    assertEquals("SELECT * FROM table WHERE col1 = 'test' HAVING COUNT(*) > 0 GROUP BY col1 ORDER BY col1 ASC LIMIT 10", Select.from("table").where(Condition.equal("col1", "test")).groupBy("col1").having(Condition.greaterThan("COUNT(*)", 0)).orderBy("col1").limit(10).build());
  }

  @Test
  void testSelectWithBetween() {
    assertEquals("SELECT * FROM table WHERE col1 BETWEEN 1 AND 2", Select.from("table").where(Condition.between("col1", 1, 2)).build());
  }

  @Test
  void testSelectWithNotBetween() {
    assertEquals("SELECT * FROM table WHERE col1 NOT BETWEEN 1 AND 2", Select.from("table").where(Condition.notBetween("col1", 1, 2)).build());
  }

  @Test
  void testSelectWithIn() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3)", Select.from("table").where(Condition.in("col1", 1, 2, 3)).build());
  }

  @Test
  void testSelectWithNotIn() {
    assertEquals("SELECT * FROM table WHERE col1 NOT IN (1, 2, 3)", Select.from("table").where(Condition.notIn("col1", 1, 2, 3)).build());
  }

  @Test
  void testSelectWithInWithList() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3)", Select.from("table").where(Condition.in("col1", Arrays.asList(1, 2, 3))).build());
  }

  @Test
  void testSelectWithNotInWithList() {
    assertEquals("SELECT * FROM table WHERE col1 NOT IN (1, 2, 3)", Select.from("table").where(Condition.notIn("col1", Arrays.asList(1, 2, 3))).build());
  }

  @Test
  void testSelectWithInWithListAndWhere() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3) AND col2 = 1", Select.from("table").where(Condition.in("col1", Arrays.asList(1, 2, 3)).and(Condition.equal("col2", 1))).build());
  }

  @Test
  void testSelectWithNotInWithListAndWhere() {
    assertEquals("SELECT * FROM table WHERE col1 NOT IN (1, 2, 3) AND col2 = 1", Select.from("table").where(Condition.notIn("col1", Arrays.asList(1, 2, 3)).and(Condition.equal("col2", 1))).build());
  }

  @Test
  void testSelectWithInWithListAndWhereAndOr() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3) OR col2 = 1", Select.from("table").where(Condition.in("col1", Arrays.asList(1, 2, 3)).or(Condition.equal("col2", 1))).build());
  }

  @Test
  void testSelectWithNotInWithListAndWhereAndOr() {
    assertEquals("SELECT * FROM table WHERE col1 NOT IN (1, 2, 3) OR col2 = 1", Select.from("table").where(Condition.notIn("col1", Arrays.asList(1, 2, 3)).or(Condition.equal("col2", 1))).build());
  }

  @Test
  void testSelectWithInWithListAndWhereAndOrAndAnd() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3) OR col2 = 1 AND col3 = 1", Select.from("table").where(Condition.in("col1", Arrays.asList(1, 2, 3)).or(Condition.equal("col2", 1)).and(Condition.equal("col3", 1))).build());
  }

  @Test
  void testSelectWithNotInWithListAndWhereAndOrAndAnd() {
    assertEquals("SELECT * FROM table WHERE col1 NOT IN (1, 2, 3) OR col2 = 1 AND col3 = 1", Select.from("table").where(Condition.notIn("col1", Arrays.asList(1, 2, 3)).or(Condition.equal("col2", 1)).and(Condition.equal("col3", 1))).build());
  }

  @Test
  void testSelectWithInWithListAndWhereAndOrAndAndAnd() {
    assertEquals("SELECT * FROM table WHERE col1 IN (1, 2, 3) OR col2 = 1 AND col3 = 1 AND col4 = 1", Select.from("table").where(Condition.in("col1", Arrays.asList(1, 2, 3)).or(Condition.equal("col2", 1)).and(Condition.equal("col3", 1)).and(Condition.equal("col4", 1))).build());
  }
}
