
### Structure and Interpretation of Computer Programs second edition 1996

    This book is dedicated, in respect and admiration, to the spirit that
    lives in the computer.

    “I think that it’s extraordinarily important that we in com-
    puter science keep fun in computing. When it started out,
    it was an awful lot of fun. Of course, the paying customers
    got shaed every now and then, and aer a while we began
    to take their complaints seriously. We began to feel as if we
    really were responsible for the successful, error-free perfect
    use of these machines. I don’t think we are. I think we’re
    responsible for stretching them, seing them off in new di-
    rections, and keeping fun in the house. I hope the field of
    computer science never loses its sense of fun. Above all, I
    hope we don’t become missionaries. Don’t feel as if you’re
    Bible salesmen. e world has too many of those already.
    What you know about computing other people will learn.
    Don’t feel as if the key to successful computing is only in
    your hands. What’s in your hands, I think and hope, is in-
    telligence: the ability to see the machine as more than when
    you were first led up to it, that you can make it more.”
    —Alan J. Perlis (April 1, 1922 – February 7, 1990)

    ...

    Since large programs grow from
    small ones, it is crucial that we develop an arsenal of standard program
    structures of whose correctness we have become sure—we call them
    idioms—and learn to combine them into larger structures using orga-
    nizational techniques of proven value.

    ...

    The computers are never large enough or
    fast enough. Each breakthrough in hardware technology leads to more
    massive programming enterprises, new organizational principles, and
    an enrichment of abstract models. Every reader should ask himself pe-
    riodically “Toward what end, toward what end?”—but do not ask it too
    often lest you pass up the fun of programming for the constipation of
    bittersweet philosophy.

    ---

    Lisp changes. e Scheme dialect used in this text has evolved from
    the original Lisp and differs from the laer in several important ways,
    including static scoping for variable binding and permiing functions to
    yield functions as values. In its semantic structure Scheme is as closely
    akin to Algol 60 as to early Lisps. Algol 60, never to be an active language
    again, lives on in the genes of Scheme and Pascal. It would be difficult
    to find two languages that are the communicating coin of two more dif-
    ferent cultures than those gathered around these two languages. Pas-
    cal is for building pyramids—imposing, breathtaking, static structures
    built by armies pushing heavy blocks into place. Lisp is for building
    organisms—imposing, breathtaking, dynamic structures built by squads
    fiing fluctuating myriads of simpler organisms into place. e organiz-
    ing principles used are the same in both cases, except for one extraordi-
    narily important difference: e discretionary exportable functionality
    entrusted to the individual Lisp programmer is more than an order of
    magnitude greater than that to be found within Pascal enterprises. Lisp
    programs inflate libraries with functions whose utility transcends the
    application that produced them. e list, Lisp’s native data structure, is
    largely responsible for such growth of utility. e simple structure and
    natural applicability of lists are reflected in functions that are amazingly
    nonidiosyncratic. In Pascal the plethora of declarable data structures in-
    duces a specialization within functions that inhibits and penalizes ca-
    sual cooperation. It is beer to have 100 functions operate on one data
    structure than to have 10 functions operate on 10 data structures. As a
    result the pyramid must stand unchanged for a millennium; the organ-
    ism must evolve or perish.

    ...

    Finally, it is this very simplicity of syntax and se-
    mantics that is responsible for the burden and freedom borne by all
    Lisp programmers. No Lisp program of any size beyond a few lines can
    be wrien without being saturated with discretionary functions. Invent
    and fit; have fits and reinvent! We toast the Lisp programmer who pens
    his thoughts within nests of parentheses.

    Alan J. Perlis


    Is it possible that soware is not like anything else, that it
    is meant to be discarded: that the whole point is to always
    see it as a soap bubble?

    —Alan J. Perlis

    Our design of this introductory computer-science subject reflects
    two major concerns. First, we want to establish the idea that a com-
    puter language is not just a way of geing a computer to perform oper-
    ations but rather that it is a novel formal medium for expressing ideas
    about methodology. us, programs must be wrien for people to read,
    and only incidentally for machines to execute. Second, we believe that
    the essential material to be addressed by a subject at this level is not
    the syntax of particular programming-language constructs, nor clever
    algorithms for computing particular functions efficiently, nor even the
    mathematical analysis of algorithms and the foundations of computing,
    but rather the techniques used to control the intellectual complexity of
    large soware systems.

    Our goal is that students who complete this subject should have a
    good feel for the elements of style and the aesthetics of programming.
    ey should have command of the major techniques for controlling
    complexity in a large system. ey should be capable of reading a 50-
    page-long program, if it is wrien in an exemplary style. ey should
    know what not to read, and what they need not understand at any mo-
    ment. ey should feel secure about modifying a program, retaining the
    spirit and style of the original author.
    ese skills are by no means unique to computer programming. e
    techniques we teach and draw upon are common to all of engineering
    design. We control complexity by building abstractions that hide details
    when appropriate. We control complexity by establishing conventional
    interfaces that enable us to construct systems by combining standard,
    well-understood pieces in a “mix and match” way. We control complex-
    ity by establishing new languages for describing a design, each of which
    emphasizes particular aspects of the design and deemphasizes others.

    Underlying our approach to this subject is our conviction that “com-
    puter science” is not a science and that its significance has lile to do
    with computers. e computer revolution is a revolution in the way we
    think and in the way we express what we think. e essence of this
    change is the emergence of what might best be called procedural epis-
    temology —the study of the structure of knowledge from an imperative
    point of view, as opposed to the more declarative point of view taken
    by classical mathematical subjects. Mathematics provides a framework
    for dealing precisely with notions of “what is.” Computation provides a
    framework for dealing precisely with notions of “how to.”

    https://en.wikipedia.org/wiki/Epistemology

    ...

    Aer a short time we forget about syntactic details of the lan-
    guage (because there are none) and get on with the real issues—figuring
    out what we want to compute, how we will decompose problems into
    manageable parts, and how we will work on the parts. Another advan-
    tage of Lisp is that it supports (but does not enforce) more of the large-
    scale strategies for modular decomposition of programs than any other
    language we know.    


    ...

    e acts of the mind, wherein it exerts its power over simple
    ideas, are chiefly these three: 1. Combining several simple
    ideas into one compound one, and thus all complex ideas
    are made. 2. e second is bringing two ideas, whether sim-
    ple or complex, together, and seing them by one another
    so as to take a view of them at once, without uniting them
    into one, by which it gets all its ideas of relations. 3. e
    third is separating them from all other ideas that accom-
    pany them in their real existence: this is called abstraction,
    and thus all its general ideas are made.
    
    —John Locke, An Essay Concerning Human Understanding
    (1690)

    We are about ot study the idea of a computational process . Com-
    putational processes are abstract beings that inhabit computers.
    As they evolve, processes manipulate other abstract things called data .
    The evolution of a process is directed by a paern of rules called a pro-
    gram . People create programs to direct processes. In effect, we conjure
    the spirits of the computer with our spells.

    https://en.wiktionary.org/wiki/esoteric

    ** the evolution of a process is directed by a pattern of rules called a program


    p2

    Well-designed com-putational systems, like well-designed automobiles or nuclear reactors,
    are designed in a modular manner, so that the parts can be constructed,
    replaced, and debugged separately.

    p3

    Just as our every-
    day thoughts are usually expressed in our natural language (such as En-
    glish, French, or Japanese), and descriptions of quantitative phenomena
    are expressed with mathematical notations, our procedural thoughts
    will be expressed in Lisp.

    p5

    If Lisp is not a mainstream language, why are we using it as the
    framework for our discussion of programming? Because the language
    possesses unique features that make it an excellent medium for studying
    important programming constructs and data structures and for relating
    them to the linguistic features that support them. e most significant of
    these features is the fact that Lisp descriptions of processes, called proce-
    dures , can themselves be represented and manipulated as Lisp data. e
    importance of this is that there are powerful program-design techniques
    that rely on the ability to blur the traditional distinction between “pas-
    sive” data and “active” processes. As we shall discover, Lisp’s flexibility
    in handling procedures as data makes it one of the most convenient
    languages in existence for exploring these techniques. e ability to
    represent procedures as data also makes Lisp an excellent language for
    writing programs that must manipulate other programs as data, such as
    the interpreters and compilers that support computer languages. Above
    and beyond these considerations, programming in Lisp is great fun.

    p6 The Elements of Programming

    A powerful programming language is more than just a means for in-
    structing a computer to perform tasks. e language also serves as a
    framework within which we organize our ideas about processes. us,
    when we describe a language, we should pay particular aention to the
    means that the language provides for combining simple ideas to form
    more complex ideas. Every powerful language has three mechanisms
    for accomplishing this:
        • primitive expressions , which represent the simplest entities the
        language is concerned with,
        • means of combination , by which compound elements are built
        from simpler ones, and
        • means of abstraction , by whi


    p8 Expressions

    Expressions representing numbers may be combined with an expres-
    sion representing a primitive procedure (such as + or * ) to form a com-
    pound expression that represents the application of the procedure to
    those numbers. For example:
    (+ 137 349)
    486
    (- 1000 334)
    666
    (* 5 99)
    495
    (/ 10 5)
    2
    (+ 2.7 10)
    12.7
    Expressions such as these, formed by delimiting a list of expressions
    within parentheses in order to denote procedure application, are called
    combinations . e lemost element in the list is called the operator , and
    the other elements are called operands . e value of a combination is
    obtained by applying the procedure specified by the operator to the ar-
    guments that are the values of the operands.

    e convention of placing the operator to the le of the operands
    is known as prefix notation 

    p11

    define is our language’s simplest means of abstraction, for it allows
    us to use simple names to refer to the results of compound operations,
    such as the circumference computed above.

    ...

    It should be clear that the possibility of associating values with sym-
    bols and later retrieving them means that the interpreter must maintain
    some sort of memory that keeps track of the name-object pairs. is
    memory is called the environment (more precisely the global environ-
    ment , since we will see later that a computation may involve a number
    of different environments)

    p12 Evaluating Combinations

    One of our goals in this chapter is to isolate issues about thinking pro-
    cedurally. As a case in point, let us consider that, in evaluating combi-
    nations, the interpreter is itself following a procedure.
    To evaluate a combination, do the following:
    1. Evaluate the subexpressions of the combination.
    2. Apply the procedure that is the value of the lemost subexpres-
    sion (the operator) to the arguments that are the values of the
    other subexpressions (the operands).
    Even this simple rule illustrates some important points about processes
    in general. First, observe that the first step dictates that in order to ac-
    complish the evaluation process for a combination we must first per-
    form the evaluation process on each element of the combination. us,
    the evaluation rule is recursive in nature; that is, it includes, as one of
    its steps, the need to invoke the rule itself.

    https://www.dictionary.com/browse/succinctly

    p13

    Viewing evaluation in terms of the tree, we can
    imagine that the values of the operands percolate upward, starting from
    the terminal nodes and then combining at higher and higher levels. In
    general, we shall see that recursion is a very powerful technique for
    dealing with hierarchical, treelike objects. In fact, the “percolate values
    upward” form of the evaluation rule is an example of a general kind of
    process known as tree accumulation .

    p14

    (at is, (define x 3) is not a combination.)
    Such exceptions to the general evaluation rule are called special forms .

    p15 Compound procedures

    We have identified in Lisp some of the elements that must appear in any
    powerful programming language:
        • Numbers and arithmetic operations are primitive data and proce-
        dures.
        • Nesting of combinations provides a means of combining opera-
        tions.
        • Definitions that associate names with values provide a limited
        means of abstraction.
    
    ...

    Special syntactic forms that are simply convenient alternative surface structures
    for things that can be wrien in more uniform ways are sometimes called syntactic
    sugar , to use a phrase coined by Peter Landin. In comparison with users of other lan-
    guages, Lisp programmers, as a rule, are less concerned with maers of syntax. (By
    contrast, examine any Pascal manual and notice how much of it is devoted to descrip-
    tions of syntax.) is disdain for syntax is due partly to the flexibility of Lisp, which
    makes it easy to change surface syntax, and partly to the observation that many “con-
    venient” syntactic constructs, which make the language less uniform, end up causing
    more trouble than they are worth when programs become large and complex. In the
    words of Alan Perlis, “Syntactic sugar causes cancer of the semicolon.”

    p19

    The process we have just described is called the substitution model for
    procedure application. It can be taken as a model that determines the
    “meaning” of procedure application, insofar as the procedures in this
    chapter are concerned.

    p20 Applicative order versus normal order

    is alternative “fully expand and then reduce” evaluation method
    is known as normal-order evaluation , in contrast to the “evaluate the
    arguments and then apply” method that the interpreter actually uses,
    which is called applicative-order evaluation .

    p22 Conditional Expressions and Predicates

    is construct is called a case analysis , and there is a special form in
    Lisp for notating such a case analysis. It is called cond (which stands for
    “conditional”)

    p24

    is uses the special form if , a restricted type of conditional that can
    be used when there are precisely two cases in the case analysis. e
    general form of an if expression is
    (if
    ⟨ predicate ⟩ ⟨ consequent ⟩ ⟨ alternative ⟩ )

    p25

    Notice that and and or are special forms, not procedures, because the
    subexpressions are not necessarily all evaluated. not is an ordinary pro-
    cedure.

    p28

    e contrast between function and procedure is a reflection of the
    general distinction between describing properties of things and describ-
    ing how to do things, or, as it is sometimes referred to, the distinction
    between declarative knowledge and imperative knowledge. In mathe-
    matics we are usually concerned with declarative (what is) descriptions,
    whereas in computer science we are usually concerned with imperative
    (how to) descriptions.
        

    p33 Procedures as Black-Box Abstractions

    e importance of this decomposition strategy is not simply that
    one is dividing the program into parts. Aer all, we could take any large
    program and divide it into parts—the first ten lines, the next ten lines,
    the next ten lines, and so on. Rather, it is crucial that each procedure ac-
    complishes an identifiable task that can be used as a module in defining
    other procedures. For example, when we define the good-enough? pro-
    cedure in terms of square , we are able to regard the square procedure
    as a “black box.” We are not at that moment concerned with how the
    procedure computes its result, only with the fact that it computes the
    square. e details of how the square is computed can be suppressed,
    to be considered at a later time.

    p36

    A formal parameter of a procedure has a very special role in the
    procedure definition, in that it doesn’t maer what name the formal
    parameter has. Such a name is called a bound variable , and we say that
    the procedure definition binds its formal parameters.

    If a variable is not bound, we say
    that it is free . e set of expressions for which a binding defines a name
    is called the scope of that name.

    p39
    
    Lexical coping dictates that free variables in a procedure are taken to refer to
    bindings made by enclosing procedure definitions; that is, they are looked up in the
    environment in which the procedure was defined.

    p40 Procedures and the Processes They Generate

    e ability to visualize the consequences of the actions under con-
    sideration is crucial to becoming an expert programmer, just as it is in
    any synthetic, creative activity.

    ...

    To become experts, we must learn to visualize the
    processes generated by various types of procedures. Only aer we have
    developed such a skill can we learn to reliably construct programs that
    exhibit the desired behavior.

    ...

    A procedure is a paern for the local evolution of a computational
    process.

    p44

    Linear recursive process

    e expansion occurs as the process builds up a chain of deferred oper-
    ations (in this case, a chain of multiplications). e contraction occurs
    as the operations are actually performed. is type of process, charac-
    terized by a chain of deferred operations, is called a recursive process .
    Carrying out this process requires that the interpreter keep track of the
    operations to be performed later on.

    Linear iterative process

    In the iterative case, the program variables provide a complete descrip-
    tion of the state of the process at any point.
    ...
    In general, an iterative process is one whose state can be sum-
    marized by a fixed number of state variables , together with a fixed rule
    that describes how the state variables should be updated as the process
    moves from state to state and an (optional) end test that specifies con-
    ditions under which the process should terminate.In computing n!, the
    number of steps required grows linearly with n. Such a process is called
    a linear iterative process .

    ...

    In contrasting iteration and recursion, we must be careful not to
    confuse the notion of a recursive process with the notion of a recursive
    procedure . When we describe a procedure as recursive, we are referring
    to the syntactic fact that the procedure definition refers (either directly
    or indirectly) to the procedure itself. But when we describe a process
    as following a paern that is, say, linearly recursive, we are speaking
    about how the process evolves, not about the syntax of how a procedure
    is wrien.

    ...
    It may seem disturbing that we refer to a recursive procedure
    such as fact-iter as generating an iterative process. However, the pro-
    cess really is iterative: Its state is captured completely by its three state
    variables, and an interpreter need keep track of only three variables in
    order to execute the process.

    One reason that the distinction between process and procedure may
    be confusing is that most implementations of common languages (in-
    cluding Ada, Pascal, and C) are designed in such a way that the interpre-
    tation of any recursive procedure consumes an amount of memory that
    grows with the number of procedure calls, even when the process de-
    scribed is, in principle, iterative. As a consequence, these languages can
    describe iterative processes only by resorting to special-purpose “loop-
    ing constructs” such as do , repeat , until , for , and while . e imple-
    mentation of Scheme we shall consider in Chapter 5 does not share this
    defect. It will execute an iterative process in constant space, even if the
    iterative process is described by a recursive procedure. An implemen-
    tation with this property is called tail-recursive .
        
    ...
    When we discuss the implementation of procedures on register machines in Chap-
    ter 5, we will see that any iterative process can be realized “in hardware” as a machine
    that has a fixed set of registers and no auxiliary memory. In contrast, realizing a re-
    cursive process requires a machine that uses an auxiliary data structure known as a
    stack .

    p46

    Tail
    recursion has long been known as a compiler optimization trick. A coherent
    semantic basis for tail recursion was provided by Carl Hewi (1977), who explained it in
    terms of the “message-passing” model of computation that we shall discuss in Chapter
    3. Inspired by this, Gerald Jay Sussman and Guy Lewis Steele Jr. (see Steele and Sussman
    1975) constructed a tail-recursive interpreter for Scheme. Steele later showed how tail
    recursion is a consequence of the natural way to compile procedure calls (Steele 1977).
    e  standard for Scheme requires that Scheme implementations be tail-recursive.

    