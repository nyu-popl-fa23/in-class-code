package popl.class03

import scala.annotation.targetName

class Pair(val first: Int, val second: Int) extends AnyRef:
  def setFirst(fst: Int) = new Pair(fst, second)
  
  @targetName("plus")
  def +(other: Pair): Pair = 
    new Pair(first + other.first, second + other.second) 
  
  override def toString = s"Pair($first, $second)"

object Pair:
  def apply(fst: Int, snd: Int) = new Pair(fst, snd)

  def make() = new JavaPair(0, 1)