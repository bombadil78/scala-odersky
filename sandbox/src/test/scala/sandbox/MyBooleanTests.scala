package sandbox

import org.scalatest.FunSuite

class MyBooleanTests extends FunSuite {

  test("basic tests") {
    assert(myTrue === myTrue)
    assert(myFalse === myFalse)
  }

  test("&&") {
    assert((myTrue && myTrue) === myTrue)
    assert((myTrue && myFalse) === myFalse)
    assert((myFalse && myTrue) === myFalse)
    assert((myFalse && myFalse) === myFalse)
  }

  test("||") {
    assert((myTrue || myTrue) === myTrue)
    assert((myTrue || myFalse) === myTrue)
    assert((myFalse || myTrue) === myTrue)
    assert((myFalse || myFalse) === myFalse)
  }

  test("not") {
    assert(!myTrue === myFalse)
    assert(!myFalse === myTrue)
  }

  test("eq") {
    assert((myTrue == myTrue) === myTrue)
    assert((myTrue == myFalse) === myFalse)
    assert((myFalse == myTrue) === myFalse)
    assert((myFalse == myFalse) === myTrue)
  }

  test("neq") {
    assert((myTrue != myTrue) === myFalse)
    assert((myTrue != myFalse) === myTrue)
    assert((myFalse != myTrue) === myTrue)
    assert((myFalse != myFalse) === myFalse)
  }

  test(">") {
    assert((myTrue > myTrue) === myFalse)
    assert((myTrue > myFalse) === myTrue)
    assert((myFalse > myTrue) === myFalse)
    assert((myFalse > myFalse) === myFalse)
  }

  test("<") {
    assert((myTrue < myTrue) === myFalse)
    assert((myTrue < myFalse) === myFalse)
    assert((myFalse < myTrue) === myTrue)
    assert((myFalse < myFalse) === myFalse)
  }
}

