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