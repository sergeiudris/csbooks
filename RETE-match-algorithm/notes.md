https://en.wikipedia.org/wiki/Charles_Forgy <br/>
https://en.wikipedia.org/wiki/Expert_system <br/>
https://en.wikipedia.org/wiki/Rule-based_machine_learning <br/>

### Rete: A Fast Algorithm for the Many PatternIMany Object Pattern Match Problem
    
    Charles L. Forgy
    Department of Computer Science, Carnegie-Mellon University,
    Pittsburgh, P A 15213, U.S.A.


> p17 Introduction

    The Rete Match Algorithm is an efficient method for comparing a large collection of patterns to a large
    collection of objects. Itfinds all the objects that match eachpattern. The algorithm was developedfor use in
    production system interpreters, and it has been used for systems containing from a few hundred to more
    than a thousand patterns and objects.

    In many patternlmany object pattern matching, a collection of patterns is
    compared to a collection of objects, and all the matches are determined. That
    is, the pattern matcher finds every object that matches each pattern. This kind
    of pattern matching is used extensively in Artificial Intelligence programs
    today. For instance, it is a basic component of production system interpreters.
    The interpreters use it t o determine which productions have satisfied condition
    parts.

> p18 

    A production system program consists of an unordered collection of If-Then
    statements called productions. The data operated on by the productions is held
    in a global data base called working memory. By convention, the If part of a
    production is called its LHS (left-hand side), and its Then part is called its RHS
    (right-hand side). The interpreter executes a production system by performing
    the following operations.

        (1) Match. Evaluate the LHSs of the productions to determine which are
        satisfied given the current contents of working memory.
        (2) Conflict resolution. Select one production with a satisfied LHS; if no
        productions have satisfied LHSs, halt the interpreter.
        (3) Act. Perform the actions in the RHS of the selected production.
        (4) Got0 1.

    The LHS of a production consists of a sequence of patterns; that is, a
    sequence of partial descriptions of working memory elements. When a pattern
    P describes an element E, P is said to match E. In some productions, some of
    the patterns are preceded by the negation symbol, -. An LHS is satisfied when
        (1) Every pattern that is not preceded by-matches a working memory
        element, and
        (2) No pattern that is preceded by - matches a working memory element.

    
> p20 Work on production system efficiency

    Since execution speed has always been a major issue for production systems,
    several researchers have worked on the problem of efficiency. The most
    common approach has been to combine a process called indexing with direct
    interpretation of the LHSs. In the simplest form of indexing, the interpreter
    begins the match process by extracting one or more features from each working
    memory element, and uses those features to hash into the collection of
    productions. This produces a set of productions that might have satisfied LHSs.
    The interpreter examines each LHS in this set individually to determine
    whether it is in fact satisfied

    A more efficient form of indexing adds memory to
    the process. A typical scheme involves storing a count with each pattern. The
    counts are all zero when execution of the system begins. When an element
    enters working memory, the indexing function is executed with the new
    element as its only input, and all the patterns that are reached have their
    counts increased by one. When an element leaves working memory, the index
    is again executed, and the patterns that are reached have their counts
    decreased by one. The interpreter performs the direct interpretation step only
    on those LHSs that have non-zero counts for all their patterns.

> p21 How to avoid iterating over working memory

    A pattern matcher can avoid iterating over the elements in working memory by
    storing information between cycles. The step that can require iteration is
    determining whether a given pattern matches any of the working memory
    elements. The simplest interpreters determine this by comparing the pattern to
    the elements one by one. The iteration can be avoided by storing, with each
    pattern, a list of the elements that it matches. The lists are updated when
    working memory changes. When an element enters working memory, the
    interpreter finds all the patterns that match it and adds it to their lists. When an
    element leaves working memory, the interpreter again finds all the patterns
    that match it and deletes it from their lists.

    Since pattern matchers using the Rete algorithm save this kind of in-
    formation, they never have to examine working memory. The pattern matcher
    can be viewed as a black box with one input and one output.

    (Changes to Working Memory) -> Black Box -> (Changes to the Conflict Set)

    The descriptions of working memory changes that are passed into the black
    box are called tokens. A token is an ordered pair of a tag and a list of data
    elements. In the simplest implementations of the Rete Match Algorithm, only
    two tags are needed, + and -. The tag + indicates that something has been
    added to working memory. The tag - indicates that something has been
    deleted from working memory. When an element is modified, two tokens are
    sent to the black box; one token indicates that the old form of the element has
    been deleted from working memory, and the other that the new form of the
    element has been added.