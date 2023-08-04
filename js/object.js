const counterClass = rep => {
  return {
    get: () => rep.val,
    inc: () => rep.val = rep.val + 1
  }
}

const newCounter = () => counterClass({ val: 0 })

const counterClient = counter => {
  counter.inc();
  counter.inc();
  counter.inc();
  return counter.get()
}

const c = newCounter()

console.log(counterClient(c))

const obj = {f : {g: 0}}

obj.f = {}

console.log(obj.f.g)