package sandbox

object Prime {

  def isPrime(n: Int): Boolean = n match {
    case 1 => true
    case default => (2 until n) forall(i => n%i != 0)
  }

}
