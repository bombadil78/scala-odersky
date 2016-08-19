package sandbox

import org.scalatest.FunSuite

class MyExpressionTests extends FunSuite {

  trait TestData {
    val one = new MyNumber(1)
    val two = new MyNumber(2)
    val onePlusTwo = new MySum(one, two)
    val three = new MyNumber(3)
    val onePlusTwoPlusThree = new MySum(onePlusTwo, three)
    val twoTimesThree = new MyProduct(two, three)
  }

  test("some tests") {
    new TestData {
      assert(one.evaluate === 1)
      assert(two.evaluate === 2)
      assert(three.evaluate === 3)
      assert(onePlusTwo.evaluate === 3)
      assert(onePlusTwoPlusThree.evaluate === 6)
      assert(onePlusTwoPlusThree.evaluate === 6)

      Show.show(one)
      Show.show(two)
      Show.show(onePlusTwo)
      Show.show(three)
      Show.show(onePlusTwoPlusThree)
      Show.show(twoTimesThree)
    }
  }

}
