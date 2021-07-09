## Real World Machine Learning

"Desiderata" (Latin: "things desired")

> 14

Wise.io

> 27

* Classification

    Spam filtering, sentiment analysis, fraud detection, customer ad
    targeting, churn prediction, support case flagging, content
    personalization, detection of manufacturing defects, customer
    segmentation, event discovery, genomics, drug efficacy

* Regression

  Predict the real-valued output for each individual, based on input data

    Stock-market prediction, demand forecasting, price estimation, ad
    bid optimization, risk management, asset management, weather
    forecasting, sports prediction

* Imputation

  Infer the values of missing input data

    Incomplete patient medical records, missing customer data, census data


> 37

The five advantages of
machine learning are

  Accurate— ML uses data to discover the optimal decision-making engine for your problem. As
  you collect more data, the accuracy can increase automatically.

  Automated— As answers are validated or discarded, the ML model can learn new patterns
  automatically. This allows users to embed ML directly into an automated workflow.

  Fast— ML can generate answers in a matter of milliseconds as new data streams in, allowing
  systems to react in real time.

  Customizable— Many data-driven problems can be addressed with machine learning. ML
  models are custom built from your own data, and can be configured to optimize whatever metric
  drives your business.
  
  Scalable— As your business grows, ML easily scales to handle increased data rates. Some ML
  algorithms can scale to handle large amounts of data on many machines in the cloud.

> 38

The ML workflow has five main components: data preparation, model
building, evaluation, optimization, and predictions on new data.

> 40

$$$ **`The first part of building a successful machine-learning system is to ask a question that can be
answered by the data.`**

> 44

In any problem domain, specific knowledge goes into deciding the data to collect, and this valuable
domain knowledge can also be used to extract value from the collected data, in effect adding to the
features of the model before model building. We call this process feature engineering

> 47 summary chapter 1

Machine-learning algorithms are distinguished from rule-based systems in that they create their
own models based on data. Supervised ML systems generalize by learning from the features of
examples with known results.

Machine learning is often more accurate, automated, fast, customizable, and scalable than
manually constructed rule-based systems.

Machine-learning challenges include identifying and formulating problems to which ML can be
applied, acquiring and transforming data to make it usable, finding the right algorithms for the
problem, feature engineering, and overfitting.

The basic machine-learning workflow consists of data preparation, model building, model
evaluation, optimization, and predictions on new data.

Online learning models continually relearn by using the results of their predictions to update
themselves.


> 49

churn prediction. In business, churn
refers to the act of a customer canceling or unsubscribing from a paid service.

> 50

The input features, together with the known values of
the target variable, compose the training set.

> 53 

Other ways of obtaining ground-truth values of the target variable include the following:

-Dedicating analysts to manually look through past or current data to determine or estimate the
ground-truth values of the target

-Using crowdsourcing to use the “wisdom of crowds” in order to attain estimates of the target

-Conducting follow-up interviews or other hands-on experiments with customers

-Running controlled experiments (for example, A/B tests) and monitoring the responses


> 60

What you want to do, if you can, is use all the data at your disposal to predict the value of the missing
variable. Does this sound familiar? This is exactly what machine learning is about, so you’re
basically thinking about building ML models in order to be able to build ML models.

> 70 summary chapter 2

Steps in compiling your training data include the following:

Deciding which input features to include
Figuring out how to obtain ground-truth values for the target variable
Determining when you’ve collected enough training data
Keeping an eye out for biased or nonrepresentative training data

Preprocessing steps for training data include the following:

Recoding categorical features
Dealing with missing data
Feature normalization (for some ML approaches)
Feature engineering

Four useful data visualizations are mosaic plots, density plots, box plots, and scatter plots:

mosaic plots, box plots, density plots, scatter plots


> 73

These various approaches can range from simple to tremendously complex, but all share a common
goal: to estimate the functional relationship between the input features and the target variable.

Y = f(X) + E

Y - target var
X - input features
E - noise

Using the noisy data that you have from hundreds of vehicles, the ML approach is to use modeling
techniques to find a good estimate for f. This resultant estimate is referred to as an ML model.

Machine learning has two main goals:
prediction and inference.

> 75

Prediction is the most common use of machine-learning systems. Prediction is central to many ML use
cases, including these:

