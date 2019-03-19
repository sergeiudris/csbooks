
## Notes from 'Jess in action: java rule based systems'




### citations

> 1.2.3  p11  Implementing business rules

    Corporations invariably define policies and strategies that specify how the busi-
    ness should respond to events ranging from individual sales to hostile takeover
    attempts. A business rule is a policy or strategy written in executable form, such that
    a computer can follow it. Here are two simple examples of business rules govern-
    ing common situations:
    IF
    employee's length of service > 1 year
    AND employee category is regular employee
    AND employee contributes to 401k plan
    THEN
    employee is vested in 401k plan
    END
    IF
    customer order is more than ten units
    AND customer type is wholesaler
    THEN
    deduct 10 percent from order
    END
    If a company’s business rules are implicit—not written as rules per se, but embed-
    ded in procedural logic—and scattered throughout corporate computer applica-
    tions, then a change in a single policy might require significant programmer
    effort to implement. Furthermore, if business rules are to be embedded directly
    into application software, it becomes difficult to use commercial, off-the-shelf
    (COTS) products, increasing the company’s development costs. The corporation
    will be forced to make a choice between containing development costs and mak-
    ing policy adjustments in response to changing circumstances.

    ...

    The solution to this dilemma is to remove the business rules from the individ-
    ual applications, make them explicit, and embed them in a centralized rule engine
    for execution. Any business policy can then theoretically be changed at a single
    point. The rule engine is often embedded in a network-based server so that it can
    be accessible across an enterprise.

> 2.1.1 p15 Declarative programming: a different approach

    A purely declarative program, in contrast, describes what the computer should
    do, but omits much of the instructions on how to do it. Declarative programs 
    must be executed by some kind of runtime system that understands how to fill in
    the blanks and use the declarative information to solve problems. Because declar-
    ative programs include only the important details of a solution, they can be easier
    to understand than procedural programs.
    ...
    Declarative programming is often the natural way to tackle problems involving control,
    diagnosis, prediction, classification, pattern recognition, or situational aware-
    ness—in short, many problems without clear algorithmic solutions. Programming
    a cooking, driving robot declaratively will be a breeze!

    ...

    procedural:

    START
    putOnTable(Bowl)
    putOnTable(Spoon)
    putOnTable(Napkin)
    open(Cereal)
    pour(Cereal)
    open(Milk)
    pour(Milk)
    eat(Cereal, Spoon)
    END

    declarative rules:

    IF
    the engine has stalled
    THEN
    start car
    END

    IF
    you hear sirens
    AND you are driving
    THEN
    pull over to curb
    END

    IF
    you see brake lights
    AND you are getting closer to them
    THEN
    depress the brake pedal
    END

    ...

    In a rule-based program, you write only the individual rules. Another program,
    the rule engine, determines which rules apply at any given time and executes
    them as appropriate.

    ...

    Modifying the program is also simpler—if you’ve ever had to work on a
    program containing a dozen levels of nested if statements, you’ll understand
    why.

> 2.2 p17 Rules and rule engines

    A rule-based system is a system that uses rules to derive conclusions from premises:
    Given the gum-chewing rule and the premise that you are in school, you (as an
    advanced kind of rule-based system yourself) might conclude that it’s time to spit
    out your gum.
    In this book, the systems we’re talking about are a specific category
    of computer programs. These programs are sometimes called rule engines. A rule
    engine doesn’t contain any rules until they are programmed in. A rule engine
    knows how to follow rules, without containing any specific knowledge itself.
    A rule engine is generally part of a rule development and deployment environment.

> 2.2.1 p18 Expert Systems

    Expert systems, rule-based computer programs that capture the knowledge of
    human experts in their own fields of expertise, were a success story for artificial
    intelligence research in the 1970s and 1980s. Early, successful expert systems
    were built around rules (sometimes called heuristics) for medical diagnosis, engi-
    neering, chemistry, and computer sales. One of the early expert system successes
    was MYCIN , 2 a program for diagnosing bacterial infections of the blood.
    ...
    Over time, of course, the drama receded, and it became clear that researchers
    had vastly underestimated the complexity of the common-sense knowledge that
    underpins general human reasoning. Nevertheless, excellent applications for
    expert systems remain to this day. Modern expert systems advise salespeople, sci-
    entists, medical technicians, engineers, and financiers, among others.

