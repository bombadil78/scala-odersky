package sandbox

import org.scalatest.FunSuite

class FruitTests extends FunSuite {

  trait TestData {
    val fruit = new Fruit
    val apple = new Apple
    val pear = new Pear
    val emptyFruitList : FruitList[Fruit] = Nil
    val emptyAppleList : FruitList[Apple] = Nil
  }

  test("can prepend apple and pear in fruit list") {
    new TestData {
      val fl0 : FruitList[Fruit] = emptyFruitList.prepend(fruit).prepend(apple)
      val fl1 : FruitList[Fruit] = emptyAppleList.prepend(apple).prepend(fruit)

      // val al0 : FruitList[Apple] = emptyFruitList => is not subtype
      val al1 : FruitList[Apple] = emptyAppleList.prepend(apple).prepend(apple)

    }
  }

}
