// In JavaScript
//   function (x) { return e; }
// can also be written as
//   x => e

/*
function fac(n) {
    let base = 1;
    for (i = 1; i <= n; i++) {
	base = i * base;
    }
    return base;
}

const fac2 = function fac2(n) {
    const loop = function loop(i, base) {
	return i <= n ? loop(i + 1, i * base) : base;
    };
    return loop(1, 1);
};
*/

const forLoop =
    init => test => body => {
	const loop = function loop(i, base) {
	    return test(i) ? loop(i + 1, body(i)(base)) : base;
	};
	return loop(1, init);
    };


const fac = n => forLoop(1)(i => (i <= n))(i => base => i * base)

console.log(fac(5))


const sumInts = function sumInts(a) {
    return b => a < b ? a + sumInts(a + 1)(b) : 0
}

const sumSqurs = function sumSqurs(a) {
    return b => a < b ? a * a + sumSqurs(a + 1)(b) : 0
}

const sum = function sum(f) {
    return a => b =>
	a < b ? f(a) + sum(f)(a + 1)(b) : 0;
}

const sumInts2 = sum(a => a)

const sumSqurs2 = sum(a => a * a)


console.log(sumInts2(1)(3))

console.log(sumSqurs2(1)(3))
