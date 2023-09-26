enum List:
  case Nil
  case ::(hd: Int, tl: List)

  def ::(hd: Int): List = List.::(hd, this)

import List._

def length(l: List): Int = l match
  case Nil => 0
  case hd :: tl => 1 + length(tl)

def append(l1: List, l2: List): List = l1 match
  case Nil => l2
  case hd :: tl => hd :: append(tl, l2)

val l1 = 6 :: 3 :: Nil
val l2 = 0 :: 5 :: 2 :: Nil

append(l1, l2)

def reverse(l: List) =
  // revLoop(lr, rev) == reverse(l)
  // l = append(l1, lr) ==> rev = reverse(l1)
  def revLoop(lr: List, rev: List): List =
    lr match
      case Nil => rev
      case hd :: tl => revLoop(tl, hd :: rev)
  end revLoop
  revLoop(l, Nil)

reverse(reverse(l2)) == l2

