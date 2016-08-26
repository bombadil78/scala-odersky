package sandbox

object Reduce {

  def reduceLeft[T](list: List[T], z: T)(op: (T, T) => T): T = list match {
    case List() => z
    case x :: xs => reduceLeft(xs, op(z, x))(op)
  }

  def reduceRight[T](list: List[T], z: T)(op: (T, T) => T): T = list match {
    case List() => z
    case default => reduceRight(list.init, op(z, list.last))(op)
  }

}
