
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

    

