package popl.class26

class Queue[+A] private (enq: List[A], deq: List[A]):
  
  def enqueue[B >: A](e: B): Queue[B] = 
    new Queue(e :: enq, deq)
  
  def dequeue: (A, Queue[A]) = 
    require(!isEmpty)
    deq match
      case hd :: tl => (hd, new Queue(enq, tl))
      case Nil => 
        // move enq to deq and dequeue again
        val q = new Queue(Nil, enq.reverse)
        q.dequeue

  def isEmpty: Boolean = enq.isEmpty && deq.isEmpty
  
  override def toString: String = 
    (deq ++ enq.reverse).mkString("Queue(", ", " ,")")


object Queue:
  def empty[A]: Queue[A] = new Queue(Nil, Nil)
  
  def apply[A](elems: A*): Queue[A] = 
    new Queue(Nil, elems.toList)

