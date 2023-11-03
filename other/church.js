// e ::= x | x => e | e1(e2)

// fix(f) -> ... -> f(fix(f)) -> f(f(fix(f))) -> ...

//const fix = f => (x => f(n => x(x)(n)))(x => f(n => x(x)(n)))

// fix(f) -> (x => f(x(x)))(x => f(x(x))) -> f((x => f(x(x)))(x => f(x(x)))) = f(fix(f))

const fix = f => (x => f(n => x(x)(n)))(x => f(n => x(x)(n)));

/*
function fac(n) {
  return n == 0 ? 1 : n * fac(n - 1)
}*/

const ctrue = x => y => x;
const cfalse = x => y => y;

const ite = b => x => y => b(x)(y)(x);

const and = a => b => a(b)(a);

const or = a => b => a(a)(b);

const not = b => b(cfalse)(ctrue);

const zero = s => z => z;
const one = s => z => s(z);
const two = s => z => s(s(z));

const fromNumFun = fromNum => n => s => z => n === 0 ? z : s(fromNum(n - 1)(s)(z));

const fromNum = fix(fromNumFun);

const toNum = cn => cn(n => n + 1)(0);

const plusOne = cn => s => z => s(cn(s)(z));

const plus = cn => cm => cn(plusOne)(cm);

const mult = cn => cm => cn(plus(cm))(zero);

const isZero = cn => cn(b => cfalse)(ctrue);

const pair = x => y => b => b(x)(y);

const fst = p => p(ctrue);

const snd = p => p(cfalse);

const minusOne = cn => snd(cn(p => pair(plusOne(fst(p)))(fst(p)))(pair(zero)(zero)));

const res = toNum(minusOne(fromNum(5)));

console.log(res);

// const x = ed; eb === (x => eb)(ed)

const facFun = fac => n => ite(isZero(n))(x => one)(x => mult(n)(fac(minusOne(n))));

/*const fac0 = n => 0
const fac1 = facFun(fac0)
const fac2 = facFun(fac1)
const fac3 = facFun(fac2)
const fac4 = facFun(fac3)
const fac5 = facFun(fac4)
const fac6 = facFun(fac5)*/

// fac = facFun(fac)

const fac = fix(facFun);

const result = toNum(fac(fromNum(5)));

console.log(result)