package sandbox

object ScalarProduct {

  def computeWithMap(v1: Vector[Int], v2: Vector[Int]): Int = {
    if (v1.length != v2.length) throw new RuntimeException("Vectors must have equal length!")
    ((v1 zip v2) map (v => v._1 * v._2)).sum
  }

  def computeWithFor(v1: Vector[Int], v2: Vector[Int]): Int = {
    if (v1.length != v2.length) throw new RuntimeException("Vectors must have equal length!")
    val s: Vector[Int] = for {
      (i, j) <- v1 zip v2
    }
      yield (i * j)
    s.sum
  }

}
