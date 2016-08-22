package patmat

object Huffman {

  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree

  def weight(tree: CodeTree): Int = tree match {
    case Leaf(c: Char, w: Int) => w
    case Fork(l, r, c, w) => weight(l) + weight(r)
  }
  
  def chars(tree: CodeTree): List[Char] = tree match {
    case Leaf(c, w) => List(c)
    case Fork(l, r, c, w) => chars(l) ::: chars(r)
  }
  
  def makeCodeTree(left: CodeTree, right: CodeTree) = Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

  def string2Chars(str: String): List[Char] = str.toList

  def times(chars: List[Char]): List[(Char, Int)] = {
    val sorted = chars.sortWith((a, b) => (a < b)).map(c => (c, 1))

    def merge(t1: (Char, Int), t2: (Char, Int)): List[(Char, Int)] = if (t1._1 == t2._1) List((t1._1, t1._2 + t2._2)) else List(t1, t2)

    def collapse(list: List[(Char, Int)]): List[(Char, Int)] = list match {
      case List() => list
      case x :: List() => list
      case x :: xs =>
        if (x._1 != xs.head._1) {
          x :: collapse(xs)
        } else {
          val collapsedRest = collapse(xs)
          merge(x, collapsedRest.head) ::: collapsedRest.tail
        }
    }
    collapse(sorted)
  }

  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = freqs.sortWith((t1, t2) => t1._2 < t2._2).map(t => new Leaf(t._1, t._2))

  def singleton(trees: List[CodeTree]): Boolean = trees.tail.isEmpty

  def combine(trees: List[CodeTree]): List[CodeTree] = trees match {
    case List() => trees
    case x :: List() => trees
    case x :: xs => (makeCodeTree(x, xs.head) :: xs.tail).sortWith((t1, t2) => weight(t1) < weight(t2))
  }

  def until(condition: List[CodeTree] => Boolean, doSth: List[CodeTree] => List[CodeTree])(input: List[CodeTree]): CodeTree =
    if (condition(input)) {
      until(condition, doSth)(doSth(input))
    } else {
      input.head
    }

  def createCodeTree(chars: List[Char]): CodeTree = {
    val trees = makeOrderedLeafList(times(chars))
    until(singleton, combine)(trees)
  }

  type Bit = Int

  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {

    def decodeIt(currentTree: CodeTree, currentBits: List[Bit]): List[Char] = currentBits match {
      case List() => {
        if (!currentTree.isInstanceOf[Leaf]) {
          throw new RuntimeException("Invalid code")
        } else {
          val leaf: Leaf = currentTree.asInstanceOf[Leaf]
          List(leaf.char)
        }
      }
      case x :: xs =>
        if (currentTree.isInstanceOf[Leaf]) {
          val leaf: Leaf = currentTree.asInstanceOf[Leaf]
          leaf.char :: decodeIt(tree, currentBits)
        } else {
          val fork: Fork = currentTree.asInstanceOf[Fork]
          if (x == 0)
            decodeIt(fork.left, xs)
          else
            decodeIt(fork.right, xs)
        }
    }

    decodeIt(tree, bits)
  }

  val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s',121895),Fork(Leaf('d',56269),Fork(Fork(Fork(Leaf('x',5928),Leaf('j',8351),List('x','j'),14279),Leaf('f',16351),List('x','j','f'),30630),Fork(Fork(Fork(Fork(Leaf('z',2093),Fork(Leaf('k',745),Leaf('w',1747),List('k','w'),2492),List('z','k','w'),4585),Leaf('y',4725),List('z','k','w','y'),9310),Leaf('h',11298),List('z','k','w','y','h'),20608),Leaf('q',20889),List('z','k','w','y','h','q'),41497),List('x','j','f','z','k','w','y','h','q'),72127),List('d','x','j','f','z','k','w','y','h','q'),128396),List('s','d','x','j','f','z','k','w','y','h','q'),250291),Fork(Fork(Leaf('o',82762),Leaf('l',83668),List('o','l'),166430),Fork(Fork(Leaf('m',45521),Leaf('p',46335),List('m','p'),91856),Leaf('u',96785),List('m','p','u'),188641),List('o','l','m','p','u'),355071),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u'),605362),Fork(Fork(Fork(Leaf('r',100500),Fork(Leaf('c',50003),Fork(Leaf('v',24975),Fork(Leaf('g',13288),Leaf('b',13822),List('g','b'),27110),List('v','g','b'),52085),List('c','v','g','b'),102088),List('r','c','v','g','b'),202588),Fork(Leaf('n',108812),Leaf('t',111103),List('n','t'),219915),List('r','c','v','g','b','n','t'),422503),Fork(Leaf('e',225947),Fork(Leaf('i',115465),Leaf('a',117110),List('i','a'),232575),List('e','i','a'),458522),List('r','c','v','g','b','n','t','e','i','a'),881025),List('s','d','x','j','f','z','k','w','y','h','q','o','l','m','p','u','r','c','v','g','b','n','t','e','i','a'),1486387)
  val secret: List[Bit] = List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1)

  def decodedSecret: List[Char] = ???
  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = ???

  type CodeTable = List[(Char, List[Bit])]
  def codeBits(table: CodeTable)(char: Char): List[Bit] = ???
  def convert(tree: CodeTree): CodeTable = ???
  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = ???
  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = ???
}
