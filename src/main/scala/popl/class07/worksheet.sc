enum Bop:
  case Add, Mul

enum Expr:
  case Num(n: Int)
  case Var(x: String)
  case BinOp(bop: Bop, e1: Expr, e2: Expr)

import Bop._, Expr._

def ov(e: Expr): Set[String] =
  e match
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => ov(e1) ++ ov(e2)

type Env = Map[String, Int]

def dom(env: Env): Set[String] = env.keySet

def eval(env: Env, e: Expr): Int =
  require(ov(e) subsetOf dom(env))
  e match
    case Num(n) => n
    case Var(x) => env(x)
    case BinOp(Add, e1, e2) =>
      eval(env, e1) + eval(env, e2)
    case BinOp(Mul, e1, e2) =>
      eval(env, e1) * eval(env, e2)

// 4 * (x + 8)
val e = BinOp(Mul, Num(4), BinOp(Add, Var("x"), Num(8)))

ov(e)

val env = Map("x" -> 2, "y" -> 1)

eval(env, e)



