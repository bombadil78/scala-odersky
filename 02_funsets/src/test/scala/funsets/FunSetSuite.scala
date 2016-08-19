package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s12 = (x: Int) => x == 1 || x == 2
    val s23 = (x: Int) => x == 2 || x == 3
    val s246 = (x: Int) => x == 2 || x == 4 || x == 6
    val s245 = (x: Int) => x == 2 || x == 4 || x == 5
    val s24 = (x: Int) => x == 2 || x == 4
    def even = (x: Int) => x%2 == 0
    def odd = (x: Int) => x%2 == 1
    def double = (x: Int) => 2 * x
  }

  test("singletonSet") {
    new TestSets {
      assert(contains(s1, 1), "singleton contains its element")
      assert(!contains(s1, 2), "singleton does not contain other elements")
    }
  }

  test("union") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "contains first element")
      assert(contains(s, 2), "contains second element")
      assert(!contains(s, 3), "does not contain other element")
    }
  }

  test("intersect") {
    new TestSets {
      val i = intersect(s12, s23)
      assert(contains(i, 2), "contains intersection")
      assert(!contains(i, 1), "only in set #1")
      assert(!contains(i, 3), "only in set #2")
    }
  }

  test("diff") {
    new TestSets {
      val i = diff(s12, s23)
      assert(contains(i, 1))
      assert(!contains(i, 2))
      assert(!contains(i, 3))

      val j = diff(s23, s12)
      assert(!contains(j, 1))
      assert(!contains(j, 2))
      assert(contains(j, 3))
    }
  }

  test("filter") {
    new TestSets {
      val i = filter(s12, even)
      assert(!contains(i, 1), "1 is not even")
      assert(contains(i, 2), "2 is even")
    }
  }

  test("forall") {
    new TestSets {
      val i = forall(s246, even)
      val j = forall(s245, even)
      assert(true == i, "246 are all even")
      assert(false == j, "245 are not all even")
    }
  }

  test("exists") {
    new TestSets {
      val i = exists(s245, odd)
      val j = exists(s246, odd)
      assert(true == i, "in 245 there exists odd")
      assert(false == j, "in 246 there does not exist odd")
    }
  }

  test("map") {
    new TestSets {
      val i = map(s12, x => 2*x)

    }
  }

}
