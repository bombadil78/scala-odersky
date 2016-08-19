package recfun

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  def pascal(c: Int, r: Int): Int =
    if (c < 0 || c > r)
      0
    else if (c == 0 && r == 0)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)

  def balance(chars: List[Char]): Boolean = myBalance(purge(chars))

  def purge(chars: List[Char]): List[Char] = chars.filter(c => c == '(' || c == ')')

  def myBalance(chars: List[Char]) : Boolean = {

    def reduce(tmpChars: List[Char]): List[Char] =
      if (
        tmpChars.isEmpty ||
        tmpChars.tail.isEmpty ||
        tmpChars.head == ')' ||
        (tmpChars.tail.head == '(' && tmpChars.tail.tail.isEmpty)
      )
        tmpChars
      else if (tmpChars.head == '(' && tmpChars.tail.head == ')')
        reduce(tmpChars.tail.tail)
      else if (tmpChars.head == '(' && tmpChars.tail.head == '(')
        reduce(List(tmpChars.head) ++ reduce(tmpChars.tail))
      else
        throw new RuntimeException("Never here ...")

    reduce(chars).isEmpty
  }

  def countChange(money: Int, coins: List[Int]) : Int =
    if (money == 0)
      1
    else if (money < 0 || coins.isEmpty)
      0
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)

}