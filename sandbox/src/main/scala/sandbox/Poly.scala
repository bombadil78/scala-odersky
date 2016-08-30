package sandbox

class Poly(val partialTerms: Map[Int, Double]) {
  val fullTerms = partialTerms.withDefaultValue(0.0)
  def this(params: (Int, Double)*) = this(params.toMap)

  // first implmentation
  def + (other: Poly) = new Poly(fullTerms ++ (other.fullTerms map adjust))
  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + fullTerms(exp))
  }

  // implementation with foldLeft
  def ++ (other: Poly): Poly = new Poly((other.fullTerms foldLeft fullTerms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    val oldValue = terms(exp)
    val newValue = oldValue + coeff
    terms.updated(exp, newValue)
  }

  override def toString: String = fullTerms.toList.sorted.reverse.map(x => x._1 + "x^" + x._2).mkString(" + ")
  override def equals(other: Any) = {
    if (other == null) false
    else if (!other.isInstanceOf[Poly]) false
    else {
      val otherValue = other.asInstanceOf[Poly]
      otherValue.fullTerms == fullTerms
    }
  }
}