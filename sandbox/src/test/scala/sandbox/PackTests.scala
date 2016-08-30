package sandbox

import org.scalatest.FunSuite

class PackTests extends FunSuite {

  trait TestData {
    val list = List("a", "a", "a", "b", "b", "c", "c")
  }

  test("pack()") {
    new TestData {
      assert(Pack.pack(list) === List(List("a", "a", "a"), List("b", "b"), List("c", "c")))
    }
  }

  test("encode()") {
    new TestData {
      assert(Pack.encode(list) === List(("a", 3), ("b", 2), ("c", 2)))
    }
  }

}
