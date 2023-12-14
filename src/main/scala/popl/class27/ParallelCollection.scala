package popl.class27

import scala.collection.parallel.immutable.ParSeq
import scala.collection.parallel.CollectionConverters._


object ParallelCollection:

  def time(s: => Unit, n: Int) =
    val start = System.nanoTime
    for i <- 1 to n do s
    (System.nanoTime - start) / 1000 / 1000 // convert to ms  
  
  def main(args: Array[String]) =

    val sseq = for i <- 0 to 10000000 yield i * i
  
    val pseq = for i <- (0 to 10000000).par yield i * i
  
    // warm-up
    time(sseq.reduce(_ + _), 5)
    time(pseq.reduce(_ + _), 5)
  
    // measuring
    println("Sequential: " + time(sseq.reduce(_ + _), 20))
    println("Parallel: " + time(pseq.reduce(_ + _), 20))
