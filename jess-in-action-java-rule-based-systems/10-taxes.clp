
(printout t "---Tax Forms Advisor" crlf)
(clear)

(defrule use-ez-form
; If filing status is "single", and...
(filing-status single)
; user made less than $50000
(income ?i&:(< ?i 50000))
=>
; recommend the user file Form 1040EZ
(recommend 1040EZ))

(deftemplate user
(slot income (default 0))
(slot dependents (default 0)))

(deftemplate form 
(slot name) 
(slot description))

(deftemplate question 
(slot text) 
(slot type) 
(slot ident))

(deftemplate answer 
(slot ident) 
(slot text))

(deftemplate recommendation 
(slot form) 
(slot explanation))


; (deffunction ask-user (?question)
; "Ask a question, and return the answer"
; (printout t ?question " ")
; (return (read)))

(deffunction is-of-type (?answer ?type)
"Check that the answer has the right form"
(if (eq ?type yes-no) then
(return (or (eq ?answer yes) (eq ?answer no)))
else (if (eq ?type number) then
(return (numberp ?answer)))
else (return (> (str-length ?answer) 0))))

(deffunction ask-user (?question ?type)
"Ask a question, and return the answer"
(bind ?answer "")
(while (not (is-of-type ?answer ?type)) do
(printout t ?question " ")
(if (eq ?type yes-no) then
(printout t "(yes or no) "))
(bind ?answer (read)))
(return ?answer))


; (ask-user "what is the answer ?" number)
; (ask-user "what is the answer ?" yes-no)


(defmodule ask)

(defrule ask::ask-question-by-id
"Ask a question and assert the answer"
(declare (auto-focus TRUE))
;; If there is a question with ident ?id...
(MAIN::question (ident ?id) (text ?text) (type ?type))
;; ... and there is no answer for it
(not (MAIN::answer (ident ?id)))
;; ... and the trigger fact for this question exists
?ask <- (MAIN::ask ?id)
=>
;; Ask the question
(bind ?answer (ask-user ?text ?type))
;; Assert the answer as a fact
(assert (MAIN::answer (ident ?id) (text ?answer)))
;; Remove the trigger
(retract ?ask)
;; And finally, exit this module
(return))

(deffacts MAIN::test-facts
(question (ident q1) (type string)
(text "What is your name?"))
(question (ident q2) (type number)
(text "What is your estimated annual income?"))
(question (ident q3) (type number)
(text "How many dependents do you have?")))

(reset)

(assert (ask q2))

(watch all)

(run)