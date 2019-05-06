# Deeplearning MIT 

* notation http://www.deeplearningbook.org/contents/notation.html

> 1

This book is about a solution to these more intuitive problems. This solution is
to allow computers to learn from experience and understand the world in terms of a
hierarchy of concepts, with each concept defined in terms of its relation to simpler
concepts. By gathering knowledge from experience, this approach avoids the need
for human operators to formally specify all of the knowledge that the computer
needs. The hierarchy of concepts allows the computer to learn complicated concepts
by building them out of simpler ones. If we draw a graph showing how these
concepts are built on top of each other, the graph is deep, with many layers. For
this reason, we call this approach to AI deep learning .


> 2

Several artificial intelligence projects have sought to hard-code knowledge about
the world in formal languages. A computer can reason about statements in these
formal languages automatically using logical inference rules. This is known as the
knowledge base approach to artificial intelligence. None of these projects has led
to a major success.

.

The difficulties faced by systems relying on hard-coded knowledge suggest
that AI systems need the ability to acquire their own knowledge, by extracting
patterns from raw data. This capability is known as machine learning.

> 5

The quintessential example of a deep learning model is the feedforward deep
network or multilayer perceptron (MLP). A multilayer perceptron is just a
mathematical function mapping some set of input values to output values. The
function is formed by composing many simpler functions. We can think of each
application of a different mathematical function as providing a new representation
of the input.



https://en.wikipedia.org/wiki/Computational_complexity_theory
https://en.wikipedia.org/wiki/Calculus
https://en.wikipedia.org/wiki/Calculus#Limits_and_infinitesimals

> 17

One of these concepts is that of distributed representation
This is the idea that each input to a system should be represented by
many features, and each feature should be involved in the representation of many
possible inputs.
One way to
improve on this situation is to use a distributed representation, with three neurons
describing the color and three neurons describing the object identity. This requires
only six neurons total instead of nine, and the neuron describing redness is able to
learn about redness from images of cars, trucks and birds, not only from images
of one specific category of objects.

> 40

Previously,
it was widely believed that this kind of learning required labeling of the individual
elements of the sequence ( Gülçehre and Bengio , 2013 ). Recurrent neural networks,
such as the LSTM sequence model mentioned above, are now used to model
relationships between sequences and other sequences rather than just fixed inputs.
This sequence-to-sequence learning seems to be on the cusp of revolutionizing
another application: machine translation

.

This trend of increasing complexity has been pushed to its logical conclusion
with the introduction of neural Turing machines (Graves et al., 2014a ) that learn
to read from memory cells and write arbitrary content to memory cells. Such
neural networks can learn simple programs from examples of desired behavior. For
example, they can learn to sort lists of numbers given examples of scrambled and
sorted sequences. This self-programming technology is in its infancy, but in the
future could in principle be applied to nearly any task.


> 47

https://en.wikipedia.org/wiki/Cartesian_product
That is, for sets A and B, the Cartesian product A × B is the set of all ordered pairs (a, b) where a ∈ A and b ∈ B. 

We can think of vectors as identifying points in space, with each element
giving the coordinate along a different axis.

https://en.wikipedia.org/wiki/Determinant

The determinant gives the signed n-dimensional volume of this parallelotope, {\displaystyle \det(A)=\pm {\text{vol}}(P)} {\displaystyle \det(A)=\pm {\text{vol}}(P)}, and hence describes more generally the n-dimensional volume scaling factor of the linear transformation produced by A
