package sandbox

import org.scalatest.FunSuite

class PolyTests extends FunSuite {

  trait TestData {
    val p1 = new Poly(0 -> 10, 1 -> 10, 3 -> 10)
    val p2 = new Poly(1 -> 1, 2 -> 2, 3 -> 3)
  }

  test("simple + test") {
    new TestData {
      assert(p1 + p2 === new Poly(0 -> 10, 1-> 11, 2 -> 2, 3 -> 13))
    }
  }

  test("simple ++ test") {
    new TestData {
      assert(p1 ++ p2 === new Poly(0 -> 10, 1 -> 11, 2 -> 2, 3 -> 13))
    }
  }

}
