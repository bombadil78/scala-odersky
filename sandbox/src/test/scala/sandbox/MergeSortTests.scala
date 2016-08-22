package sandbox

import org.scalatest.FunSuite

class MergeSortTests extends FunSuite {

  trait TestData {
    val sorted = List(1, 2, 3, 4, 5)
    val reversed = sorted.reverse
    val shuffled = List(2, 1, 3, 5, 4)
  }

  test("sort() on empty list") {
    new TestData {
      assert(MergeSort.sort(List()) === List())
    }
  }

  test("sort() on simple sorted list") {
    new TestData {
      assert(MergeSort.sort(sorted) === sorted)
    }
  }

  test("sort() on simple reversed list") {
    new TestData {
      assert(MergeSort.sort(reversed) === sorted)
    }
  }

  test("sort() on simple shuffled list") {
    new TestData {
      assert(MergeSort.sort(shuffled) === sorted)
    }
  }

}
