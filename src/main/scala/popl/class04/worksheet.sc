
enum Bop:
  case Add, Sub, Mul, Div

enum Expr:
  case Num(n: Int)
  case BinOp(bop: Bop, left: Expr, right: Expr)

import Expr._, Bop._

// 3 + 5*6 - 7
val e = BinOp(Sub, BinOp(Add, Num(3), BinOp(Mul, Num(5), Num(6))), Num(7))

val e1 = Num(7)
val e2: BinOp = BinOp(Add, Num(5), Num(0))
val e3 = BinOp(Add, Num(5), Num(6))

e2.copy(bop = Sub)

def simplifyTop(e: Expr): Expr =
  e match
    case BinOp(Mul, _, e @ Num(0)) => e
    case BinOp(Mul | Div, e @ Num(0), _) => e // e1 * 0 ~> 0
    case BinOp(Mul, e1, Num(1)) => e1
    case BinOp(Add, e1, Num(0)) => e1
    // e + e ~> 2 * e
    case BinOp(Add, e1, e2) => BinOp(Mul, Num(2), e1)
    case _ => e
    

simplifyTop(BinOp(Mul, Num(0), Num(3)))




