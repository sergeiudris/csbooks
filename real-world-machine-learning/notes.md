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

