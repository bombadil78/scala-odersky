package sandbox

class Fruit {
  override def toString : String = "fruit(" + this.hashCode() + ")"
}

class Apple extends Fruit {
  override def toString : String = "apple(" + this.hashCode() + ")"
}

class Pear extends Fruit {
  override def toString : String = "pear(" + this.hashCode() + ")"
}

abstract class FruitList[+T <: Fruit] {
  def head : T
  def tail : FruitList[T]
  def prepend[U >: T <: Fruit](superFruit: U) : FruitList[U] = new FruitCons[U](superFruit, this)

  def listAsString : String = {
    if (this != Nil) {
      println(head.toString)
      head.toString() + " " + tail.listAsString
    } else {
      println("a the end now...")
      ""
    }
  }

  override def toString : String = "[ " + listAsString + " ]"

}

class FruitCons[T <: Fruit](val head : T, val tail : FruitList[T]) extends FruitList[T] {

}

object Nil extends FruitList[Nothing] {
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
}
