package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {

  trait TestTrees {
		val t1 =
      Fork(
        Leaf('a', 2),
        Leaf('b', 3),
        List('a', 'b'),
        5)

		val t2 =
      Fork(
        Fork(
          Leaf('a', 2),
          Leaf('b', 3),
          List('a', 'b'), 5),
        Leaf('d', 4),
        List('a', 'b', 'd'),
        9)

    val t3 =
      Fork(
        Fork(
          Leaf('a', 1),
          Leaf('b', 1),
          List('a', 'b'),
          2),
        Leaf('c', 2),
        List('a', 'b', 'c'),
        4)

    val simpleText: List[Char] = List('a', 'a', 'b', 'c', 'c')
    val simpleCode: List[Bit] = List(0, 0, 0, 0, 0, 1, 1, 1)
    val simpleText2: List[Char] = List('c', 'c', 'c', 'a')
    val simpleCode2: List[Bit] = List(1, 1, 1, 0, 0)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode() single character") {
    new TestTrees {
      assert(decode(t3, List(0, 0)) === List('a'))
    }
  }

  test("decode() simple code") {
    new TestTrees {
      assert(decode(t3, simpleCode) === simpleText)
    }
  }

  test("decode() invalid code") {
    new TestTrees {
      val invalidCode: List[Bit] = List(1, 0)
      intercept[RuntimeException] {
        decode(t3, invalidCode)
      }
    }
  }

  test("encode() some simple texts") {
    new TestTrees {
      val encodeT3 = encode(t3)_
      assert(encodeT3(simpleText) === simpleCode)
      assert(encodeT3(simpleText2) === simpleCode2)
    }
  }

  test("encode() invalid text") {
    new TestTrees {
      intercept[RuntimeException] {
        encode(t3)(List('z'))
      }
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("convert()") {
    new TestTrees {
      val codeTable: CodeTable = List(('a', List(0, 0)), ('b', List(0, 1)), ('c', List(1)))
      assert(convert(t3) === codeTable)
    }
  }

  test("decodedSecret()") {
    new TestTrees {
      println(decodedSecret)
    }
  }

}
