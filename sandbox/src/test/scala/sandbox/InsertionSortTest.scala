package sandbox

import org.scalatest.FunSuite

class InsertionSortTest extends FunSuite {

  trait TestData {
    val emptyList: MyList[Integer] = MyList()
    val oneElementList: MyList[Integer] = MyList(1)
    val alreadySorted: MyList[Integer] = MyList(1, 2, 3, 4, 5)
    val unsorted: MyList[Integer] = MyList(2, 22, 4, 77, 3)
    val unsortedIntList: List[Int] = List(2, 22, 4, 77, 3)
  }

  test("sort empty list") {
    new TestData {
      assert(InsertionSort.sort(emptyList) === emptyList)
    }
  }

  test("sort single element list") {
    new TestData {
      assert(InsertionSort.sort(oneElementList) === oneElementList)
    }
  }

  test("sort already sorted list") {
    new TestData {
      val sorted = InsertionSort.sort(alreadySorted)
      assert(sorted.head.toString() === "1")
      assert(sorted.tail.head.toString() === "2")
      assert(sorted.tail.tail.head.toString() === "3")
      assert(sorted.tail.tail.tail.head.toString() === "4")
      assert(sorted.tail.tail.tail.tail.head.toString() === "5")
    }
  }

  test("sort unsorted") {
    new TestData {
      val sorted = InsertionSort.sort(unsorted)
      assert(sorted.size === 5)
      assert(sorted.head.toString() === "2")
      assert(sorted.tail.head.toString() === "22")
      assert(sorted.tail.tail.head.toString() === "3")
      assert(sorted.tail.tail.tail.head.toString() === "4")
      assert(sorted.tail.tail.tail.tail.head.toString() === "77")
    }
  }

  test("sort unsorted for InsertionSort2") {
    new TestData {
      val sorted = InsertionSort2.sort(unsortedIntList)
      assert(sorted.size === 5)
      assert(sorted.head === 2)
      assert(sorted.tail.head === 3)
      assert(sorted.tail.tail.head === 4)
      assert(sorted.tail.tail.tail.head === 22)
      assert(sorted.tail.tail.tail.tail.head === 77)
    }
  }

}
