import scala.annotation.targetName

class Pair(val first: Int, val second: Int) extends AnyRef:
  override def toString: String = s"Pair($first, $second)"

  @targetName("plus")
  def +(other: Pair): Pair =
    new Pair(first + other.first, second + other.second)
end Pair

class Triple(fst: Int, snd: Int, val third: Int)
  extends Pair(snd, snd):
  override def toString: String = s"Triple(${first}, $second, $third)"

object Pair:
  def apply(fst: Int, snd: Int) = new Pair(fst, snd)

val p = Pair(1, 2) // Pair.apply(1, 2)
val q = Pair(2, 3)

val t = Triple(1, 2, 3)

p.toString

p + q // p.+(q)

object O:
  val x: Int = 3
  def foo(y: Int): Int = x + y

O.x
O.foo(3)


