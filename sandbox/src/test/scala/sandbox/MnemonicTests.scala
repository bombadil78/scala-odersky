package sandbox

import org.scalatest.FunSuite

class MnemonicTests extends FunSuite {

  trait TestData {
    val aceOrBad = Set("ACE", "BAD")
    val otto = Set("OTTO")
    val fire = Set("FIRE")
    val yes = Set("YES")
    val noOrOn = Set("NO", "ON")

    val aceOrBadNumber: Int = 223
    val ottoNumber: Int = 6886
    val fireNumber: Int = 3473
    val yesNumber: Int = 937
    val noOrOnNumber: Int = 66

    val emptyPhone = new Mnemonic(Set())
    val simplePhone = new Mnemonic(aceOrBad ++ otto ++ fire ++ yes ++ noOrOn)
  }

  test("empty phone has no mnemonics and throws exception") {
    new TestData {
      intercept[NoSuchElementException] {
        emptyPhone.translate(aceOrBadNumber)
      }
      intercept[NoSuchElementException] {
        emptyPhone.translate(ottoNumber)
      }
      intercept[NoSuchElementException] {
        emptyPhone.translate(fireNumber)
      }
      intercept[NoSuchElementException] {
        emptyPhone.translate(yesNumber)
      }
      intercept[NoSuchElementException] {
        emptyPhone.translate(noOrOnNumber)
      }
    }
  }

  test("simple phone has mnemonics") {
    new TestData {
      assert(simplePhone.translate(aceOrBadNumber) === aceOrBad)
      assert(simplePhone.translate(ottoNumber) === otto)
      assert(simplePhone.translate(fireNumber) === fire)
      assert(simplePhone.translate(yesNumber) === yes)
      assert(simplePhone.translate(noOrOnNumber) === noOrOn)
    }
  }
}