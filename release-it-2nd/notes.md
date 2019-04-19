# Release It! 2nd

    ... and everyone else who saw that a few things had changed
    since we were building monoliths in 2006.

    p 3

    During the hectic rush of a development project, you can easily make decisions
    that optimize development cost at the expense of operational cost. This makes
    sense only in the context of the team aiming for a fixed budget and delivery
    date. In the context of the organization paying for the software, it’s a bad
    choice.

    p 4

    It’s a terrible irony that these very early
    decisions are also the least informed. The beginning is when your team is
    most ignorant of the eventual structure of the software, yet that’s when some
    of the most irrevocable decisions must be made.

    p 5

    If you’ve ever gritted your teeth while coding something according to the “com-
    pany standards” that would be ten times easier with some other technology,
    then you’ve been the victim of an ivory-tower architect.

    p 24

    A transaction is an abstract unit of work processed by the system.

    Transactions are the reason that the system exists.

    The word system means the complete, interdependent set of hardware,
    applications, and services required to process transactions for users. A system
    might be as small as a single application, or it might be a sprawling, multitier
    network of applications and servers.

    A robust system keeps processing transactions, even when transient
    impulses, persistent stresses, or component failures disrupt normal process-
    ing. This is what most people mean by “stability.”

    p 25

    The major dangers to your system’s longevity are memory leaks and data
    growth.

    p 26

    If all else fails, production becomes your longevity testing environment by
    default. You’ll definitely find the bugs there, but it’s not a recipe for a happy
    lifestyle.
    
    The original trigger and the way the crack spreads to the rest of
    the system, together with the result of the damage, are collectively called a
    failure mode.

    p 28

    Looking at even larger architecture issues, CF could’ve been built using
    request/reply message queues. In that case, the caller would know that a
    reply might never arrive. It would have to deal with that case as part of han-
    dling the protocol itself.

    Fault 
    A condition that creates an incorrect internal state in your software.
    A fault may be due to a latent bug that gets triggered, or it may be due
    to an unchecked condition at a boundary or external interface.

    Error 
    Visibly incorrect behavior. When your trading system suddenly buys
    ten billion dollars of Pokemon futures, that is an error.

    Failure
    An unresponsive system. When a system doesn’t respond, we say it
    has failed. Failure is in the eye of the beholder...a computer may have
    the power on but not respond to any requests.

    p 29

    The exhaustive brute-force approach is clearly impractical for anything but life-critical systems
    or Mars rovers. What if you actually have to deliver in this decade?

    p 33

    I haven’t seen a straight-up “website” project since about 1996. Everything
    is an integration project with some combination of HTML veneer, front-end
    app, API, mobile app, or all of the above.

    p 34

    All these connections are integration points, and every single one of them is
    out to destroy your system.

    p 36

    Between electrons and a TCP connection are many layers of abstraction. Fortunately, we get to
    choose whichever level of abstraction is useful at any given point in time.) These
    packets are the Internet Protocol (IP) part of TCP/IP. Transmission Control
    Protocol (TCP) is an agreement about how to make something that looks like a
    continuous connection out of discrete packets.

    p 37

    -> SYN 
    <- SYN/ACK
    -> ACK

    These three packets have now established the “connection,” and the applications can send
    data back and forth.

    p 40

    A firewall is nothing but a specialized router. It routes packets from one set
    of physical ports to another.

    p 46

    If your system scales horizontally, then you will have load-balanced farms or
    clusters where each server runs the same applications. The multiplicity of
    machines provides you with fault tolerance through redundancy.

    p 59

    So the robots most likely to respect robots.txt are the ones that might actually
    generate traffic (and revenue) for you, while the leeches ignore it completely.

    p 61

    The primary risk to stability is the now-classic
    distributed denial-of-service (DDoS) attack. The attacker causes many com-
    puters, widely distributed across the Net, to start generating load on your
    site.

    p 96

    When the circuit is “open,” calls to the circuit breaker fail immediately,
    without any attempt to execute the real operation. After a suitable amount
    of time, the circuit breaker decides that the operation has a chance of suc-
    ceeding, so it goes into the “half-open” state.

    p 123

    A governor limits the speed of an engine. Even if the source of power could drive it faster, the governor prevents it from running
    at unsafe RPMs.

    p 129

    This calendar, decreed by Pope Gregory XIII, became known
    as the Gregorian calendar rather than the Lilian calendar. (They just use your
    mind and they never give you credit. It’s enough to drive you crazy if you let
    it.)

    p 143

    That means “DNS name to IP address” is a many-to-
    many relationship.

    There’s another many-to-many relationship in the mix as well. A single
    machine may have multiple network interface controllers (NICs.) If you run
    “ifconfig” on a Linux or Mac machine, or “ipconfig” on a Windows machine,
    you’ll probably see several NICs listed. Each NIC can be attached to a different
    network. Each active NIC gets an IP address on its particular network. This
    is called multihoming. Nearly every server in a data center will be multihomed.

    p 155

    In the car business, they say the engine needs fuel, fire, and air to work. Our
    version of that is code, config, and connection.

    Service
    A collection of processes across machines that work together to
    deliver a unit of functionality.

    Instance
    An installation on a single machine (container, virtual, or physical)
    out of a load-balanced array of the same executable.

    Executable
    An artifact that a machine can launch as a process and created
    by a build process.

    Process
    An operating system process running on a machine; the runtime
    image of an executable.

    Installation
    The executable and any attendant directories, configuration
    files, and other resources as they exist on a machine

    Deployment
    The act of creating an installation on a machine.

    