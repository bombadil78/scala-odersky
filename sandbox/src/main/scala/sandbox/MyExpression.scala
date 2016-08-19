package sandbox

trait MyExpression {
  def evaluate: Integer
}

case class MyNumber(override val evaluate: Integer) extends MyExpression {
}

case class MySum(val leftOp: MyExpression, val rightOp: MyExpression) extends MyExpression {
  override def evaluate: Integer = leftOp.evaluate + rightOp.evaluate
}

case class MyProduct(val leftOp: MyExpression, val rightOp: MyExpression) extends MyExpression {
  override def evaluate: Integer = leftOp.evaluate * rightOp.evaluate
}

object Show {

  def show(expr: MyExpression) = {

    def aggregate(expr: MyExpression) : String = {
      val s: String = expr match {
        case MyNumber(n) => n.toString
        case MySum(leftOp, rightOp) => aggregate(leftOp) + " + " + aggregate(rightOp)
        case MyProduct(leftOp, rightOp) => aggregate(leftOp) + " * " + aggregate(rightOp)
      }
      s
    }

    println(aggregate(expr))

  }
}
