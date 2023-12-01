package popl.class21

object interpreter:
  import ast.*

  /* 
   * Substitutions e[er/x]
   */
  def subst(e: Expr, x: String, er: Expr): Expr =
    /* Simple helper that calls substitute on an expression
     * with the input value v and variable name x. */
    def substX(e: Expr): Expr = subst(e, x, er)
    /* Body */
    e match
      case Num(_) | Addr(_) => e
      case Var(y) => if x == y then er else e
      case UnOp(uop, e1) => UnOp(uop, substX(e1))
      case BinOp(bop, e1, e2) => BinOp(bop, substX(e1), substX(e2))
      case Decl(mut, y, ed, eb) => 
        Decl(mut, y, substX(ed), if x == y then eb else substX(eb))

  /* Memory */
  class Mem private(map: Map[Addr, Val], nextAddr: Int):
    def apply(key: Addr): Val = map(key)

    def get(key: Addr): Option[Val] = map.get(key)

    def +(kv: (Addr, Val)): Mem = new Mem(map + kv, nextAddr)

    def contains(key: Addr): Boolean = map.contains(key)

    def alloc(m: Mem, v: Val): (Mem, Addr) =
      val freshAddr = Addr(nextAddr)
      (new Mem(map + (freshAddr -> v), nextAddr + 1), freshAddr)

    override def toString: String = map.toString

  object Mem:
    def empty = new Mem(Map.empty, 1)


  def eval(e: Expr): State[Mem, Val] =
    def eToNum(e: Expr): State[Mem, Int] = m =>
      val (mp, v) = eval(e)(m)
      v match
        case Num(n) => (mp, n)
        case _ => throw StuckError(e)

    e match
      /** rule EvalVal */
      case v: Val =>
        State.insert(v)

      /** rule EvalUMinus */
      case UnOp(UMinus, e1) =>
        for
          n1 <- eToNum(e1)
        yield Num(-(n1))
        // eToNum(e1).map{ n1 => Num(-n1) }

      /** rule EvalDeref */
      case UnOp(Deref, a: Addr) =>
        State.read[Mem, Val](m => m(a))

      /** rule EvalPlus */
      case BinOp(Plus, e1, e2) =>
        for
          n1 <- eToNum(e1)
          n2 <- eToNum(e2)
        yield Num(n1 + n2)
        /*
        eToNum(e1).flatMap { n1 =>
          eToNum(e2).map { n2 => Num(n1 + n2) }
        }*/

      /** rule EvalAssignVar */
      case BinOp(Assign, UnOp(Deref, a: Addr), e2) => ???

      /** rule EvalConstDecl const x = ed; eb */
      case Decl(MConst, x, ed, eb) =>
        for
          vd <- eval(ed)
          v <- eval(subst(eb, x, vd))
        yield v

        // eval(ed).flatMap{ (vd: Val) => eval(subst(eb, x, vd).map(v => v) }

      /** rule EvalVarDecl */
      /*case Decl(MLet, x, ed, eb) =>
        val (md, vd) = eval(m, ed)
        val (mp, a) = md.alloc(md, vd)
        eval(mp, subst(eb, x, UnOp(Deref, a)))*/