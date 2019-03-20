
(printout t "---modules" crlf)
(clear)


(defmodule WORK)

(deftemplate WORK::job (slot salary))

(list-deftemplates WORK)

(get-current-module)

(defmodule COMMUTE)
(get-current-module)

(deftemplate bus (slot route-number))
(defrule take-the-bus
?bus <- (bus (route-number 76))
(have-correct-change)
=>
(get-on ?bus))

(ppdefrule take-the-bus)


(reset)
(run)


(clear)

(assert (MAIN::mortgage-payment 2000))
(defmodule WORK)
(deftemplate job (slot salary))
(defmodule HOME)
(deftemplate hobby (slot name) (slot income))
(defrule WORK::quit-job
(job (salary ?s))
(HOME::hobby (income ?i&:(> ?i (/ ?s 2))))
(mortgage-payment ?m&:(< ?m ?i))
=>
(call-boss)
(quit-job))

(ppdefrule WORK::quit-job)


(clear)

(defmodule DRIVING)

(defrule get-in-car
=>
(printout t "Ready to go!" crlf))

(reset)

(run); get-in-car wont fire -  it does not have the focus
(focus DRIVING)
(run)





(defmodule PROBLEMS)

(defrule crash
(declare (auto-focus TRUE))
(DRIVING::me ?location)
(DRIVING::other-car ?location)
=>
(printout t "Crash!" crlf)
(halt))

(defrule DRIVING::travel
?me <- (me ?location)
=>
(printout t ".")
(retract ?me)
(assert (me (+ ?location 1))))

(assert (me 1))
(assert (other-car 4))

(focus DRIVING)
(run) ; fires 'crash' rule becouse of auto-focus


(deftemplate gift (slot name) (slot price))

(defquery find-affordable-gifts
"Finds all gifts in a given price range"
(declare (variables ?lower ?upper))
(gift (price ?p&:(and (> ?p ?lower) (< ?p ?upper)))))

(deffacts catalog
(gift (name red-scarf) (price 20))
(gift (name leather-gloves) (price 35))
(gift (name angora-sweater) (price 250))
(gift (name mohair-sweater) (price 99))
(gift (name keychain) (price 5))
(gift (name socks) (price 6))
(gift (name leather-briefcase) (price 300)))


(reset)

(bind ?it (run-query find-affordable-gifts 20 100))

(while (?it hasNext)
(bind ?token (call ?it next))
(bind ?fact (call ?token fact 1))
(bind ?name (fact-slot-value ?fact name))
(printout t ?name crlf))

(defquery find-factorial
(declare (max-background-rules 5)
(variables ?arg))
(factorial ?arg ?))