Deciphering handwritten digits or voice recordings
Predicting the stock market
Forecasting
Predicting which users are most likely to click, convert, or buy
Predicting which users will need product support and which are likely to unsubscribe
Determining which transactions are fraudulent
Making recommendations

INference enable you to answer deep questions about the associations between the variables at hand:

Which input features are most strongly related to the target variable?
Are those relationships positive or negative?
Is f a simple relationship, or is it a function that’s more nuanced and nonlinear?

> 76

In addition, the machine-learning model has two main types: parametric and nonparametric. The
essential difference is that parametric models assume that f takes a specific functional form, whereas
nonparametric models don’t make such strict assumptions. Therefore, parametric approaches tend to
be simple and interpretable, but less accurate. Likewise, nonparametric approaches are usually less
interpretable but more accurate across a broad range of problems.

> 77

A simple example of a nonparametric model is a classification tree. A classification tree is a series
of recursive binary decisions on the input features. The classification tree learning algorithm uses the
target variable to learn the optimal series of splits such that the terminal leaf nodes of the tree contain
instances with similar values of the target.

Other examples of nonparametric approaches to machine learning include k-nearest neighbors,
splines, basis expansion methods, kernel smoothing, generalized additive models, neural nets,
bagging, boosting, random forests, and support vector machines.

> 78 

Machine-learning problems fall into two camps: supervised and unsupervised. Supervised problems
are ones in which you have access to the target variable for a set of training data, and unsupervised
problems are ones in which there’s no identified target variable.

$$$ The unsupervised learning approach has two main classes:

Clustering— Use the input features to discover natural groupings in the data and to divide the
data into those groups. Methods: k-means, Gaussian mixture models, and hierarchical clustering.

Dimensionality reduction— Transform the input features into a small number of coordinates
that capture most of the variability of the data. Methods: principal component analysis (PCA),
multidimensional scaling, manifold learning.

...

In machine learning, classification describes the prediction of new data into buckets (classes) by
using a classifier built by the machine-learning algorithm.

> 80

Maybe the age together with the sex and social status divides the passengers
much better than any single feature. In fact, this is one of the main reasons to use machine-learning
algorithms in the first place: to find signals in many dimensions that humans can’t discover easily.

> 84

What
you see here is a good example of an important concept in machine learning: overfitting. The
algorithm is capable of fitting well to the data, almost at the single-record level, and you risk losing
the ability to make good predictions on new data that wasn’t included in the training set; the more
complex you allow the model to become, the higher the risk of overfitting.

> 85

The support vector machine (SVM) algorithm is a popular choice for both linear and nonlinear
problems.

The main idea behind the algorithm is, as with logistic regression discussed previously, to find the
line (or equivalent in higher dimensions) that separates the classes optimally. Instead of measuring the
distance to all points, SVMs try to find the largest margin between only the points on either side of
the decision line.

A kernel is a mathematical construct that can “warp”
the space where the data lives. The algorithm can then find a linear boundary in this warped space,
making the boundary nonlinear in the original space.

> 91

Like logistic regression for classification, linear regression is arguably the simplest and most widelyused algorithm for building regression models. The main strengths are linear scalability and a high
level of interpretability.

> 94

the random forest (RF) algorithm. This
highly accurate nonlinear algorithm is widely used in real-world classification and regression
problems.
The basis of the RF algorithm is the decision tree.

This is where the random forest method comes in. By building a collection of
decision trees, you mitigate this risk. When making the answer, you pick the majority vote in the case
of classification, or take the mean in case of regression. Because you use votes or means, you can
also give back full probabilities in a natural way that not many algorithms share.

Random forests are also known for other kinds of advantages, such as their immunity to unimportant
features, noisy datasets in terms of missing values, and mislabeled records.

> 94 summary chapter 3

