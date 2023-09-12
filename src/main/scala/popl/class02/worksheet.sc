import scala.annotation.tailrec

def sum(a: Int, b: Int): Int =
  var acc = 0
  var i = a
  while i < b do
    acc = acc + i
    i = i + 1
  acc

def sumTail(a: Int, b: Int): Int =
  @tailrec
  def sumLoop(i: Int, acc: Int): Int =
    if i < b then sumLoop(i + 1, acc + i) else acc
  end sumLoop
  sumLoop(a,0)

def sumRec(a: Int, b: Int): Int =
  if a < b then a + sumRec(a + 1, b)
  else 0

sum(1, 3)
sum(2, 5)

//sumRec(2, 100000000)

sum(2, 100000000)



val p = (1, (2, 3))

val x = 5
val y1 = 4

y1 match
  case y2 if y2 < 0 => println(s"$y2 < 0")
  case y2 => println(s"$y2 >= 0")

y1 + 2
