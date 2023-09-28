enum Bop:
  case Add, Mul

enum Expr:
  case Num(n: Int)
  case Var(x: String)
  case BinOp(bop: Bop, e1: Expr, e2: Expr)
  case ConstDecl(x: String, ed: Expr, eb: Expr)

import Bop._, Expr._

def ov(e: Expr): Set[String] =
  e match
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => ov(e1) ++ ov(e2)
    case ConstDecl(x, ed, eb) => Set(x) ++ ov(ed) ++ ov(eb)

def bv(e: Expr): Set[String] =
  e match
    case Num(n) => Set()
    case Var(x) => Set()
    case BinOp(_, e1, e2) => bv(e1) ++ bv(e2)
    case ConstDecl(x, ed, eb) => Set(x) ++ bv(ed) ++ bv(eb)

def fv(e: Expr): Set[String] =
  e match
    case Num(n) => Set()
    case Var(x) => Set(x)
    case BinOp(_, e1, e2) => fv(e1) ++ fv(e2)
    case ConstDecl(x, ed, eb) => fv(ed) ++ (fv(eb) - x)

type Env = Map[String, Int]

def dom(env: Env): Set[String] = env.keySet

def eval(env: Env, e: Expr): Int =
  require(fv(e) subsetOf dom(env))
  e match
    case Num(n) => n
    case Var(x) => env(x)
    case BinOp(Add, e1, e2) =>
      eval(env, e1) + eval(env, e2)
    case BinOp(Mul, e1, e2) =>
      eval(env, e1) * eval(env, e2)
    case ConstDecl(x, ed, eb) =>
      val v = eval(env, ed)
      eval(env + (x -> v), eb)

// const x = x + 2; const x = y + x; x * 3
val e =
  ConstDecl("x", BinOp(Add, Var("x"), Num(2)),
    ConstDecl("x", BinOp(Add, Var("y"), Var("x")),
      BinOp(Mul, Var("x"), Num(3))))

fv(e)

val env = Map("x" -> 2, "y" -> 1)

eval(env, e)