The purpose of modeling is to describe the relationship between the input features and the target
variable.
You can use models either to generate predictions for new data (whose target is unknown) or to
infer the true associations (or lack thereof) present in the data.
There are hundreds of methods for ML modeling. Some are parametric, meaning that the form of
the mathematical function relating the features to the target is fixed in advance. Parametric
models tend to be more highly interpretable yet less accurate than nonparametric approaches,
which are more flexible and can adapt to the true complexity of the relationship between the
features and the target. Because of their high levels of predictive accuracy and their flexibility,
nonparametric approaches are favored by most practitioners of machine learning.
Machine-learning methods are further broken into supervised and unsupervised methods.
Supervised methods require a training set with a known target, and unsupervised methods don’t
require a target variable. Most of this book is dedicated to supervised learning.
The two most common problems in supervised learning are classification, in which the target iscategorical, and regression, in which the target is numerical. In this chapter, you learned how to
build both classification and regression models and how to employ them to make predictions on
new data.
You also dove more deeply into the problem of classification. Linear algorithms can define
linear decision boundaries between classes, whereas nonlinear methods are required if the data
can’t be separated linearly. Using nonlinear models usually has a higher computational cost.
In contrast to classification (in which a categorical target is predicted), you predict a numerical
target variable in regression models. You saw examples of linear and nonlinear methods and
how to visualize the predictions of these models.

> 97

The primary goal of supervised machine learning is accurate prediction.

> 99

. For regression, the standard metric for
evaluation is mean squared error (MSE), which is the average squared difference between the true
value of the target variable and the model-predicted value

> 102

The holdout method

This approach is referred to as the holdout method, because a random subset of the training data is
held out from the training process.

K-fold cross-validation

The primary difference is that k-fold CV begins by randomly splitting the
data into k disjoint subsets, called folds (typical choices for k are 5, 10, or 20). For each fold, a
model is trained on all the data except the data from that fold and is subsequently used to generate
predictions for the data from that fold.

> 108

confusion matrix

Each element in the matrix shows the class-wise accuracy or confusion between the positive and the
negative class.

> 109

The output of a probabilistic classifier is what we call the probability vectors or class probabilities.
For every row in the test set, you get a real-valued number from 0 to 1 for every class in the classifier
(summing to 1)

> 112

The ROC curve illustrates the overall model performance. You can quantify this by defining the AUC metric:
the area under the ROC curve.

> 116

The simplest form of performance measurement of a regression model is the root-mean-square error,
or RMSE. This estimator looks at the difference from each of the predicted values to the known
values, and calculates the mean in a way that’s immune to the fact that predicted values can be both
higher and lower than the actual values.

> 117

Whether using MSE, RMSE, or R 2 as the evaluation metric, you should always keep the following in
mind:
Always use cross-validation to assess the model. If you don’t, the metrics will always improve
with increasing model complexity, causing overfitting.
Wherever possible, the evaluation metric should align with the problem at hand. For instance, if
predicting MPG from automobile features, an RMSE of 5 means that you expect the average
prediction to differ from the true MPG by 5 miles per gallon.

> 119

Here are the standard tuning parameters
for some of the popular classification algorithms that you learned about in chapter 3, listed in order of
increasing complexity:
Logistic regression— None
K-nearest neighbors— Number of nearest neighbors to average
Decision trees— Splitting criterion, max depth of tree, minimum samples needed to make a split
Kernel SVM— Kernel type, kernel coefficient, penalty parameter
Random forest— Number of trees, number of features to split in each node, splitting criterion,
minimum samples needed to make a split
Boosting— Number of trees, learning rate, max depth of tree, splitting criterion, minimum
samples needed to make a split

> 123 summary chapter 4

In this chapter, you learned the basics of evaluating ML model performance. Here’s a quick rundown
of the main takeaways:
When you evaluate models, you can’t double-dip the training data and use it for evaluation as
well as training.
Cross-validation is a more robust method of model evaluation.
Holdout cross-validation is the simplest form of cross-validation. A testing set is held out for
prediction, in order to better estimate the model’s capability to be generalized.
In k-fold cross-validation, k-folds are held out one at a time, providing more-confident estimatesof model performance. This improvement comes at a higher computational cost. If available, the
best estimate is obtained if k = number of samples, also known as leave-one-out cross-
validation.
The basic model-evaluation workflow is as follows:
1. Acquire and preprocess the dataset for modeling (chapter 2) and determine the appropriate
ML method and algorithm (chapter 3).
2. Build models and make predictions by using either the holdout or k-fold cross-validation
methods, depending on the computing resources available.
3. Evaluate the predictions with the performance metric of choice, depending on whether the
ML method is classification or regression.
4. Tweak the data and model until the desired model performance is obtained. In chapters 5–8,
you’ll see various methods for increasing the model performance in common real-world
scenarios.
For classification models, we introduced a few model-performance metrics to be used in step 3
of the workflow. These techniques include simple counting accuracy, the confusion matrix,
receiver-operator characteristics, the ROC curve, and the area under the ROC curve.
For regression models, we introduced the root-mean-square error and R-squared estimators.
Simple visualizations, such as the prediction-versus-actual scatter plot and the residual plot, are
useful.
You can use a grid-search algorithm to optimize a model with respect to tuning parameters


