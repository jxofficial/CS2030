# CS2030
AY18/19 Semester 1 CS2030 Programming Methodology II 
<hr />


Lab 09 Infinite List.

How to generate an infinite list? 

//"s" are all individual supplier objects.
// generate() will create two new suppliers: s1 and s2.
// Hence, the tail of ifl_1 is s2, and the head of ifl_1 is s1. s2.get() will SUPPLY / CREATE a new ifl_2
// s1 = head. s1.get() will return something, eg maybe "Hello" or 123 or any object.
// s1.get() will SUPPLY / CREATE "Hello"

// ifl_2 has head s3 and tail s4.
// ifl_2 will have a head s3, which is the same as s, and will SUPPLY/ CREATE / RETURN "Hello"
// ifl_3 is CREATED / SUPPLIED when s4.get() is called.

generate method body
<U> IFL<U> generate(Supplier<U> s) {
  return new IFL(s, () -> generate(s)); 
}


