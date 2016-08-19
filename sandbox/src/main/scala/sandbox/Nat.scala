package sandbox

abstract class Nat {
  lazy val succ : NonZero = new NonZero(this)
  def isZero: Boolean
  def predecessor : Nat
  def successor : Nat = succ
  def + (that: Nat) : Nat
  def - (that: Nat) : Nat
  def getIntValue() : Integer
  def toString() : String
}

object Zero extends Nat {
  override def isZero = true
  override def predecessor = this
  override def + (that: Nat) = that
  override def - (that: Nat) = that
  override def getIntValue() = 0
  override def toString() : String = "0"
}

class NonZero(val pred: Nat) extends Nat {
  def isZero = false
  override def predecessor: Nat = pred
  override def + (that: Nat): Nat =
    if (that.isZero)
      this
    else
      successor + that.predecessor

  override def - (that: Nat): Nat =
    if (that.isZero)
      this
    else
      predecessor - that.predecessor

  override def getIntValue() : Integer = predecessor.getIntValue() + 1
  override def toString() : String = getIntValue().toString
}

