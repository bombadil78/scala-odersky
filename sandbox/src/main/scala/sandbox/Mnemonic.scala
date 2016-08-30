package sandbox

class Mnemonic(val dictionary: Set[String]) {

  val keys: Map[String, Int] = Map(
    "ABC" -> 2,
    "DEF" -> 3,
    "GHI" -> 4,
    "JKL" -> 5,
    "MNO" -> 6,
    "PQRS"-> 7,
    "TUV" -> 8,
    "WXYZ" -> 9
  )

  val lookup: Map[Int, Set[String]] = dictionary.groupBy((s: String) => getCode(s.toList))

  def translate(input: Int): Set[String] = lookup(input)

  private def getCode(l: List[Char]): Int = toIntValue(getCodeAsList(l))

  private def getCodeAsList(l: List[Char]): List[Int] = l match {
    case List() => List()
    case x :: xs => {
      val found = keys.find(r => r._1.contains(x))
      found match {
        case Some(x) => x._2 :: getCodeAsList(xs)
        case None => throw new RuntimeException()
      }
    }
  }

  private def toIntValue(l: List[Int]): Int = ((l zip (l.length-1 to 0 by -1)) map(x => x._1 * math.pow(10, x._2))).sum.toInt
}
