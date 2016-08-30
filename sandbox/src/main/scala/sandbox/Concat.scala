package sandbox

object Concat {

  // :: is binary, but not symmetric, so we cannot foldLeft here
  def concat(l1: List[String], l2: List[String]): List[String] = (l1 foldRight l2) (_ :: _)

}
