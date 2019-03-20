
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

> 6.1 p76 Facts in Jess 

    It is important to recognize that we’re using the word fact in a specific, techni-
    cal sense, and the meaning differs slightly from the colloquial English usage. In
    rule-based systems terminology, fact is another word for working memory element. A
    fact is therefore the smallest unit of information that can be separately added to
    or removed from the working memory of a rule-based system. Jess facts aren’t gen-
    erally atomic; rather, they have some structure to them, as you’ll see in the follow-
    ing sections.

> 7 p96 Writing rules in Jess 

    There are two main classes of rules in Jess: forward-chaining and backward-chain-
    ing rules. Forward-chaining rules are somewhat like if ... then statements in a pro-
    cedural language, and they’re the most common and important kind of rule in
    Jess. Backward-chaining rules, on the other hand, don’t have a clear analogy in
    procedural programming. They are also similar to if ... then statements, but a
    backward-chaining rule actively tries to satisfy the conditions of its if part.

    You can access working memory directly with queries. You can design queries to
    search working memory, to find specific facts, and to explore their relationships.
    Queries have a lot in common with rules—if you can write one, you know how to
    write the other. You’ll learn how to write and invoke queries in section 7.7.


> 7.1 p 98 Forward-chaining rules

    Let me say that again: The left-hand side of a rule (the if part) consists of pat-
    terns that match facts; they are not function calls. The right-hand side of a rule
    (the then clause) is made up of function calls. The following rule does not work:
    Jess> (defrule wrong-rule
    (eq 1 1)
    =>
    (printout t "Just as I thought, 1 == 1!" crlf))
    Many novice Jess users write rules like this, intending (eq 1 1) to be interpreted as
    a function call. This rule will not fire just because the function call (eq 1 1) would
    evaluate to TRUE . Instead, Jess tries to find a fact in the working memory that looks
    like (eq 1 1) . Unless you have previously asserted such a fact, this rule will not be
    activated and will not fire. If you want to fire a rule based on the evaluation of a
    function, you can use the test conditional element, described in section 7.3.4.

> 7.2 Constraining slot data

    Most facts
    do, however, and most patterns need to specify some particular set of slot values
    for the facts they match. These specifications are called constraints, because they
    constrain the values a slot can have in a fact that matches the pattern. A number
    of different kinds of constraints can be used to match slot data:
        ■ Literal constraints—Specify exact slot values
        ■ Variable constraints—Bind a matched value to a variable
        ■ Connective constraints—Let you combine conditions to match A and B, or A or B
        ■ Predicate constraints—Let you call a function to test for a match
        ■ Return value constraints—Test for an exact match between a slot’s contents
    and the result of a function call


> 7.3 p108  Qualifying patterns with conditional elements

    We’ve just been looking at increasingly sophisticated ways to match the data
    within individual facts. Now we’ll look at ways to express more complex relation-
    ships between facts, and to qualify the matches for entire facts. Conditional elements
    (CEs) are pattern modifiers. They can group patterns into logical structures, and
    they can say something about the meaning of a match. There’s even one condi-
    tional element, test , that doesn’t involve matching a fact at all.
    Before we begin, let me caution you that many of these conditional elements
    have the same names as predicate functions we looked at in the last section.
    There’s an and conditional element, and there’s an and predicate function.
    Although they may look similar, they’re entirely unrelated. The and predicate
    function operates on Boolean expressions, but the and conditional element oper-
    ates on patterns. You can always tell which you’re dealing with by the context—
    predicate functions can appear only as constraints on slot data. The following are
    all of Jess’s conditional elements:
        ■ and —Matches multiple facts
        ■ or —Matches alternative facts
        ■ not —Matches if no facts match
        ■ exists —Matches if at least one fact matches
        ■ test —Matches if a function call doesn’t evaluate to FALSE
        ■ logical —Matching facts offer logical support to new facts

> 7.4 p116 Backward-chaining rules

    The rules you’ve seen so far have been forward-chaining rules; as I’ve said, that
    means the rules are treated as if ... then statements, with the engine simply exe-
    cuting the RHSs of activated rules. Some rule-based systems, notably Prolog and
    its derivatives, support backward chaining. In a backward-chaining system, rules are
    still if ... then statements, but the engine actively tries to make rules fire. If the if
    clause of one rule is only partially matched and the engine can determine that fir-
    ing some other rule would cause it to be fully matched, the engine tries to fire
    that second rule. This behavior is often called goal seeking.

    ...

    The head of the fact is constructed by taking the head of the reactive pattern and
    adding the prefix need- . These need-x facts are called goal-seeking or trigger facts.

> 7.5 p121 Managing the agenda

    A typical rule-based system may contain hundreds or thousands of rules. It’s very
    likely that at any given moment, more than one rule is activated. The set of acti-
    vated rules that are eligible to be fired is called the conflict set, and the process of
    putting the rules in firing order is called conflict resolution. The output of the con-
    flict-resolution process is the ordered list of activations called the agenda. You can
    see this ordered list of activated, but not yet fired, rules with the agenda function.

    Conflict resolution in Jess is controlled by pluggable conflict-resolution strategies.
    Jess comes with two strategies: depth (the default) and breadth. You can set the cur-
    rent strategy with the set-strategy command. Using (set-strategy depth)
    causes the most recently activated rules to fire first, and (set-strategy breadth)
    makes rules fire in activation order—the most recently activated rules fire last.

