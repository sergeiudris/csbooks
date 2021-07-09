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


    p 138

    The way you express your computations is crucially important if you want to avoid com-
    plexity, prevent bugs, and increase productivity. The main techniques for fighting com-
    plexity are abstraction and composition, and it’s important that your data-processing
    tool encourage these techniques rather than make them difficult.

    p 147

    Instead, you must
    take an iterative approach where each step modifies the graph to a state closer to the
    desired structure shown in figure 8.9. Once you’ve defined the iterative step, you exe-
    cute it repeatedly until no further progress is made. This is known as reaching a fixed
    point where the resulting output is the same as the input. When this point is reached,
    the graph has attained the desired state.

    p 183

    The normalization versus denormalization decision is ultimately a choice between
    unacceptable trade-offs. In the relational world, you want to store your data fully nor-
    malized; this involves defining relationships between independent datasets to mini-
    mize redundancy. Unfortunately, querying normalized data can be slow, so you may
    need to store some information redundantly to improve response times. This denor-
    malization process increases performance, but it comes with the huge complexity of
    keeping the redundant data consistent.
    To illustrate this tension, suppose you’re storing user location information in rela-
    tional tables, as shown in figure 10.6. Each location has an identifier, and each person
    uses one of those identifiers to indicate their location. A query to retrieve the location
    for a specific individual requires a join between the two tables. This is an example of a
    fully normalized schema, as no information is stored redundantly.

    p 209

    The basic objective of the speed layer is the same as for the batch and serving layers: to
    produce views that can be efficiently queried. The key differences are that the views
    only represent recent data and that they must be updated very shortly after new data
    arrives. What “very shortly” means varies per application, but it typically ranges from a
    few milliseconds to a few seconds.

    Suppose your data system receives 32 GB of new data per day, and that new data gets into the serving layer
    within 6 hours of being received.The speed layer would be responsible for at most 6
    hours of data—about 8 GB .

    p 212

    The CAP theorem is about fundamental trade-offs between consistency, where reads
    are guaranteed to incorporate all previous writes, and availability, where every query
    returns an answer instead of erroring.

    CAP is typically stated as “you can have at most two of consistency, availability, and
    partition-tolerance.” The problem with this explanation is that the CAP theorem is
    entirely about what happens to data systems when not all machines can communicate
    with each other. Saying a data system is consistent and available but not partition-tolerant
    makes no sense, because the theorem is entirely about what happens under partitions.

    The proper way to present the CAP theorem is that “when a distributed data sys-
    tem is partitioned, it can be consistent or available but not both.” Should you choose
    consistency, sometimes a query will receive an error instead of an answer. When you
    choose availability, reads may return stale results during network partitions. The best
    consistency property you can have in a highly available system is eventual consistency,
    where the system returns to consistency once the network partition ends.
        
    Some distributed databases have an option called sloppy quorums, which provides
    availability in the extreme—writes are accepted even if replicas for that data aren’t
    available. Instead, a temporary replica will be created and then merged into the official
    replicas once they become available. With sloppy quorums, the potential number of
    replicas for a piece of data is equal to the number of nodes in the cluster if every node
    is partitioned from every other node. While this can be useful, keep in mind that such
    an approach increases the incidental complexity of your system.

    p 215

    To implement eventually consistent counting correctly, you need to make use of
    structures called conflict-free replicated data types (commonly referred to as CRDT s).

    p 217

    There are uses for both synchronous and asynchronous updates. Synchronous
    updates are typical among transactional systems that interact with users and require
    coordination with the user interface. Asynchronous updates are common for analyt-
    ics-oriented workloads or workloads not requiring coordination. The architectural
    advantages of asynchronous updates—better throughput and better management of
    load spikes—suggest implementing asynchronous updates unless you have a good rea-
    son not to do so.    

    p 226

    This fire-and-forget scheme can’t guarantee that all the data is successfully pro-
    cessed. For example, if a worker dies before completing its assigned task, there’s no
    mechanism to detect or correct the error. The architecture is also susceptible to bursts
    in traffic that exceed the resources of the processing cluster. In such a scenario, the
    cluster would be overwhelmed and messages could be lost.
    Writing events to a persistent queue addresses both of these issues. Queues allow
    the system to retry events whenever a worker fails, and they provide a place for events
    to buffer when downstream workers hit their processing limits.

    p 228

    Discussing the limitations of a single-consumer queue helps identify the desired
    properties for a queuing system. What you really want is a single queue that can be
    used by many consumers, where adding a consumer is simple and introduces a mini-
    mal increase in load. When you think about this further, the fundamental issue with a
    single-consumer queue is that the queue is responsible for keeping track of what’s
    consumed. Because of the restrictive condition that an item is either “consumed” or
    “not consumed,” the queue is unable to gracefully handle multiple clients wanting to
    consume the same item.

    Thankfully there’s an alternative queue design that doesn’t suffer the problems associ-
    ated with single-consumer queues. The idea is to shift the responsibility of tracking
    the consumed/unconsumed status of events from the queue to the applications them-
    selves. If an application keeps track of the consumed events, it can then request the
    event stream to be replayed from any point in the stream history.

    p 229

    There’s another notable difference between single-consumer and multi-consumer
    queues. With a single-consumer queue, a message is deleted once it has been ack ed
    and can no longer be replayed. As a result, a failed event can cause the event stream to
    be processed out of order. To understand this behavior, if the stream is consumed in
    parallel and an event fails, other events subsequent to the failed event may be pro-
    cessed successfully before a reattempt is made. In contrast, a multi-consumer queue
    allows you to rewind the stream and replay everything from the point of failure, ensur-
    ing that you process events in the order in which they originated. The ability to replay
    the event stream is a great benefit of multi-consumer queues, and these queues don’t
    have any drawbacks when compared to single-consumer queues. Accordingly, we
    highly recommend using multi-consumer queues such as Apache Kafka.

    p 231

    The higher-level, one-at-a-time, stream-processing approach you’ll learn is a general-
    ization of the queues-and-workers model but without any of its complexities. Like
    queues and workers, the scheme processes tuples one tuple at a time, but the code
    runs in parallel across the cluster so that the system is scalable with high throughput.

    p 232

    Storm model
    The Storm model represents the entire stream-processing pipeline as a graph of com-
    putation called a topology. Rather than write separate programs for each node of the
    topology and connect them manually, as required in the queues-and-workers schemes,
    the Storm model involves a single program that’s deployed across a cluster.

    In essence, the Storm model is about trans-
    forming streams into new streams, potentially updating databases along the way.

    The next abstraction in the Storm model is the spout. A spout is a source of streams in
    a topology

    While spouts are sources of streams, the bolt abstrac-
    tion performs actions on streams. A bolt takes any
    number of streams as input and produces any num-
    ber of streams as output

    Having defined these abstractions, a topology is
    therefore a network of spouts and bolts with each
    edge representing a bolt that processes the output
    stream of another spout or bolt

    p 233

    Spouts and bolts consist of
    multiple tasks that are
    executed in parallel. A bolt
    task receives tuples from all
    tasks that generate the bolt’s
    input stream.

    p 247

    it’s possible to guarantee message processing with the
    Storm model without intermediate message queues. When a failure is detected down-
    stream from the spout in the tuple DAG , tuples can be retried from the spout.

    p 285

    Latency—The time it takes to run a query. In many cases, your latency require-
    ments will be very low—on the order of milliseconds. Other times it’s okay for a
    query to take a few seconds. When doing ad hoc analysis, your latency require-
    ments are often very lax, even on the order of hours.

    Timeliness—How up-to-date the query results are. A completely timely query
    takes into account all data ever seen in the past, whereas a less timely query may
    not include results from the recent minutes or hours.

    Accuracy—In many cases, in order to make queries performant or scalable, you
    must make approximations in your query implementations.

    The CAP theorem shows that under par-
    titions, a system can either be consistent (queries take into account all previous written
    data) or available (queries are answered at the moment). Consistency is just a form of
    timeliness, and availability just means the latency of the query is bounded. An eventu-
    ally consistent system chooses latency over timeliness (queries are always answered, but
    may not take into account all prior data during failure scenarios)

    p 286

    This leads us to the basic model of data systems:
      ■ A master dataset consisting of an ever-growing set of data
      ■ Queries as functions that take in the entire master dataset as input

    p 288

    There’s an alternative that blurs the line between incrementalization and recompu-
    tation and gets you the best of both worlds. This technique is called partial recomputation.

    
