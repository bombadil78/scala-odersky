package sandbox

import org.scalatest.FunSuite

class AnaTests extends FunSuite {

  trait TestData {
    val s: Set[Int] = Set(1, 2, 3)
    val ss = Set(Set(), Set(1), Set(2), Set(3), Set(1, 2), Set(1, 3), Set(2, 3), Set(1, 2, 3))
    val sss = Set(Set(Set(), Set(1, 2, 3)), Set(Set(1), Set(2, 3)), Set(Set(2), Set(1, 3)), Set(Set(3), Set(1, 2)))
  }

  test("generate subsets of a set") {
    new TestData {
      assert((Ana.subSets(s)) === ss)
    }
  }

  test("generate combinations of subsets") {
    new TestData {
      assert(Ana.combine(s) === sss)
    }
  }

}
