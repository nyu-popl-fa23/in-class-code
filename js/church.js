// Restricted JakartaScript fragment (Lambda calculus, Church 1936)
// e ::= x | e1(e2) | x => e

// Expressing const declarations in the Lambda calculus
// const x = ed ; eb   ===   (x => eb)(ed)

// Church Booleans

const ctrue = x => y => x

const cfalse = x => y => y

const toBool = b => b(true)(false)

// Note that e1 and e2 are zero-argument functions that
// compute the actual value of the corresponding branch. That is
// b ? e1 : e2 is translated into ite(b)(() => e1)(() => e2)
// This is to ensure that only the selected branch is evaluated.
const ite = b => e1 => e2 => b(e1)(e2)() 

const and = b1 => b2 => b1(b2)(cfalse)

const or = b1 => b2 => b1(ctrue)(b2)

const not = b => b(cfalse)(ctrue)

// Church pairs

const pair = n => m => (b => b(n)(m))

const fst = p => p(ctrue)

const snd = p => p(cfalse)

// Church numerals

const zero = s => z => z 

const one = s => z => s(z)

const two = s => z => s(s(z))

const toInt = n => n(x => x + 1)(0) 

const plus = n => m => (s => z => m(s)(n(s)(z)))

function fromInt(n) {
    return n === 0 ? zero : plus(one)(fromInt(n-1));
}

const mult = n => m => m(plus(n))(zero)

const isZero = n => n(x => cfalse)(ctrue)

const minusOne = n => fst(n(p => pair(snd(p))(plus(snd(p))(one)))(pair(zero)(zero)))

// Dealing with recursion

const facFun = fac => n =>
      ite(isZero(n))(() => one)(() => mult(n)(fac(minusOne(n))))

const fac0 = facFun(n => zero)
const fac1 = facFun(fac0)
const fac2 = facFun(fac1)

// Y combinator
const fix = f => (x => f(y => x(x)(y)))(x => f(y => x(x)(y)))

/*
const fix = f => (x => f(x(x)(y)))(x => f(x(x)(y)))

fix(facFun) ->
    (x => facFun(x(x)))(x => facFun(x(x))) ->
    facFun(fix(facFun))
*/

const fac = fix(facFun)

const result = toInt(fac(fromInt(5)))
      
console.log(result)

// Church lists

const nil = op => z => z

const l1 = op => z => op(1)(op(2)(z))

const cons = x => l => (op => z => op(x)(l(op)(z)))

const foldRight = l => op => z => l(op)(z)

// const map = l => ???

// const head = l => ???

// const tail = l => ???

// const isEmpty = l => ???
