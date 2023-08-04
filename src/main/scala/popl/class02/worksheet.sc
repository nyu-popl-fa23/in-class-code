import scala.annotation.tailrec
import popl.class03.Pair

println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

def sum(a: Int, b: Int): Int = {
	if (a < b) a + sum(a + 1, b) else 0
} //> sum(a: Int, b: Int): Int

def sumImp(a: Int, b: Int): Int = {
	var i = a
	var acc = 0

	while (i < b) {
		acc = i + acc
		i = i + 1
	}

	acc
} //> sumImp(a: Int, b: Int): Int

def sumTail(a: Int, b: Int): Int = {
	@tailrec def loop(i: Int, acc: Int): Int = {
		if (i < b) loop(i + 1, i + acc) else acc
	}

	loop(a, 0)
} //> sumTail(a: Int, b: Int): Int

sumTail(1, 100000) //> res1: Int = 704982704


val p = Pair(1, 3) // -> Pair.apply(1, 3) //> p  : popl.class03.Pair = Pair(1, 3)
val p2 = p.setFirst(2) //> p2  : popl.class03.Pair = Pair(2, 3)

p == p2 // -> p.equals(p2)                //> res2: Boolean = false

p + p2 // -> p.+(p2)                      //> res3: popl.class03.Pair = Pair(3, 6)

p.first //> res4: Int = 1

3.+(4) //> res5: Int = 7
