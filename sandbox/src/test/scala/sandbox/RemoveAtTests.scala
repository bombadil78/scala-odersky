package sandbox

import org.scalatest.FunSuite

class RemoveAtTests extends FunSuite {

  trait TestData {
    val list = List(1, 2, 3, 4, 5)
  }

  test("removeAt() on first element") {
    new TestData {
      assert(RemoveAt.doIt(0, list) === List(2, 3, 4, 5))
    }
  }

  test("removeAt() on element in the middle") {
    new TestData {
      assert(RemoveAt.doIt(2, list) === List(1, 2, 4, 5))
    }
  }

  test("removeAt() on last element") {
    new TestData {
      assert(RemoveAt.doIt(4, list) === List(1, 2, 3, 4))
    }
  }

  test("removeAt() on invalid index") {
    intercept[IndexOutOfBoundsException] {
      RemoveAt.doIt(3, List(1, 2))
    }
  }

  test("removeAt() on empty list") {
    intercept[IndexOutOfBoundsException] {
      RemoveAt.doIt(0, List())
    }
  }

}
