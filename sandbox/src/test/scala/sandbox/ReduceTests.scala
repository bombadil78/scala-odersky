package sandbox

import org.scalatest.FunSuite

class ReduceTests extends FunSuite {

  trait TestData {
    val intList: List[Int] = List(1, 2, 3, 4)
    val stringList: List[String] = List("a", "a", "b", "c", "c")

    def sum(x: Int, y: Int): Int = x + y
    def multiply(x: Int, y: Int): Int = x * y
    def concat(s: String, t: String): String = s + t

    val zSum = 0;
    val zMulti = 1;
    val zConcat = "";
  }

  test("reduceLeft() on empty list returns initial value") {
    new TestData {
      assert(Reduce.reduceLeft(List(), zSum)(sum) === 0)
    }
  }

  test("reduceLeft() to sum integers") {
    new TestData {
      assert(Reduce.reduceLeft(intList, zSum)(sum) === 10)
    }
  }

  test("reduceLeft() to multiply integers") {
    new TestData {
      assert(Reduce.reduceLeft(intList, zMulti)(multiply) === 24)
    }
  }

  test("reduceLeft() to concat identical strings") {
    new TestData {
      assert(Reduce.reduceLeft(stringList, zConcat)(concat) === "aabcc")
    }
  }

}
