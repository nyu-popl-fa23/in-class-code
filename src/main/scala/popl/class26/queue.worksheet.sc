import popl.class26._

abstract class Animal:
  def name: String
  override def toString = name
  def makeNoise: String

abstract class Cat extends Animal
object Tiger extends Cat:
  def name = "Tiger"  
  def makeNoise = "Roaaar"

abstract class Bird extends Animal:
  def fly: String = "Going south!"

object Duck extends Bird:
  def name = "Duck"
  def makeNoise = "Quack"

object Sparrow extends Bird:
  def name = "Sparrow"
  def makeNoise = "Peep"

val q = Queue(Duck)
  
val (e, q1) = q.enqueue[Bird](Sparrow).dequeue
e.fly

// A <: B ==> Queue[A] <: Queue[B] (covariance)
// A <: B ==> Queue[B] <: Queue[A] (contravariance)

// B1 <: A1 && A2 <: B2 ==> (A1 => A2) <: (B1 => B2)

//Function[-A,+R] ===  A => R