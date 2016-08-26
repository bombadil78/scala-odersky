package sandbox

object Pack {

  def pack[T](l: List[T]): List[List[T]] = l match {
    case List() => List()
    case x :: xs => {
      val (first: List[T], second: List[T]) = l partition(y => y == x)
      first :: pack(second)
    }
  }

  def encode[T](l: List[T]): List[(T, Int)] = pack(l).map(sl => (sl(0), sl.length))
}
