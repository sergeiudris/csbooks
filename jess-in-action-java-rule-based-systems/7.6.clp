
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


