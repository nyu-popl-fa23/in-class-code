// standard call-by-value semantics (const parameter)
let x = 0;
const f = (const y: Num) => y + y;
const r = f(x = x + 1);
console.log(r); // 2
console.log(x); // 1

// call-by-name semantics (name parameter)
let x = 0;
const f = (name y: Num) => y + y;
const r = f(x = x + 1);
console.log(r); // 3
console.log(x); // 2

// simulating call-by-name via call-by-value by wrapping the name parameter
// in a no-argument function

let x = 0;
const f = (const y: () => Num) => y() + y();
const r = f(() => x = x + 1);
console.log(r); // 2
console.log(x); // 1

// call-by-variable semantics (var parameter)
let x = 0;
const f = (let y: Num) => (y = y + 1, y);
const r = f(x);
console.log(r); // 1
console.log(x); // 0

//call-by-reference semantics (ref parameter)
let x = 0;
const f = (ref y: Num) => (y = y + 1, y);
const r = f(x);
console.log(r); // 1
console.log(x); // 1

// implementing a while loop construct using call-by-name parameters and function currying 

const while = function while(name cond: Bool): (name Undefined) => Undefined {
  return (name body: Undefined) => cond ? (body, while(cond)(body)) : undefined ;
}


var x = 0;
while(x !== 10)({
  x = x + 1;
  console.log(x);
});

  
