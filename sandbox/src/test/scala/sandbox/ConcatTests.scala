package sandbox

import org.scalatest.FunSuite

class ConcatTests extends FunSuite {

  trait TestData {
    val abcd = List("a", "b", "c", "d")
    val defg = List("d", "e", "f", "g")
  }

  test("concat()") {
    new TestData {
      assert(Concat.concat(abcd, defg) === (abcd ::: defg))
    }
  }
}