> 7.5.2 p122 Changing rule priority with salience

    Sometimes you may find that a particular rule should be treated as a special case
    by the conflict-resolution strategy. A rule that reports a security breach might
    need to fire immediately, regardless of what else is on the agenda. On the other
    hand, a rule that cleans up unused facts might only need to run during the idle
    time when no other rules are activated. You can tell the conflict resolver to treat
    these rules specially using rule salience.

    ...

    Note that extensive use of salience is generally discouraged, for two reasons.
    First, use of salience has a negative impact on performance, at least with the built-
    in conflict-resolution strategies. Second, it is considered bad style in rule-based
    programming to try to force rules to fire in a particular order. If you find yourself
    using salience on most of your rules, or if you are using more than two or three
    different salience values, you probably need to reconsider whether you should be
    using a rule-based approach to your problem. If you want strict control over exe-
    cution order, then you’re trying to implement a procedural program. Either
    change your rules to be less sensitive to execution order, or consider implement-
    ing your algorithm as one or more deffunction s or as Java code. Alternatively,
    you might consider structuring your program using modules.


> 7.6 p123 Partitioning the rule base with defmodule

    You might hope to mitigate the problem by partitioning a rule base into man-
    ageable chunks. The defmodule construct lets you divide rules and facts into dis-
    tinct groups called modules. Modules help you in two ways: First, they help you
    physically organize large numbers of rules into logical groups. The commands for
    listing constructs ( rules , facts , and so on) let you specify the name of a module
    and can then operate on one module at a time. Second, modules provide a con-
    trol mechanism: The rules in a module fire only when that module has the focus,
    and only one module can be in focus at a time (you’ll learn about module focus in
    section 7.6.3).
    We’ll discuss the following functions and constructs in this section:
        ■ clear-focus-stack —Empties the focus stack
        ■ defmodule —Defines a new module
        ■ focus —Sets the focus module
        ■ get-current-module —Returns the current module
        ■ get-focus-stack —Returns the focus stack’s contents as a list
        ■ list-focus-stack —Displays the focus stack’s contents
        ■ pop-focus —Pops a module from the focus stack

> p126 7.6.3 Module focus and execution control 

    Rule bases are commonly divided into modules along functional lines. For
    example, you might put all your input-gathering rules into one module, your
    data-processing rules into another, and your reporting rules into a third. Then,
    changing the focus from input, to processing, to output represents a natural pro-
    gression through well-defined phases of your application’s execution.


> p151 9.2 Introduction to knowledge engineering

    A good knowledge engineer has to be a jack of all trades, because knowledge
    engineering is really just learning—the knowledge engineer must learn a lot
    about the domain in which the proposed system will operate. A knowledge engi-
    neer doesn’t need to become an expert, although that sometimes happens. But
    the knowledge engineer does have to learn something about the topic. In gen-
    eral, this information will include:

        ■   The requirements—Looking at the problem the system needs to solve is the
            first step. However, you might not fully understand the problem until later
            in the process.
        ■   The principles—You need to learn the organizing principles of the field.
        ■   The resources—Once you understand the principles, you need to know where
            to go to learn more.
        ■   The frontiers—Every domain has its dark corners and dead ends. You need to
            find out where the tough bits, ambiguities, and limits of human understand-
            ing lie.

> p152 9.2.1 Where do you start ?

    Detailed comments like those shown here will help non-technical people read
    and understand the rules, if necessary. Buy a stack of white index cards and write
    each potential rule on one side of an individual card. Use pencil so you can make
    changes easily. The cards are useful because they let you group the rules accord-
    ing to function, required inputs, or other criteria. When you have a stack of 100
    cards or more, the utility becomes obvious. You can use the reverse sides of the
    cards to record issues regarding each rule. This stack of cards might be the final
    product of knowledge engineering, or the cards’ contents might be turned into a
    report. The cards themselves are often the most useful format, though.

    After organizing the new knowledge on index cards, you may see obvious gaps
    that require additional information. Develop a new set of interview questions and
    meet with the expert again. The appropriate number of iterations depends on the
    complexity of the system.
    Knowledge engineering doesn’t necessarily end when development begins.
    After an initial version of a system is available, the expert should try it out as a user
    and offer advice to correct its performance. If possible, a prototype of the system
    should be presented to the expert at every interview—except perhaps the first one.
    Likewise, development needn’t be deferred until knowledge engineering is
    complete. For many small projects, the knowledge engineer is one of the develop-
    ers, and in this case you may be able to dispense with the cards and simply encode
    the knowledge you collect directly into a prototype system. This is what you’ll do
    for the Tax Forms Advisor.

> p160 9.4 Summary

    The application area for a rule-based system is called its problem domain. The pro-
    cess of collecting information about a problem domain is called knowledge engineer-
    ing. Knowledge engineering can include gathering data from interviews, books
    and other publications, the Internet, and other sources.

> p166 10.5 Organizing the rules
    The Tax Forms Advisor needs to do four things:
        1 Initialize the application
        2 Conduct an interview with the user to learn about her tax situation
        3 Figure out what tax forms to recommend
        4 Present the list of forms to the user, removing any duplicate recommenda-
        tions in the process

