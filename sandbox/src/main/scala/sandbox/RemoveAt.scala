package sandbox

object RemoveAt {

  def doIt(i: Int, l: List[Int]): List[Int] = l match {
    case List() => throw new IndexOutOfBoundsException
    case x :: xs => if (i == 0) xs else x :: doIt(i - 1, xs)
  }

}
