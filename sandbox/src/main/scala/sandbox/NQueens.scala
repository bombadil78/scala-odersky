package sandbox

object NQueens {

  def queens(n: Int): Set[List[Int]] = {

    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens
    }

    placeQueens(n)
  }

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val currentRow = queens.length
    val solution = (currentRow - 1 to 0 by -1) zip queens
    solution forall {
      case (r, c) => {
        val horizontalAbsoluteDistance = math.abs(col - c)
        val verticalAbsoluteDistance = currentRow - r
        col != c && horizontalAbsoluteDistance != verticalAbsoluteDistance
      }
    }
  }
}

