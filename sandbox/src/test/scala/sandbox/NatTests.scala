package sandbox

import org.scalatest.FunSuite

class NatTests extends FunSuite {

  trait TestData {
    val zero = Zero
    val one = zero.successor
    val two = zero.successor.successor
    val three = two.successor
    val four = three.successor.predecessor.successor
  }

  test("isZero") {
    assert(Zero.isZero)
    assert(!new NonZero(Zero).isZero)
  }

  test("predecessor / successor") {
    new TestData {
      assert(zero.predecessor === Zero)
      assert(one.predecessor === Zero)
      assert(two.predecessor === one)
      assert(three.predecessor === two)

      assert(zero.successor === one)
      assert(one.successor === two)
      assert(two.successor === three)
      assert(three.successor === four)
      assert(four.successor !== null)
    }
  }

  test("addition with zeroes") {
    new TestData {
      assert((zero + zero) === zero)
      assert((zero + one) === one)
      assert((one + zero) === one)
    }
  }

  test("normal addition") {
    new TestData {
      assert((one + one) === two)
      assert((two + one) === three)
      assert((one + two) === three)
      assert((two + two) === four)
    }
  }

  test("substraction with zero") {
    new TestData {
      assert((zero - zero) === zero)
      assert((zero - one) === one)
      assert((one - zero) === one)
    }
  }
}