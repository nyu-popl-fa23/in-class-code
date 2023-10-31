/*function fac(n) {
  let acc = 1;
  for (let i = 1; i <= n; i++) {
    acc = acc * i;
  }
  return acc;
}*/

const forLoop = acc => {
  function loop(i) {
    return acc => b => body => 
      b(i) ? loop(i+1)(body(i)(acc))(b)(body) : acc;
  }
  return loop(1)(acc);
}

const fac =
      n => forLoop(1)(i => i <= n)(i => acc => acc * i);

//console.log(fac(5));

const f = x => y => x + y;

console.log(f(1,2)(3));


/*
function fac(n) {
  function loop(i) {
    return acc => i <= n ? loop(i+1)(acc * i) : acc;
  }
  return loop(1)(1);
}
*/


/*function sumInts(a) {
  return b => a < b ? a + sumInts(a+1)(b) : 0;
}

function sumSqurs(a) {
  return b => a < b ? a * a + sumSqurs(a+1)(b) : 0;
}
*/

/*

function sum(f) {
  return a => b =>
    a < b ? f(a) + sum(f)(a+1)(b) : 0;
}

const sumInts = sum(a => a);
const sumSqurs = sum(a => a * a);

console.log(sumInts(1)(4));
console.log(sumInts(1)(8));
console.log(sumSqurs(1)(4));
*/
