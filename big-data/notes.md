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


