package sandbox

import org.scalatest.FunSuite

class PrimeTests extends FunSuite {

  test("isPrime(): 1 is not by definition") {
    assert(Prime.isPrime(1) === true)
  }

  test("isPrime(): 2, 3, 5, 7, 31, 41, 73 are primes") {
    assert(Prime.isPrime(2) === true)
    assert(Prime.isPrime(3) === true)
    assert(Prime.isPrime(5) === true)
    assert(Prime.isPrime(7) === true)
    assert(Prime.isPrime(31) === true)
    assert(Prime.isPrime(41) === true)
    assert(Prime.isPrime(73) === true)
  }

  test("isPrime(): 4, 6, 10, 25, 39, 99, 100 are no primes") {
    assert(Prime.isPrime(4) === false)
    assert(Prime.isPrime(6) === false)
    assert(Prime.isPrime(10) === false)
    assert(Prime.isPrime(25) === false)
    assert(Prime.isPrime(39) === false)
    assert(Prime.isPrime(99) === false)
    assert(Prime.isPrime(100) === false)
  }
}
