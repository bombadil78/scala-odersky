package sandbox

import java.util.NoSuchElementException

trait MyList[T] {
  def isEmpty : Boolean
  def head : T
  def tail : MyList[T]
}

class Cons[T](val head: T, val tail: MyList[T]) extends MyList[T] {
  override def isEmpty : Boolean = false
}

class MyNil[T] extends MyList[T] {
  override def isEmpty : Boolean = true
  override def head : Nothing = throw new NoSuchElementException("Nil.head")
  override def tail : Nothing = throw new NoSuchElementException("Nil.tail")
}

object MyOwnList {
  def apply[T]() = new MyNil
  def apply[T](t: T) = new Cons(t, new MyNil)
  def apply[T](t1: T, t2: T) = new Cons(t1, new Cons(t2, new MyNil))
}
