package objsets
import TweetReader._

class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
    "Text: " + text + " [" + retweets + "]"
}

abstract class TweetSet {
  def filter(p: Tweet => Boolean): TweetSet
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet
  def union(that: TweetSet): TweetSet
  def mostRetweeted: Tweet
  def descendingByRetweet: TweetList
  def incl(tweet: Tweet): TweetSet
  def remove(tweet: Tweet): TweetSet
  def contains(tweet: Tweet): Boolean
  def foreach(f: Tweet => Unit): Unit
  def getElem() : Tweet
  def getLeft() : TweetSet
  def getRight() : TweetSet
}

class Empty extends TweetSet {
  def filter(p: Tweet => Boolean) : TweetSet = new Empty
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  def contains(tweet: Tweet): Boolean = false
  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)
  def remove(tweet: Tweet): TweetSet = this
  def foreach(f: Tweet => Unit): Unit = ()
  override def union(that: TweetSet): TweetSet = that
  override def descendingByRetweet: TweetList = Nil
  override def mostRetweeted: Tweet = null
  override def getElem = null
  override def getLeft = new Empty
  override def getRight = new Empty
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filter(p: Tweet => Boolean) : TweetSet = filterAcc(p, new Empty)

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    var v = acc
    if (p(elem)) v = acc.incl(elem)
    v union left.filterAcc(p, acc) union right.filterAcc(p, acc)
  }

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }

  override def union(that: TweetSet): TweetSet =
    if (that.isInstanceOf[Empty])
      this
    else {
      val otherRoot = that.getElem()
      if (!contains(otherRoot)) {
        incl(otherRoot).union(that.getLeft()).union(that.getRight())
      } else {
        union(that.getLeft()).union(that.getRight())
      }
    }

  override def getElem() : Tweet = elem
  override def getLeft() : TweetSet = left
  override def getRight() : TweetSet = right


  override def descendingByRetweet: TweetList =
    if (left.isInstanceOf[Empty] && right.isInstanceOf[Empty]) {
      new Cons(elem, Nil)
    } else {
      val max = mostRetweeted
      new Cons(max, remove(max).descendingByRetweet)
    }

  override def mostRetweeted: Tweet = {
    var tweet: Tweet = null;
    foreach((t: Tweet) => {
      if (tweet == null) {
        tweet = t;
      } else if (tweet != null && t.retweets > tweet.retweets) {
        tweet = t;
      } else {

      }
    })
    tweet;
  }

}

trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}

object GoogleVsApple {
  import TweetReader.allTweets

  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  def filterTweetByKeyword(ts: TweetSet, keywords: List[String]) : TweetSet = {
    ts.filter((tweet: Tweet) => {
      val words : List[String] = tweet.text.split(" ").toList
      words.exists((word: String) => {
        keywords.contains(word)
      })
    })
  }

  lazy val googleTweets: TweetSet = filterTweetByKeyword(allTweets, google)
  lazy val appleTweets: TweetSet = filterTweetByKeyword(allTweets, apple)
  lazy val trending: TweetList = (googleTweets union appleTweets).descendingByRetweet
}

object Main extends App {
  GoogleVsApple.trending foreach println
}
