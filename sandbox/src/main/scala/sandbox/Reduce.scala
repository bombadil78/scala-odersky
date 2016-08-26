package sandbox

object Reduce {

  def reduceLeft[T](list: List[T], z: T)(op: (T, T) => T): T = list match {
    case List() => z
    case x :: xs => reduceLeft(xs, op(z, x))(op)
  }

}
