class repeat(body: => Unit):
  def until(cond: => Boolean): Unit =
    body
    if !cond then until(cond)

object repeat:
  def apply(body: => Unit): repeat = new repeat(body)

var x = 0
repeat {
  x = x + 1
} until x == 10

repeat.apply({
  x = x + 1
}).until(x == 10)

println(x)