> 125

Feature engineering

Dividing total dollar amount by total number of payments to get a ratio of dollars per payment
Counting the occurrence of a particular word across a text document
Computing statistical summaries (such as mean, median, standard deviation, and skew) of a
distribution of user ping times to assess network health
Joining two tables (for example, payments and support) on user ID
Applying sophisticated signal-processing tools to an image and summarizing their output (for
example, histogram of gradients)

5 reasons to use feature engineering:

Transform original data to relate to the target
Bring in external data sources
Use unstructured data sources
Create features that are more easily interpreted
Enhance creativity by using large sets of features

> 127

 But for more-challenging
problems, the ML models stand to improve significantly from the codification of that domain
expertise into the feature set.

Web conversions are always higher on Tuesday (include the Boolean feature “Is it Tuesday?”).
Household power consumption increases with higher temperature (include temperature as a
feature).
Spam emails typically come from free email accounts (engineer the Boolean feature “Is from
free email account?” or email domain).
Loan applicants with recently opened credit cards default more often (use the feature “Days
since last credit card opened”).
Customers often switch their cell-phone provider after others in their network also switch
providers (engineer a feature that counts the number of people in a subscriber’s network who
recently switched).

> 145 summary chapter 5

This chapter introduced feature engineering, which transforms raw data to improve the accuracy of
ML models. The primary takeaways from this chapter are as follows:
Feature engineering is the process of applying mathematical transformations to raw data to
create new input features for ML modeling. The transformations can range from simple to
extremely complex.
Feature engineering is valuable for the following five reasons:
It can create features that are more closely related to the target variable.
It enables you to bring in external data sources.
It allows you to use unstructured data.
It can enable you to create features that are more interpretable.
It gives you the freedom to create lots of features and then choose the best subset via feature
selection.
There’s an intricate link between feature engineering and domain knowledge
Feature engineering fits into the overall ML workflow in two places:
On the training dataset, prior to fitting a model
On the prediction dataset, prior to generating predictions
Two types of simple feature engineering can be used on a problem of event recommendation:
Extraction of features from date-time information
Feature engineering on natural language text
Feature selection is a rigorous way to select the most predictive subset of features from a
dataset.

> 153

When working with machine learning, it’s critical to
watch out for two pitfalls: too-good-to-be-true scenarios and making premature assumptions that
aren’t rooted in the data.

> 163 summary chapter 6 NYC taxi

With more organizations producing vast amounts of data, increasing amounts of data are
becoming available within organizations, if not publicly.
Records of all taxi trips from NYC in 2013 have been released publicly. A lot of taxi trips occur
in NYC in one year!
Real-world data can be messy. Visualization and knowledge about the domain helps. Don’t get
caught in too-good-to-be-true scenarios and don’t make premature assumptions about the data.
Start iterating from the simplest possible model. Don’t spend time on premature optimization.
Gradually increase complexity.
Make choices and move on; for example, choose an algorithm early on. In an ideal world, you’d
try all combinations at all steps in the iterative process of building a model, but you’d have to fix
some things in order to make progress.
Gain insights into the model and the data in order to learn about the domain and potentially
improve the model further.

> 164 

you learned about the bag-of-words
representation, in which you count the occurrences of words across all texts and use the counts of the
top-N words as N new features. This work of transforming natural-language text into machine-usable
data is commonly referred to as natural language processing, or NLP.

Tokenization and Transformation

The splitting of a text into pieces is known as tokenization.The most common way to split is on
words, but in some cases (for example, in character-based languages), you may want to split on
characters or split on pairs or groups of words or even something more advanced. Groups of words
in a split are known as n-grams. Two- or three-word combinations are known as bigrams and
trigrams, respectively (and they’re the most common after one-word unigrams)

Stemming—which strips word suffixes—can also be a powerful transformation for
extracting more signals out of different words with similar meanings. Using stemming, for instance,
causes the words “jump,” “jumping,” “jumps,” and “jumped” to all be expressed as the token “jump”
in your dictionary.

