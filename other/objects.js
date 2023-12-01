// object literals: {mut1 f1: e1, ..., mutn fn: en}
// field dereference: e.f
// field assignments: e1.f = e2

/*const x = {f: 1 + 2, g: true};
const y = x;
x.f = x.f + 1;
const res = y.f;

res*/


// counter

const counterClass = (rep) => ({
  get: () => rep.x,
  inc: () => rep.x = rep.x + 1
});

const newCounter = () => {
  const rep = { x: 0 };
  return counterClass(rep);
};

const counter = newCounter();
const counter2 = newCounter();

const counterClient = (counter) => {
  counter.inc();
  counter.inc();
  return counter.get();
};

const res = counterClient(counter);
const res2 = counter2.get()

res
res2

// reset counter

const resetCounterClass = (rep) => {
  const _super = counterClass(rep);
  return {
    get: _super.get,
    inc: _super.inc,
    reset: () => rep.x = 0
  };
};

const newResetCounter = () => {
  const rep = {x: 0};
  return resetCounterClass(rep);
};

const resetCounter = newResetCounter();

counterClient(resetCounter)

const r = resetCounter.get()

r

// backup counter
const backupCounterClass = (rep) => {
  const _super = resetCounterClass(rep);
  return {
    get: _super.get,
    inc: _super.inc,
    reset: () => rep.x = rep.oldX,
    backup: () => rep.oldX = rep.x
  };
};

const newBackupCounter = () => {
  const rep = {x: 0, oldX: 0};
  return backupCounterClass(rep);
};

const backupCounter = newBackupCounter();

backupCounter.inc();
backupCounter.backup();
backupCounter.inc();
backupCounter.reset();
const r3 = backupCounter.get();

r3

const funnyBackupCounterClass = (rep) => {
  const _super = backupCounterClass(rep);
  return {
    get: _super.get,
    inc: () => (_super.backup(), _super.inc()),
    reset: _super.reset,
    backup: _super.backup
  };
};

const newFunnyCounter = () => {
  const rep = {x: 0, oldX: 0};
  return funnyBackupCounterClass(rep);
};

const funnyCounter = newFunnyCounter();

counterClient(funnyCounter)

// counter with get/set

const counterWithSetVtable = {
  inc: (_this) => _this.vptr.set(_this, _this.vptr.get(_this) + 1),
  set: (_this, i) => _this.x = i,
  get: (_this) => _this.x
};
  
const newCounterWithSet = () => {
  const c = {
    vptr: counterWithSetVtable,
    x: 0,
  };
  return c
};

const fooVtable = {
  inc: counterWithSetVtable.inc,
  set: (_this, i) => _this.x = i + 1,
  get: counterWithSetVtable.get
};

const newFoo = () => {
  const c = {
    vptr: fooVtable,
    x: 0,
  };
  return c
};

const foo = newFoo();

foo.vptr.inc(foo)

const r5 = foo.vptr.get(foo);

r5

//const setCounter = newCounterWithSet();

//setCounter.inc();

const r4 = setCounter.get();

r4