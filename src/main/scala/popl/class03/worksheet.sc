import scala.annotation.targetName

class Pair(private val first: Int, val second: Int) extends AnyRef:
  override def toString: String = s"Pair($first, $second)"

  @targetName("plus")
  def +(other: Pair): Pair =
    new Pair(first + other.first, second + other.second)

class Triple(fst: Int, snd: Int, val third: Int)
  extends Pair(first, snd):
  override def toString: String = s"Triple(${this.fst}, $second, $third)"


//object Pair:
  //def apply(fst: Int, snd: Int) = new Pair(fst, snd)

val p = Pair(1, 2) // Pair.apply(1, 2)
val q = Pair(2, 3)

val t = Triple(1, 2, 3)

t.first


p.toString


p + q // p.+(q)

object O:
  val x: Int = 3
  def foo(y: Int): Int = x + y

O.x
O.foo(3)


