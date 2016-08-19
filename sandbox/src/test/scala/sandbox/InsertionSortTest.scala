package sandbox

import org.scalatest.FunSuite

class InsertionSortTest extends FunSuite {

  trait TestData {
    val emptyList: MyList[Integer] = MyList()
    val oneElementList: MyList[Integer] = MyList(1)
    val alreadySorted: MyList[Integer] = MyList(1, 2, 3, 4, 5)
    val unsorted: MyList[Integer] = MyList(2, 22, 4, 77, 3)
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
      assert(InsertionSort.sort(alreadySorted) === alreadySorted)
    }
  }

  test("sort unsorted") {
    new TestData {
      val sorted = InsertionSort.sort(unsorted)
      assert(sorted.head.toString() === 2)
      assert(sorted.tail.head.toString() === 3)
      assert(sorted.tail.tail.head.toString() === 4)
      assert(sorted.tail.tail.tail.head.toString() === 22)
      assert(sorted.tail.tail.tail.tail.head.toString() === 77)
    }
  }

}
