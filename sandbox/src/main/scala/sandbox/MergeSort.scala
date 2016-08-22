package sandbox

object MergeSort {

  def sort(list: List[Int]): List[Int] = {
    val split: Int = list.size / 2
    if (split == 0) {
      list
    } else {
      val (firstPart, secondPart) = list splitAt split
      merge(sort(firstPart), sort(secondPart))
    }
  }

  private def merge(firstList: List[Int], secondList: List[Int]): List[Int] = (firstList, secondList) match {
    case (List(), _) => secondList
    case (_, List()) => firstList
    case default =>
      if (firstList.head < secondList.head) firstList.head :: merge(firstList.tail, secondList)
      else secondList.head :: merge(firstList, secondList.tail)
  }

}
