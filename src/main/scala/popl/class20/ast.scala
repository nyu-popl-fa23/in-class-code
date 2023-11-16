package popl.class20

import scala.util.parsing.input.Positional
//import org.kiama.output.PrettyPrinter

object ast:
  sealed abstract class Expr extends Positional
  
  /* Literals and Values */
  sealed abstract class Val extends Expr
  case class Num(n: Double) extends Val
  case class Addr (a: Int) extends Val
  
  /* Variables */
  case class Var(x: String) extends Expr
  
  /* Declarations */
  case class Decl(mut: Mut, x: String, ed: Expr, eb: Expr) extends Expr
  
  /* Mutabilities */
  sealed abstract class Mut
  case object MConst extends Mut
  case object MLet extends Mut
  
  /* Unary and Binary Operators */
  case class UnOp(op: Uop, e1: Expr) extends Expr
  case class BinOp(op: Bop, e1: Expr, e2: Expr) extends Expr

  sealed abstract class Uop
  case object UMinus extends Uop /* - */

  sealed abstract class Bop
  case object Plus extends Bop /* + */
  
  /* Addresses and Mutation */
  case object Assign extends Bop /* = */
  case object Deref extends Uop /* * */

  case class StuckError(e: Expr) extends Exception("stuck while evaluating expression")
