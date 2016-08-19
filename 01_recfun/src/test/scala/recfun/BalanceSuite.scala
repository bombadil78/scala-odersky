package recfun

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance
  import Main.purge

  test("my slice() test") {
    assert("abcdef".toList.slice(1, "abcdef".size - 1) === "bcde".toList)
  }

  test("purge: abc(())def") {
    assert(purge("abc(())def".toList) === "(())".toList)
  }

  test("balance: empty string is balanced") {
    assert(balance("".toList) === true)
  }

  test("purge it") {
    assert(purge("(if (zero? x) max (/ 1 x))".toList) == "(()())".toList)
  }

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: counting is not enough") {
    assert(!balance("())(".toList))
  }

  test("balance: i should terminate...") {
    assert(!balance("((".toList))
  }

}
