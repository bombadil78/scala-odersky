package sandbox

import org.scalatest.FunSuite

class NQueensTests extends FunSuite {

  test("nqueens(x) returns empty set for n in {2, 3}") {
    assert(NQueens.queens(2) === Set())
    assert(NQueens.queens(3) === Set())
  }

  test("nqueens(4) has two solutions") {
    assert(NQueens.queens(4).contains(List(1, 3, 0, 2)))
    assert(NQueens.queens(4).contains(List(2, 0, 3, 1)))
  }

  test("nqueens(5) has ten solutions") {
    assert(NQueens.queens(5).contains(List(0, 3, 1, 4, 2)))
    assert(NQueens.queens(5).contains(List(2, 0, 3, 1, 4)))
    assert(NQueens.queens(5).contains(List(0, 2, 4, 1, 3)))
    assert(NQueens.queens(5).contains(List(2, 4, 1, 3, 0)))
    assert(NQueens.queens(5).contains(List(1, 3, 0, 2, 4)))
    assert(NQueens.queens(5).contains(List(3, 0, 2, 4, 1)))
    assert(NQueens.queens(5).contains(List(4, 2, 0, 3, 1)))
    assert(NQueens.queens(5).contains(List(4, 1, 3, 0, 2)))
    assert(NQueens.queens(5).contains(List(3, 1, 4, 2, 0)))
    assert(NQueens.queens(5).contains(List(1, 4, 2, 0, 3)))
  }
}
