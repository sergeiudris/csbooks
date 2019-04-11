### big data

https://medium.com/red-planet-labs/introducing-red-planet-labs-2a0304a67312

  > p7

    When you keep tracing back where information is derived from, you eventually
    end up at information that’s not derived from anything. This is the rawest information
    you have: information you hold to be true simply because it exists. Let’s call this infor-
    mation data.

    ...

    If a data system answers questions by looking at past data, then the most general-
    purpose data system answers questions by looking at the entire dataset. So the most
    general-purpose definition we can give for a data system is the following:
    
    query = function ( all data )





  > p8

    Oftentimes a new feature or a change to an existing feature requires a migration of
    old data into a new format. Part of making a system extensible is making it easy to do
    large-scale migrations. Being able to do big migrations quickly and easily is core to the
    approach you’ll learn.

    [disagree: you shoudn't need to migrate. ever. easier said though..]

    ...


    Being able to do ad hoc queries on your data is extremely important. Nearly every
    large dataset has unanticipated value within it. Being able to mine a dataset arbitrarily
    gives opportunities for business optimization and new applications.

  > p9

    At the highest level, traditional architectures look like figure 1.3. What characterizes
    these architectures is the use of read/write databases and maintaining the state in those
    databases incrementally as new data is seen. For
    example, an incremental approach to count-
    ing pageviews would be to process a new
    pageview by adding one to the counter for its
    URL .
    This characterization of architectures is a
    lot more fundamental than just relational versus non-relational—in fact, the vast major-
    ity of both relational and non-relational database deployments are done as fully incre-
    mental architectures. This has been true for many decades.

    It’s worth emphasizing that fully incremental architectures are so widespread that
    many people don’t realize it’s possible to avoid their problems with a different archi-
    tecture. These are great examples of familiar complexity—complexity that’s so
    ingrained, you don’t even think to find a way to avoid it.


  > p10

    In a read/write database, as a disk index is incrementally added to and modified,
    parts of the index become unused. These unused parts take up space and eventually
    need to be reclaimed to prevent the disk from filling up. Reclaiming space as soon as
    it becomes unused is too expensive, so the space is occasionally reclaimed in bulk in a
    process called compaction.

    ...

    Dealing with online compaction is a complexity
    inherent to fully incremental architectures, but in a Lambda Architecture the primary
    databases don’t require any online compaction.


    > 11

    It turns out that achieving high availability competes directly with another impor-
    tant property called consistency. A consistent system returns results that take into
    account all previous writes.

    > 15

    Let’s call the precomputed query function the batch view.

    batch view = function ( all data )
    query = function ( batch view )

    15

    Because this discussion is somewhat abstract, let’s ground it with an example. Sup-
    pose you’re building a web analytics application (again), and you want to query the
    number of pageviews for a URL on any range of days. If you were computing the query
    as a function of all the data, you’d scan the dataset for pageviews for that URL within
    that time range, and return the count of those results.
    The batch view approach instead runs a function on all the pageviews to precom-
    pute an index from a key of [url, day] to the count of the number of pageviews for
    that URL for that day. Then, to resolve the query, you retrieve all values from that view
    for all days within that time range, and sum up the counts to get the result. This
    approach is shown in figure 1.7.

    # batch layer

    The portion of the Lambda Architecture
    that implements the batch view = function(all
    data) equation is called the batch layer. The
    batch layer stores the master copy of the
    dataset and precomputes batch views on that
    master dataset (see figure 1.8). The master
    dataset can be thought of as a very large list
    of records.

    # serving layer

    The serving layer is a specialized
    distributed database that loads in a batch
    view and makes it possible to do random
    reads on it
    When new
    batch views are available, the serving layer
    automatically swaps those in so that more
    up-to-date results are available

    * innately
        as an inborn characteristic; naturally.

    # speed layer

    You can think of the speed layer as being similar to the batch layer in that it produces
    views based on data it receives. One big difference is that the speed layer only looks at
    recent data, whereas the batch layer looks at all the data at once. Another big difference
    is that in order to achieve the smallest
    latencies possible, the speed layer doesn’t look at all the new data at once.
    Instead, it updates the realtime views as it receives new data instead of recomput-
    ing the views from scratch like the batch layer does.

    realtime view = function ( realtime view, new data )

    # Lambda Architecture

    batch view = function ( all data )
    realtime view = function ( realtime view, new data )
    query = function ( batch view. realtime view )

    p 20

    This property of the Lambda Architecture is called com-
    plexity isolation, meaning that complexity is pushed into a layer whose results are only
    temporary.

    * transient
        lasting only for a short time; impermanent.
    
    > Vibrant open source ecosystem for Big Data

    - Batch computation systems
        HDFS Hadoop MapReduce
    - Serialization frameworks
        Thrift, Protocol Buffers, and Avro
    - Random-access No SQL databases
        Cassandra, HB ase, Mongo DB , Voldemort, Riak, Couch DB , and others
    - Messaging/queuing systems
        Kafka
    - Realtime computation system
        Storm

    p 29

    - Information is the general collection of knowledge relevant to your Big Data sys-
      tem. It’s synonymous with the colloquial usage of the word data.

    - Data refers to the information that can’t be derived from anything else. Data
      serves as the axioms from which everything else derives.

    - Queries are questions you ask of your data. For example, you query your finan-
      cial transaction history to determine your current bank account balance.

    - Views are information that has been derived from your base data. They are built
      to assist with answering specific types of queries.


    the key properties of data: rawness, immutability, and perpetuity (or the “eternal trueness of data”)

    p 32

    When deciding what raw data to store, a common hazy area is the line between parsing
    and semantic normalization. Semantic normalization is the process of reshaping free-
    form information into a structured form of data

    ...

    STORE UNSTRUCTURED DATA WHEN... As a rule of thumb, if your algorithm for
    extracting the data is simple and accurate, like extracting an age from an
    HTML page, you should store the results of that algorithm. If the algorithm is
    subject to change, due to improvements or broadening the requirements,
    store the unstructured form of the data.

    > 36

    Garbage collection—When you perform garbage collection, you delete all data
    units that have low value. You can use garbage collection to implement data-
    retention policies that control the growth of the master dataset. For example,
    you may decide to implement a policy that keeps only one location per person
    per year instead of the full history of each time a user changes locations.

    Regulations—Government regulations may require you to purge data from your
    databases under certain conditions.

    > 37

    Data is the set of information that can’t be derived from anything else

    ...

    Facts are atomic because they can’t be subdivided further into meaningful compo-
    nents

    To distinguish different pageviews, you can add a nonce to your schema—a 64-bit
    number randomly generated for each pageview
    The nonce, combined with the
    other fields, uniquely identifies
    a particular pageview.

    > 39

    To quickly recap, the fact-based model
      - Stores your raw data as atomic facts
      - Keeps the facts immutable and eternally true by using timestamps
      - Ensures each fact is identifiable so that query processing can identify duplicates

    ...

    The good news is
    that by changing your data model paradigm, you gain numerous advantages. Specifi-
    cally, your data

      - Is queryable at any time in its history
      - Tolerates human errors
      - Handles partial information
      - Has the advantages of both normalized and denormalized forms

    > 40

    Data normalization is completely
    unrelated to the semantic normalization term that we used earlier. In this case,
    data normalization refers to storing data in a structured manner to minimize
    redundancy and promote consistency.

    > 43

    The figure highlights the three core components of a graph schema—nodes, edges,
    and properties


    p 46

    How you model your master dataset creates the foundation for your Big Data system.
    The decisions made about the master dataset determine the kind of analytics you can
    perform on your data and how you’ll consume that data. The structure of the master
    dataset must support evolution of the kinds of data stored, because your company’s
    data types may change considerably over the years.
    The fact-based model provides a simple yet expressive representation of your data
    by naturally keeping a full history of each entity over time. Its append-only nature
    makes it easy to implement in a distributed system, and it can easily evolve as your data
    and your needs change. You’re not just implementing a relational system in a more
    scalable way—you’re adding whole new capabilities to your system as well.

    P 48

    * rigorous
      - strictly applied or adhered to

    p 53

    The right way to think about a schema is as a function that takes in a piece of data
    and returns whether it’s valid or not. The schema language for Apache Thrift lets you
    represent a subset of these functions where only field existence and field types are
    checked. The ideal tool would let you implement any possible schema function.

    Such an ideal tool—particularly one that is language neutral—doesn’t exist, but
    there are two approaches you can take to work around these limitations with a serial-
    ization framework like Apache Thrift:

    - Wrap your generated code in additional code that checks the additional properties you care
      about, like ages being non-negative.
    - Check the extra properties at the very beginning of your batch-processing workflow


    p 61

    Although the batch layer is built to run functions on the entire dataset, many compu-
    tations don’t require looking at all the data. For example, you may have a computa-
    tion that only requires information collected during the past two weeks. The batch
    storage should allow you to partition your data so that a function only accesses data
    relevant to its computation. This process is called vertical partitioning, and it can greatly
    contribute to making the batch layer more efficient.

    p 75

    Whenever records are added to this pail, they’ll be automatically compressed. This
    pail will use significantly less space but will have a higher CPU cost for reading and
    writing records.
    
    p 76

    The unions within a graph schema provide a natural vertical partitioning scheme for a dataset.

    p 91

    The key takeaway is that you must always have recomputation versions of your
    algorithms. This is the only way to ensure human-fault tolerance for your system,
    and human-fault tolerance is a non-negotiable requirement for a robust system.

    * ad hoc
      Ad hoc is a Latin phrase meaning literally "for this". In English, it generally signifies a solution designed for a specific problem or task, non-generalizable, and not intended to be able to be adapted to other purposes (compare with a priori).

    p 92

    Scalability is the ability of a system to maintain performance
    under increased load by adding more resources.
    
    Load in a Big Data context is a combi-
    nation of the total amount of data you have, how much new data you receive every
    day, how many requests per second your application serves, and so forth.

    p 93

    We delved into this discussion about scalability to set the scene for introducing Map-
    Reduce, a distributed computing paradigm that can be used to implement a batch
    layer. As we cover the details of its workings, keep in mind that it’s linearly scalable:
    should the size of your master dataset double, then twice the number of servers will be
    able to build the batch views with the same latency.

    p 96

    Because tasks can be retried, MapReduce requires that your map and reduce func-
    tions be deterministic. This means that given the same inputs, your functions must
    always produce the same outputs.

    p 98

    MapReduce vs. Spark
    Spark is a relatively new computation system that has gained a lot of attention.
    Spark’s computation model is “resilient distributed datasets.” Spark isn’t any more
    general or scalable than MapReduce, but its model allows it to have much higher per-
    formance for algorithms that have to repeatedly iterate over the same dataset
    (because Spark is able to cache that data in memory rather than read it from disk
    every time). Many machine-learning algorithms iterate over the same data repeatedly,
    making Spark particularly well suited for that use case.

    https://github.com/apache/spark

    p 101

    And so you have a tough trade-off to make—either weave all the functionality
    together, engaging in bad software-engineering practices, or modularize the code,
    leading to poor resource usage.

    p 102

    Pipe diagrams in practice
    Pipe diagrams aren’t a hypothetical concept; all of the higher-level MapReduce tools
    are a fairly direct mapping of pipe diagrams, including Cascading, Pig, Hive, and Cas-
    calog. Spark is too, to some extent, though its data model doesn’t natively include
    the concept of tuples with an arbitrary number of named fields.

    ...

    The idea behind pipe diagrams is to think of processing in
    terms of tuples, functions, filters, aggregators, joins, and
    merges—concepts you’re likely already familiar with from
    SQL .

    p 108

    All combiner aggregators work this way—doing a par-
    tial aggregation first and then combining the partial
    results to get the desired result. Not every aggregator can
    be expressed this way, but when it’s possible you get huge
    performance and scalability boosts when doing global
    aggregations or aggregations with very few groups. Count-
    ing and summing, two of the most common aggregators,
    can be implemented as combiner aggregators.

    p 114

    Complexity in code arises in two
    forms: essential complexity that is inherent in the problem to be solved, and accidental
    complexity that arises solely from the approach to the solution.

    ...

    A common source of complexity in data-processing tools is the use of custom lan-
    guages. Examples of this include SQL for relational databases or Pig and Hive for
    Hadoop.

    [bold...]

    ....

    The use of custom languages introduces a language barrier that requires an inter-
    face to interact with other parts of your code. This interface is a common source of
    errors and an unavoidable source of complexity.

    Modulariza-
    tion can become painful—the custom language may support namespaces and func-
    tions, but ultimately these are not going to be as good as their general-purpose
    language counterparts.

    if you want to incorporate your own business
    logic into queries, you must create your own user-defined functions ( UDF s) and regis-
    ter them with the language.

    Lastly, you have to coordinate switching between your general-purpose language
    and your data-processing language.

    p 115

    Another common source of accidental complexity can occur when using multiple
    abstractions in conjunction. It’s important that your abstractions can be composed
    together to create new and greater abstractions—otherwise you’re unable to reuse
    code and you keep reinventing the wheel in slightly different ways.

    p 116

    JC ascalog manipulates and transforms tuples—named lists of values where each value
    can be any type of object. A set of tuples shares a schema that specifies how many fields
    are in each tuple and the name of each field.

    p 118

    A key design decision for JC ascalog was to make all predicates share a common struc-
    ture. The first argument to a predicate is the predicate operation, and the remaining
    arguments are parameters for that operation. For function and aggregator predicates,
    the labels for the outputs are specified using the out method.

    .predicate(new Plus(), 2, "?x").out(6)

    .predicate(new Multiply(), 2, "?a").out("?z") 
    .predicate(new Multiply(), 3, "?b").out("?z")


    
