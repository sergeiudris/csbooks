
p 19

The documentation strings for reduce and areduce show several terse parameter names.
Here are some parameter names and how they are normally used:
Parameter Usage
a A Java array
agt An agent
coll A collection
expr An expression
f A function
idx Index
r A ref
v A vector
val A value
These names may seem a little terse, but there is a good reason for them: the “good
names” are often taken by Clojure functions! Naming a parameter that collides with
a function name is legal but considered bad style: the parameter will shadow the
function, which will be unavailable while the parameter is in scope. So, don’t call
your refs ref, your agents agent, or your counts count. Those names refer to functions.