After
defining the dictionary, you can convert any text to a set of numbers corresponding to the occurrences
of each dictionary word in the text. Figure 7.2 shows this process, which is called vectorization.

Most natural-language texts include many words
that aren’t important for understanding the topic, but are simply “filling.” These include words such
as “the,” “is,” and “and.” In NLP research, these are called stop words, and they’re usually removed
from the dictionary as they typically aren’t highly predictive of anything interesting and can dilute the
more meaningful words that are important from an ML perspective.

In some cases, though, the list of stop words will
be different for your specific project, and you’ll need to choose a stop-word threshold (a standard
choice is to exclude any words that appear in more than 90% of all documents).

> 167 

Term frequency-inverse document frequency

The tf can be calculated in different ways, but the simplest is to use the number of times a word
occurs in a particular document. It’s also common to use other versions of the tf factor, such as binary
(1 if the word is in a document, and 0 otherwise) and logarithmic (1 + log[tf]).
The inverse document frequency is calculated as the logarithm of the total number of documents,
divided by the number of documents that contain the term, so that relatively uncommon words attain
higher values.

> 168 

Latent semantic analysis, or LSA (also commonly called latent semantic indexing, or LSI) is a more
sophisticated method of topic modeling. It’s also more advanced both conceptually and
computationally. The idea is to use the bag-of-word counts to build a term-document matrix, with a
row for each term and a column for each document. The elements of this matrix are then normalized
similarly to the tf-idf process in order to avoid frequent terms dominating the power of the matrix.

The main trick of the LSA algorithm is in its notion of a concept. A concept is a pattern of similar
terms in the document corpus. For example, the concept of “dog” may have related terms (words, in
this case) of “barking,” “leash,” and “kennel.”

> 185 summary chapter 7

For text-based datasets, you need to transform variable-length documents to a fixed-length
number of features. Methods for this include the following:

Simple bag-of-words methods, in which particular words are counted for each document.
The tf-idf algorithm, which takes into account the frequency of words in the entire corpus to
avoid biasing the dictionary toward unimportant-but-common words.
More-advanced algorithms for topic modeling, such as latent semantic analysis and latent
Dirichlet analysis.
Topic-modeling techniques can describe documents as a set of topics, and topics as a set of
words. This allows sophisticated semantic understanding of documents and can help build
advanced search engines, for example, in addition to the usefulness in the ML world.
You can use the scikit-learn and Gensim Python libraries for many interesting experiments
in the field of text extraction.

For images, you need to be able to represent characteristics of the image with numeric features:

You can extract information about the colors in the image by defining color ranges and
color statistics.
You can extract potentially valuable image metadata from the image file itself; for example,
by tapping into the EXIF metadata available in most image files.
In some cases, you need to be able to extract shapes and objects from images. You can use
the following methods:
Simple edge-detection-based algorithms using Sobel or Canny edge-detection filters
Sophisticated shape-extraction algorithms such as histogram of oriented gradients
(HOG)
Dimensionality reduction techniques such as PCA
Automated feature extraction by using deep neural nets

Time-series data comes in two flavors: classical time series and point processes. A plethora of
ML features can be estimated from this data.
Two principal machine-learning tasks are performed on time-series data:

Forecasting the value of a single time series
Classifying a set of time series
For classical time series, the simplest features involve computing time-windowed summary
statistics and windowed differences.
More-sophisticated features involve the statistical characterization of the time series, using
tools such as autocorrelation and Fourier analysis.
Various classical time-series models can be used to derive features. These include AR,
ARMA, GARCH, and HMM.
From point-process data, you can compute all these features and more, because of the finer
granularity of the data.
Common models for point-process data include Poisson processes and nonhomogeneous
Poisson processes.


> 189 

For this reason, we recommend that holdout testing sets always be constructed with
temporal cutoffs, so that the testing set consists of instances that are newer than the training instances.

> 209 summary chapter 8

It’s essential to focus on the right problem. You should always start by asking, for each possible
use case, “What’s the value of solving this problem?”
For each use case, you need to inspect the data and systematically determine whether the data is
sufficient to solve the problem at hand.
Start with simple off-the-shelf algorithms to build an initial model whenever possible. In our
example, we predicted review sentiment with almost 90% accuracy.
Accuracy can be improved by testing and evaluating alternative models and combinations of
model parameters.
There are often trade-offs between different model parameters and evaluation criteria. We
looked at how the trade-off between false positive and false negative rates for movie reviews is
represented by the model’s ROC curve.
State-of-the-art natural-language and ML modeling techniques like word2vec are examples of
how advanced feature engineering may enable you to improve your models.
Your choice of algorithms may depend on factors other than model accuracy, such as training
time and the need to incorporate new data or perform predictions in near-real time.
In the real world, models can always be improved.

