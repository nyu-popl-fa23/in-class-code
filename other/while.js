const while = function while(name cond: Bool) : (name Undefined) => Undefined {
  return (name body: Undefined) =>
    cond ? (body, while(cond)(body)) : undefined ;
};


let x = 0;
while (x < 10)({
  console.log(x);
  x = x + 1;
  undefined;
})
