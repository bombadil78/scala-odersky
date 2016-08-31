package forcomp


object Anagrams {

  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]

  val dictionary: List[Word] = loadDictionary

  def wordOccurrences(w: Word): Occurrences = (w.groupBy[Char](c => c.toLower) map (x => x._1 -> x._2.length) toList).sorted

  def sentenceOccurrences(s: Sentence): Occurrences = s flatMap (word => wordOccurrences(word))

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary groupBy[Occurrences] (word => wordOccurrences(word))

  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences(wordOccurrences(word))

  def combinations(occurrences: Occurrences): List[Occurrences] = {

    def split(el: (Char, Int)): Occurrences = {
      val (myChar, myCount) = el
      if (myCount == 0)
        List()
      else
        el :: split((myChar, myCount - 1))
    }

    if (occurrences.isEmpty) {
      List(List())
    } else {
      val oldCombinations = combinations(occurrences.tail)
      val newElements = split(occurrences.head)
      val newCombinations = for {
        newElement <- newElements
        oldCombination <- oldCombinations
      }
        yield newElement :: oldCombination
      newCombinations ::: oldCombinations
    }
  }

  // todo: improve using foldLeft (=> see examples)
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {

    val subtractedFromX = for {
      xElem <- x.toMap
      yElem <- y.toMap
      if (xElem._1 == yElem._1)
    }
      yield (xElem._1, xElem._2 - yElem._2)

    val subtractedFromXWithoutZeroOrBelow = subtractedFromX.filter(x => x._2 > 0)

    val onlyInX = for {
      xElem <- x.toMap
      if (!y.toMap.contains(xElem._1))
    }
      yield (xElem)

    (onlyInX ++ subtractedFromXWithoutZeroOrBelow).toList
  }

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???
}
