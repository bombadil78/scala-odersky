package sandbox

import org.scalatest.FunSuite

class MyListTests extends FunSuite {

  test("MyList()") {
    val v1 = MyOwnList(1, 2)
    val v2 = MyOwnList("a", "b")
  }

}
