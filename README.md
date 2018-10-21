# CS2030
AY18/19 Semester 1 CS2030 Programming Methodology II 
<hr>


Lab 09 Infinite List.

How to generate an infinite list? 
<ol>
  <li>"s" are all individual supplier objects.</li>
  <li>generate() will create two new suppliers: s1 and s2.</li>
  <li>Hence, the tail of ifl_1 is s2, and the head of ifl_1 is s1. s2.get() will SUPPLY / CREATE a new ifl_2</li>
  <li>s1 = head. s1.get() will return something, eg maybe "Hello" or 123 or any object.</li>
  <li>s1.get() will SUPPLY / CREATE "Hello"</li>
  <li>ifl_2 has head s3 and tail s4.</li>
  <li>ifl_2 will have a head s3, which is the same as s, and will SUPPLY/ CREATE / RETURN "Hello"</li>
  <li>ifl_3 is CREATED / SUPPLIED when s4.get() is called.</li>
</ol>

<p>generate method body:
<br><U> IFL<U> generate(Supplier<U> s) {
  return new IFL(s, () -> generate(s)); 
}</p>


