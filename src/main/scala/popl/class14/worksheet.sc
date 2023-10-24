val l = List(1, 2, 3, 4)

def map[A, B](l: List[A])(op: A => B): List[B] =
  l match
    case Nil => Nil
    case hd :: tl => op(hd) :: map(tl)(op)

def incr(l: List[Int]) = map(l)(_ + 1)

l.map(_ + 1)

def sum(l: List[Int]): Int =
  l match
    case Nil => 0
    case hd :: tl => hd + sum(tl)

//def sum2(l: List[Int]) = foldRight(l)(0)(_ + _)

//l.foldRight(0)(_ + _)

def foldRight[A,B](l: List[A])(z: B)(op: (A, B) => B): B =
  l match
    case Nil => z
    case hd :: tl => op(hd, foldRight(tl)(z)(op))

def reverse[A](l: List[A]): List[A] =
  // append(reverse(l), acc) = loop(l, acc)
  def loop(l: List[A], acc: List[A]): List[A] =
    l match
      case Nil => acc
      case hd :: tl => loop(tl, hd :: acc)
  loop(l, Nil)

def foldLeft[A,B](l: List[A])(z: B)(op: (B, A) => B): B =
  l match
    case Nil => z
    case hd :: tl => foldLeft(tl)(op(z, hd))(op)

def reverse2[A](l: List[A]): List[A] =
  l.foldLeft(Nil: List[A])((acc, hd) => hd :: acc)

def plus(x: Int, y: Int) = x + y

l.foldLeft(0)(plus)


reverse(l)
reverse2(l)

def append[A](l1: List[A], l2: List[A]): List[A] =
  l1 match
    case Nil => l2
    case hd :: tl => hd :: append(tl, l2)

def append2[A](l1: List[A], l2: List[A]): List[A] =
  l1.foldRight(l2)((hd, acc) => hd :: acc)

append2(l, List(5,6,7))

// (x1, x2, x3) * (y1, y2, y3) = x1*y1 + x2*y2 + x3*y3
//((x1, y1), (x2, y2), (x3, y3))
def dotprod(v1: List[Double], v2: List[Double]): Double =
  v1.zip(v2).map(_ * _).sum //foldLeft(0.0)(_ + _)
  //v1.zip(v2).foldLeft(0.0){ case (acc, (x, y)) => acc + x * y }

