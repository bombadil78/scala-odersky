package sandbox

object InsertionSort {

  def sort[T](list: MyList[T]): MyList[T] =
    if (list.isEmpty || list.tail.isEmpty)
      list
    else
      insert(list.head, sort(list.tail))

  private def insert[T](elem: T, someList: MyList[T]): MyList[T] =
    if (someList.isEmpty)
      MyList(elem)
    else if (elem.toString() > someList.head.toString)
      new MyCons(someList.head, insert(elem, someList.tail))
    else
      new MyCons(elem, someList)

}

object InsertionSort2 {

  def sort(list: List[Int]): List[Int] = list match {
    case List() => list
    case x :: xs => insert(x, sort(xs))
  }

  private def insert(elem: Int, list: List[Int]): List[Int] = list match {
    case List() => List(elem)
    case x :: xs => if (elem > x) x :: insert(elem, xs) else elem :: list
  }

}