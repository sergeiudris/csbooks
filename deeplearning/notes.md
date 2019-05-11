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


> 52

However, A −1 is primarily useful as a theoretical
tool, and should not actually be used in practice for most software applications.
Because A −1 can be represented with only limited precision on a digital computer,
algorithms that make use of the value of b can usually obtain more accurate
estimates of x .


> 53

In order for the matrix to have an inverse, we additionally need to ensure that
equation 2.11 has at most one solution for each value of b. To do so, we need to
ensure that the matrix has at most m columns. Otherwise there is more than one
way of parametrizing each solution.
Together, this means that the matrix must be square, that is, we require that
m = n and that all of the columns must be linearly independent. A square matrix
with linearly dependent columns is known as singular .


> 54

For square matrices, the left inverse and right inverse are equal.


> 68

Probability theory is a mathematical framework for representing uncertain
statements. It provides a means of quantifying uncertainty and axioms for deriving
new uncertain statements. In artificial intelligence applications, we use probability
theory in two major ways. First, the laws of probability tell us how AI systems
should reason, so we design our algorithms to compute or approximate various
expressions derived using probability theory. Second, we can use probability and
statistics to theoretically analyze the behavior of proposed AI systems.

> 69

There are three possible sources of uncertainty:

1. Inherent stochasticity in the system being modeled. For example, most
interpretations of quantum mechanics describe the dynamics of subatomic
particles as being probabilistic. We can also create theoretical scenarios that
we postulate to have random dynamics, such as a hypothetical card game
where we assume that the cards are truly shuffled into a random order.

2. Incomplete observability. Even deterministic systems can appear stochastic
when we cannot observe all of the variables that drive the behavior of the
system. For example, in the Monty Hall problem, a game show contestant is
asked to choose between three doors and wins a prize held behind the chosen
door. Two doors lead to a goat while a third leads to a car. The outcome
given the contestant’s choice is deterministic, but from the contestant’s point
of view, the outcome is uncertain.

3. Incomplete modeling. When we use a model that must discard some of
the information we have observed, the discarded information results in
uncertainty in the model’s predictions. For example, suppose we build a
robot that can exactly observe the location of every object around it. If the
robot discretizes space when predicting the future location of these objects,
then the discretization makes the robot immediately become uncertain about
the precise position of objects: each object could be anywhere within the
discrete cell that it was observed to occupy.

    Discretization
    In applied mathematics, discretization is the process of transferring continuous functions, models, variables, and equations into discrete counterparts. 


> 70

In
the case of the doctor diagnosing the patient, we use probability to represent a
degree of belief, with 1 indicating absolute certainty that the patient has the flu
and 0 indicating absolute certainty that the patient does not have the flu. The
former kind of probability, related directly to the rates at which events occur, is
known as frequentist probability, while the latter, related to qualitative levels
of certainty, is known as Bayesian probability.

> 71 

Probability can be seen as the extension of logic to deal with uncertainty. Logic
provides a set of formal rules for determining what propositions are implied to
be true or false given the assumption that some other set of propositions is true
or false. Probability theory provides a set of formal rules for determining the
likelihood of a proposition being true given the likelihood of other propositions.


> 88

The basic intuition behind information theory is that learning that an unlikely
event has occurred is more informative than learning that a likely event has
occurred. A message saying “the sun rose this morning” is so uninformative as
to be unnecessary to send, but a message saying “there was a solar eclipse this
morning” is very informative.
We would like to quantify information in a way that formalizes this intuition.


> 112

Machine learning is
essentially a form of applied statistics with increased emphasis on the use of
computers to statistically estimate complicated functions and a decreased emphasis
on proving confidence intervals around these functions


> 114

A machine learning algorithm is an algorithm that is able to learn from data. But
what do we mean by learning? Mitchell ( 1997 ) provides the definition “A computer
program is said to learn from experience E with respect to some class of tasks T
and performance measure P , if its performance at tasks in T , as measured by P ,
improves with experience E.”

