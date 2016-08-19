package sandbox

import org.scalatest.FunSuite

class MyListTests extends FunSuite {

  trait TestData {
    val v0 = MyList()
    val v1 = MyList(1)
    val v5 = MyList(1, 2, 3, 4, 5)
  }

  test("MyList()") {
    val v1 = MyList(1, 2)
    val v2 = MyList("a", "b")
  }

  test("size()") {
    new TestData {
      assert(v0.size === 0)
      assert(v1.size === 1)
      assert(v5.size === 5)
    }
  }

}
