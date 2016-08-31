package sandbox

object Ana {

  /// generate subsets of a set
  def subSets[T](s: Set[T]): Set[Set[T]] =
    if (s.isEmpty) {
      Set(Set())
    } else {
      val oldSubSets = subSets(s.tail)
      val newSubSets = oldSubSets map (old => old + s.head)
      oldSubSets ++ newSubSets
    }

  // generate possible combinations how a subset can be partitioned into sets of two subsets
  def combine[T](s: Set[T]): Set[Set[Set[T]]] = {
    val ss = subSets(s)
    ss map (el => Set(el, s -- el))
  }

}
