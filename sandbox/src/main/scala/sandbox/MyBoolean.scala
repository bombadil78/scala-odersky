package sandbox

abstract class MyBoolean {
  def ifThenElse[T](x: => T, y: => T) : T
  def && (b: => MyBoolean) : MyBoolean = ifThenElse(b, myFalse)
  def || (b: => MyBoolean) : MyBoolean = ifThenElse(myTrue, b)
  def unary_! : MyBoolean = ifThenElse(myFalse, myTrue)
  def == (b: MyBoolean) : MyBoolean = ifThenElse(b, b.unary_!)
  def != (b: MyBoolean) : MyBoolean = ifThenElse(b.unary_!, b)
  def > (b: MyBoolean) : MyBoolean = ifThenElse(b.unary_!, myFalse)
  def < (b: MyBoolean) : MyBoolean = ifThenElse(myFalse, b)
}

object myFalse extends MyBoolean {
  override def ifThenElse[T](x: => T, y: => T) : T = y
}

object myTrue extends MyBoolean {
  override def ifThenElse[T](x: => T, y: => T) : T = x
}

