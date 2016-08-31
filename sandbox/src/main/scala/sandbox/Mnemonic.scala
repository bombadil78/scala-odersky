package sandbox

class Mnemonic(val dictionary: Set[String]) {

  val keys: Map[String, Char] = Map(
    "ABC" -> '2',
    "DEF" -> '3',
    "GHI" -> '4',
    "JKL" -> '5',
    "MNO" -> '6',
    "PQRS"-> '7',
    "TUV" -> '8',
    "WXYZ" -> '9'
  )

  val charCode: Map[Char, Char] = for ((str, digit) <- keys; char <- str) yield char -> digit
  def wordCode(input: String): String = input.toUpperCase() map charCode
  val wordsForNum: Map[String, Set[String]] = dictionary groupBy(w => wordCode(w).toString())
  def translate(digits: String): Set[String] = wordsForNum(digits)

}
