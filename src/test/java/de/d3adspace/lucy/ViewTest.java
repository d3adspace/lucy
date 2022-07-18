package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ViewTest {
  @Test
  void test() {
    var view = View.named("test").createAs(Select.from("test"));;
    assertEquals("CREATE VIEW test AS SELECT * FROM test", view.toString());
  }

  @Test
  void testWithWhere() {
    var view = View.named("test").createAs(Select.from("test").where(Condition.equal("id", 1)));
    assertEquals("CREATE VIEW test AS SELECT * FROM test WHERE id = 1", view.toString());
  }

  @Test
  void testWithWhereAndReplaceIfExists() {
    var view = View.named("test").replaceIfExists().createAs(Select.from("test").where(Condition.equal("id", 1)));
    assertEquals("CREATE VIEW OR REPLACE test AS SELECT * FROM test WHERE id = 1", view.toString());
  }

  @Test
  void testDrop() {
    var view = View.drop("test");
    assertEquals("DROP VIEW test", view.toString());
  }
  
  @Test
  void testInnerDrop() {
    var view = View.named("test").drop();
    assertEquals("DROP VIEW test", view.toString());
  }
}
