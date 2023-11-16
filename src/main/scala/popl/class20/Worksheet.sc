val l1 = List(1, 3)
val l2 = List("a", "b", "c")                   
  
for 
  x <- l1
yield x + 1
// l1.map(x => x + 1)

for
  x <- l1
  y <- l2
yield (x, y)

for 
  x <- Some(1)
yield x + 1  

// Future 

// def map[A, B](op: A => B): List[B]

// for x1 <- e1; x2 <- e2; ...; xn <- en yield e
// e1.flatMap(x1 => e2.flatMap(x2 => ... .map(xn => en)...)) 


def flatMap[A,B](l: List[A], op: A => List[B]): List[B] =
  l.foldRight(Nil: List[B])((x, acc) => op(x) ++ acc)


l1.flatMap(x => l2.map(y => (x, y))) 