Machine learning tasks are usually described in terms of how the machine
learning system should process an example. An example is a collection of features
that have been quantitatively measured from some object or event that we want
the machine learning system to process. We typically represent an example as a
vector x ∈ R n where each entry x i of the vector is another feature. For example,
the features of an image are usually the values of the pixels in the image.

> 115

Many kinds of tasks can be solved with machine learning. Some of the most
common machine learning tasks include the following:

Classification:

Classification with missing inputs:

Regression:

Transcription:

Machine translation:

Structured output:

    One example is parsing—mapping a
    natural language sentence into a tree that describes its grammatical structure
    and tagging nodes of the trees as being verbs, nouns, or adverbs, and so on.
    See Collobert ( 2011 ) for an example of deep learning applied to a parsing
    task. Another example is pixel-wise segmentation of images, where the
    computer program assigns every pixel in an image to a specific category.

Anomaly detection:

Synthesis and sampling:

Imputation of missing values:

Denoising:
    n obtained by an unknown corruption process
    from a clean example x ∈ R n . The learner must predict the clean example
    x from its corrupted version x̃, or more generally predict the conditional
    probability distribution p(x | x̃).

Density estimation or probability mass function estimation:


> 118

In order to evaluate the abilities of a machine learning algorithm, we must design
a quantitative measure of its performance. Usually this performance measure P is
specific to the task T being carried out by the system.
For tasks such as classification, classification with missing inputs, and tran-
scription, we often measure the accuracy of the model.

> 120

One of the oldest datasets studied by statisticians and machine learning re-
searchers is the Iris dataset ( Fisher , 1936 ). It is a collection of measurements of
different parts of 150 iris plants. Each individual plant corresponds to one example.
The features within each example are the measurements of each of the parts of the
plant: the sepal length, sepal width, petal length and petal width. The dataset
also records which species each plant belonged to. Three different species are
represented in the dataset.


> 121 

Though unsupervised learning and supervised learning are not completely formal or
distinct concepts, they do help to roughly categorize some of the things we do with
machine learning algorithms. Traditionally, people refer to regression, classification
and structured output problems as supervised learning. Density estimation in
support of other tasks is usually considered unsupervised learning.


Some machine learning algorithms do not just experience a fixed dataset. For
example, reinforcement learning algorithms interact with an environment, so
there is a feedback loop between the learning system and its experiences.

Most machine learning algorithms simply experience a dataset. A dataset can
be described in many ways. In all cases, a dataset is a collection of examples,
which are in turn collections of features


One common way of describing a dataset is with a design matrix . A design
matrix is a matrix containing a different example in each row. Each column of the
matrix corresponds to a different feature.

> 125

Typically, when training a machine learning model, we have access to a training
set, we can compute some error measure on the training set called the training
error, and we reduce this training error. So far, what we have described is simply
an optimization problem. What separates machine learning from optimization is
that we want the generalization error, also called the test error, to be low as
well. The generalization error is defined as the expected value of the error on a
new input. Here the expectation is taken across different possible inputs, drawn
from the distribution of inputs we expect the system to encounter in practice.

How can we affect performance on the test set when we get to observe only the
training set? The field of statistical learning theory provides some answers. If
the training and the test set are collected arbitrarily, there is indeed little we can
do. If we are allowed to make some assumptions about how the training and test
set are collected, then we can make some progress.

> 126

The factors determining how well a machine
learning algorithm will perform are its ability to:
1. Make the training error small.
2. Make the gap between training and test error small.

These two factors correspond to the two central challenges in machine learning:
underfitting and overfitting . Underfitting occurs when the model is not able to
obtain a sufficiently low error value on the training set. Overfitting occurs when
the gap between the training error and test error is too large.

> 127

Machine learning algorithms will generally perform best when their capacity
is appropriate for the true complexity of the task they need to perform and the
amount of training data they are provided with. Models with insufficient capacity
are unable to solve complex tasks. Models with high capacity can solve complex
tasks, but when their capacity is higher than needed to solve the present task they
may overfit.

> 131