> 2.3 p19 Architecture of a rule-based system

    A typical rule engine contains:
        ■ An inference engine
        ■ A rule base
        ■ A working memory
    The inference engine, in turn, consists of:
        ■ A pattern matcher
        ■ An agenda
        ■ An execution engine

    The architecture of a typical
    rule-based system. The pattern-
    matcher applies the rules in the
    rule-base to the facts in working
    memory to construct the
    agenda. The execution engine
    fires the rules from the agenda,
    which changes the contents of
    working memory and restarts
    the cycle.

    The inference engine controls the whole process of applying the rules to the
    working memory to obtain the outputs of the system. Usually an inference
    engine works in discrete cycles that go something like this:

    1 All the rules are compared to working memory (using the pattern matcher)
    to decide which ones should be activated during this cycle. This unordered
    list of activated rules, together with any other rules activated in previous
    cycles, is called the conflict set.

    2 The conflict set is ordered to form the agenda—the list of rules whose
    right-hand sides will be executed, or fired. The process of ordering the
    agenda is called conflict resolution. The conflict resolution strategy for a
    given rule engine will depend on many factors, only some of which will
    be under the programmer’s control.

    3 To complete the cycle, the first rule on the agenda is fired (possibly
    changing the working memory) and the entire process is repeated. This
    repetition implies a large amount of redundant work, but many rule
    engines use sophisticated techniques to avoid most or all of the redun-
    dancy. In particular, results from the pattern matcher and from the
    agenda’s conflict resolver can be preserved across cycles, so that only the
    essential, new work needs to be done

> 2.3.2 p20 The rule base

    Your rule engine will obviously need somewhere to store rules. The rule base con-
    tains all the rules the system knows. They may simply be stored as strings of text,
    but most often a rule compiler processes them into some form that the inference
    engine can work with more efficiently. For an email filter, the rule compiler might
    produce tables of patterns to search for and folders to file messages in.

    Some rule engines allow (or require) you to store the rule base in an external
    relational database, and others have an integrated rule base. Storing rules in a
    relational database allows you to select rules to be included in a system based on
    criteria like date, time, and user access rights.

> 2.3.3 p21 The working memory

    You also need to store the data your rule engine will operate on. In a typical rule
    engine, the working memory, sometimes called the fact base, contains all the pieces
    of information the rule-based system is working with. The working memory can
    hold both the premises and the conclusions of the rules. Typically, the rule
    engine maintains one or more indexes, similar to those used in relational data-
    bases, to make searching the working memory a very fast operation.

> 2.3.4 p22 The pattern matcher

    Your inference engine has to decide what rules to fire, and when. The purpose of
    the pattern matcher is to decide which rules apply, given the current contents of
    the working memory.

> 2.3.5 p22 The agenda

    Once your inference engine figures out which rules should be fired, it still must
    decide which rule to fire first. The list of rules that could potentially fire is stored
    on the agenda. The agenda is responsible for using the conflict strategy to decide
    which of the rules, out of all those that apply, have the highest priority and
    should be fired first.

> 2.3.6 p23 The execution engine

    Finally, once your rule engine decides what rule to fire, it has to execute that
    rule’s action part. The execution engine is the component of a rule engine that
    fires the rules. In a classical production system such as MYCIN, rules could do
    nothing but add, remove, and modify facts in the working memory. In modern
    rule engines, firing a rule can have a wide range of effects. Some modern rule
    engines (like Jess) offer a complete programming language you can use to define
    what happens when a given rule fires. The execution engine then represents the
    environment in which this programming language executes. For some systems,
    the execution engine is a language interpreter; for others, it is a dispatcher that
    invokes compiled code.

> 6.1 p76

    It is important to recognize that we’re using the word fact in a specific, techni-
    cal sense, and the meaning differs slightly from the colloquial English usage. In
    rule-based systems terminology, fact is another word for working memory element. A
    fact is therefore the smallest unit of information that can be separately added to
    or removed from the working memory of a rule-based system. Jess facts aren’t gen-
    erally atomic; rather, they have some structure to them, as you’ll see in the follow-
    ing sections.