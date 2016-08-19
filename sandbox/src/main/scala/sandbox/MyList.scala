package sandbox

import java.util.NoSuchElementException

trait MyList[T] {
  def isEmpty : Boolean
  def head : T
  def tail : MyList[T]
}

class MyCons[T](val head: T, val tail: MyList[T]) extends MyList[T] {
  override def isEmpty : Boolean = false
}

class MyNil[T] extends MyList[T] {
  override def isEmpty : Boolean = true
  override def head : Nothing = throw new NoSuchElementException("Nil.head")
  override def tail : Nothing = throw new NoSuchElementException("Nil.tail")
}

object MyList {
  def apply[T](): MyList[T] = new MyNil
  def apply[T](t: T) = new MyCons(t, new MyNil)
  def apply[T](t1: T, t2: T) = new MyCons(t2, new MyCons(t1, new MyNil))
  def apply[T](t1: T, t2: T, t3: T) = new MyCons(t3, new MyCons(t2, new MyCons(t1, new MyNil)))
  def apply[T](t1: T, t2: T, t3: T, t4: T) = new MyCons(t4, new MyCons(t3, new MyCons(t2, new MyCons(t1, new MyNil))))
  def apply[T](t1: T, t2: T, t3: T, t4: T, t5: T) = new MyCons(t5, new MyCons(t4, new MyCons(t3, new MyCons(t2, new MyCons(t1, new MyNil)))))
}
