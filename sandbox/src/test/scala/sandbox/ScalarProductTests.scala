package sandbox

import org.scalatest.FunSuite

class ScalarProductTests extends FunSuite {

  trait TestData {
    val v0 = Vector();
    val v1 = Vector(1, 2, 3);
    val v2 = Vector(3, 2, 1);
    val v3 = Vector(0, 1, 2, 3)
  }

  test("computeWithZip() for vectors of different length throws exception") {
    new TestData {
      intercept[RuntimeException] {
        ScalarProduct.computeWithMap(v1, v3);
      }
    }
  }

  test("computeWithZip() for empty vectors returns zero") {
    new TestData {
      assert(ScalarProduct.computeWithMap(v0, v0) === 0)
    }
  }

  test("computeWithZip() for simple case") {
    new TestData {
      assert(ScalarProduct.computeWithMap(v1, v1) === 14)
      assert(ScalarProduct.computeWithMap(v1, v2) === 10)
    }
  }

  test("computeWithFor() for vectors of different length throws exception") {
    new TestData {
      intercept[RuntimeException] {
        ScalarProduct.computeWithFor(v1, v3);
      }
    }
  }

  test("computeWithFor() for empty vectors returns zero") {
    new TestData {
      assert(ScalarProduct.computeWithFor(v0, v0) === 0)
    }
  }

  test("computeWithFor() for simple case") {
    new TestData {
      assert(ScalarProduct.computeWithFor(v1, v1) === 14)
      assert(ScalarProduct.computeWithFor(v1, v2) === 10)
    }
  }

}