> 216

The Hadoop community has developed a machine-learning library called Mahout that implements a
range of popular ML algorithms that work with HDFS and MapReduce in the Hadoop framework. If
your data is in Hadoop, Mahout may be worth looking into for your machine-learning needs. Mahout
is moving away from the simplistic MapReduce framework into more-advanced distributed
computing approaches based on Apache Spark. Apache Spark, a more recent and widely popular
framework based on the ideas of Hadoop, strives to achieve better performance by working on data
in memory. Spark has its own library of machine-learning algorithms in the MLlib library included
with the framework. Figure 9.3 shows a simple diagram of the Apache Spark ecosystem.


> 225 summary chapter 9

Scaling up your machine-learning system is sometimes necessary. These are some common
reasons:
The training data doesn’t fit on a single machine.
The time to train a model is too long.
The volume of data coming in is too high.
The latency requirements for predictions are low.

Sometimes you can avoid spending time and resources on a scalable infrastructure by doing the
following:
Choosing a different ML algorithm that’s fast or lean enough to work on a single machine
without sacrificing accuracy
Subsampling the data
Scaling up vertically (upgrading the machine)
Sacrificing accuracy or easing other constraints if it’s still cheaper than scaling up

If it’s not possible to avoid scaling up in a horizontal fashion, widely used systems are available
for setting up a scalable data-management and processing infrastructure:
The Hadoop ecosystem with the Mahout machine-learning framework
The Spark ecosystem with the MLlib machine-learning library
The Turi (formerly GraphLab) framework
Streaming technologies such as Spark Streaming, Apache Storm, Apache Kafka, and AWS
Kinesis

When scaling up a model-building pipeline, consider the following:
Choosing a scalable algorithm such as logistic regression or linear SVMs
Scaling up other (for example, nonlinear) algorithms by making data and algorithm
approximations
Building a scalable version of your favorite algorithm using a distributed computing
infrastructure

Predictions can be scaled in both volume and velocity. Useful approaches include the following:
Building your infrastructure so that it allows you to scale up the number of workers with the
prediction volume
Sending the same prediction to multiple workers and returning the first one in order to
optimize prediction velocity
Choosing an algorithm that allows you to parallelize predictions across multiple machines

> 242 summary chapter 10

The first step is always to understand the business or activity you’re modeling, its objectives,
and how they’re measured. It’s also important to consider how your predictions can be acted on
—to anticipate what adjustments or optimizations can be made based on the insight you deliver.

Different feature-engineering strategies may yield very different working datasets. Casting a
wide net and considering a range of possibilities can be beneficial. In the first model, you
expanded the feature set vastly and then reduced it using SVD. In the second, you used simple
aggregations. Which approach works best depends on the problem and the data.

After exploring a subsample of data, you were able to estimate the computing resources needed
to perform your analyses. In our example, the bottleneck wasn’t the ML algorithms themselves,
but rather the collection and aggregation of raw data into a form suitable for modeling. This isn’t
unusual, and it’s important to consider both prerequisite and downstream workflow tasks when
you consider resource needs.

Often, the best model isn’t a single model, but an ensemble of models, the predictions of which
are aggregated by yet another predictive model. In many real-world problems, practical trade-
offs exist between the best possible ensembles and the practicality of creating, operating, and
maintaining complex workflows.

In the real world, there are often a few, and sometimes many, variations on the problem at hand.
We discussed some of these for advertising, and they’re common in any complex discipline.

The underlying dynamics of the phenomena you model often aren’t constant. Business, markets,
behaviors, and conditions change. When you use ML models in the real world, you must
constantly monitor their performance and sometimes go back to the drawing board.


> 244

www.predictiveanalyticstoday.com—For industry news
www.analyticbridge.com and its parent site, www.datasciencecentral.com
www.analyticsvidhya.com—Analytics news focused on learning
www.reddit.com/r/machinelearning—Machine-learning discussion
www.kaggle.com—Competitions, community, scripts, job board