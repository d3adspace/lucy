package de.d3adspace.lucy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ViewTest {
  @Test
  void test() {
    View view = View.named("test").as(Select.from("test"));;
    assertEquals("CREATE VIEW test AS SELECT * FROM test", view.toString());
  }

  @Test
  void test2() {
    View view = View.named("test");
    view.as(Select.from("test").where(Condition.equal("id", 1)));
    assertEquals("CREATE VIEW test AS SELECT * FROM test WHERE id = 1", view.toString());
  }
}