In part, machine learning avoids this problem by offering only probabilistic rules,
rather than the entirely certain rules used in purely logical reasoning. Machine
learning promises to find rules that are probably correct about most members of
the set they concern.
Unfortunately, even this does not resolve the entire problem. The no free
lunch theorem for machine learning (Wolpert , 1996 ) states that, averaged over
all possible data generating distributions, every classification algorithm has the
same error rate when classifying previously unobserved points. In other words,
in some sense, no machine learning algorithm is universally any better than any
other.

> 133

This means that the goal of machine learning research is not to seek a universal
learning algorithm or the absolute best learning algorithm. Instead, our goal is to
understand what kinds of distributions are relevant to the “real world” that an AI
agent experiences, and what kinds of machine learning algorithms perform well on
data drawn from the kinds of data generating distributions we care about.

The no free lunch theorem implies that we must design our machine learning
algorithms to perform well on a specific task. We do so by building a set of
preferences into the learning algorithm. When these preferences are aligned with
the learning problems we ask the algorithm to solve, it performs better.

> 135

Regularization is any modification we make to a
learning algorithm that is intended to reduce its generalization error but not its
training error.

$$$

The no free lunch theorem has made it clear that there is no best machine
learning algorithm, and, in particular, no best form of regularization. Instead
we must choose a form of regularization that is well-suited to the particular task
we want to solve. The philosophy of deep learning in general and this book in
particular is that a very wide range of tasks (such as all of the intellectual tasks
that people can do) may all be solved effectively using very general-purpose forms
of regularization.


Most machine learning algorithms have several settings that we can use to control
the behavior of the learning algorithm. These settings are called hyperparame-
ters. The values of hyperparameters are not adapted by the learning algorithm
itself (though we can design a nested learning procedure where one learning
algorithm learns the best hyperparameters for another learning algorithm).
In the polynomial regression example we saw in figure 5.2 , there is a single
hyperparameter: the degree of the polynomial, which acts as a capacity hyper-
parameter. The λ value used to control the strength of weight decay is another
example of a hyperparameter.

> 136

The subset of data used to guide the selection of hyperparameters is called the
validation set.Typically, one uses about 80% of the training data for training and
20% for validation.

> 162

A classic unsupervised learning task is to find the “best” representation of the
data. By ‘best’ we can mean different things, but generally speaking we are looking
for a representation that preserves as much information about x as possible while
obeying some penalty or constraint aimed at keeping the representation simpler or
more accessible than x itself.


The notion of representation is one of the central themes of deep learning


> 170

Recognizing that most machine learning algorithms
can be described using this recipe helps to see the different algorithms as part of a
taxonomy of methods for doing related tasks that work for similar reasons, rather
than as a long list of algorithms that each have separate justifications.

> 175

The core idea in deep learning is that we assume that the data was generated by
the composition of factors or features, potentially at multiple levels in a hierarchy.
Many other similarly generic assumptions can further improve deep learning al-
gorithms. These apparently mild assumptions allow an exponential gain in the
relationship between the number of examples and the number of regions that can
be distinguished.


> 176

A manifold is a connected region.

Mathematically, it is a set of points,
associated with a neighborhood around each point.

For example, a figure eight is a manifold that has a single
dimension in most places but two dimensions at the intersection at the center.

> 177

The first observation in favor of the manifold hypothesis is that the proba-
bility distribution over images, text strings, and sounds that occur in real life is
highly concentrated. Uniform noise essentially never resembles structured inputs
from these domains. Figure 5.12 shows how, instead, uniformly sampled points
look like the patterns of static that appear on analog television sets when no signal
is available. Similarly, if you generate a document by picking letters uniformly at
random, what is the probability that you will get a meaningful English-language
text? Almost zero, again, because most of the long sequences of letters do not
correspond to a natural language sequence: the distribution of natural language
sequences occupies a very small volume in the total space of sequences of letters.

> 178

images encountered in AI applications occupy a negligible proportion of the
volume of image space.

> 179

It remains likely that there are multiple manifolds involved in most
applications. For example, the manifold of images of human faces may not be
connected to the manifold of images of cat faces.


