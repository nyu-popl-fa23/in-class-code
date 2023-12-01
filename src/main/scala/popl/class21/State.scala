package popl.class21

// The state monad: a container for stateful computations S => (S,R)
abstract class State[S, +R]:
  def apply(s: S): (S, R)

  def map[P](f: R => P): State[S, P] =
    s =>
      val (sp, r) = this.apply(s) // this.apply(s)
      (sp, f(r))
    /*
    new State {
      def apply(s: S): (S, P) =
        val (sp, r) = this.apply(s)
        (sp, f(r))
    }*/

  def flatMap[P](f: R => State[S,P]): State[S, P] =
    s =>
      val (sp, r) = this(s)
      f(r)(sp)

object State:
  def apply[S, R](f: S => (S, R)): State[S, R] =
    s => f(s)

  def insert[S, R](r: R): State[S, R] =
    s => (s,r)

  def write[S](f: S => S): State[S, Unit] =
    s => (f(s), ())

  def read[S,R](f: S => R): State[S, R] =
    s => (s, f(s))
