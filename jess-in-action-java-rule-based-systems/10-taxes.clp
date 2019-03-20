(clear)
(reset)


; (defrule use-ez-form
; ; If filing status is "single", and...
; (filing-status single)
; ; user made less than $50000
; (income ?i&:(< ?i 50000))
; =>
; ; recommend the user file Form 1040EZ
; (recommend 1040EZ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module MAIN

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

(deffacts question-data
  "The questions the system can ask."
  (question (ident income) (type number)
            (text "What was your annual income?"))
  (question (ident interest) (type yes-no)
            (text "Did you earn more than $400 of taxable interest?"))
  (question (ident dependents) (type number)
            (text "How many dependents live with you?"))
  (question (ident childcare) (type yes-no)
            (text "Did you have dependent care expenses?"))
  (question (ident moving) (type yes-no)
            (text "Did you move for job-related reasons?"))
  (question (ident employee) (type yes-no)
            (text "Did you have unreimbursed employee expenses?"))
  (question (ident reimbursed) (type yes-no)
            (text "Did you have reimbursed employee expenses, too?"))
  (question (ident casualty) (type yes-no)
            (text "Did you have losses from a theft or an accident?"))
  (question (ident on-time) (type yes-no)
            (text "Will you be able to file on time?"))
  (question (ident charity) (type yes-no)
            (text "Did you give more than $500 in property to charity?"))
  (question (ident home-office) (type yes-no)
            (text "Did you work in a home office?")))


(defglobal ?*crlf* = "
")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module ask
(defmodule ask)

; (deffunction ask-user (?question)
; "Ask a question, and return the answer"
; (printout t ?question " ")
; (return (read)))

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

(deffunction is-of-type (?answer ?type)
"Check that the answer has the right form"
(if (eq ?type yes-no) then
(return (or (eq ?answer yes) (eq ?answer no)))
else (if (eq ?type number) then
(return (numberp ?answer)))
else (return (> (str-length ?answer) 0))))


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




; (reset)

; (assert (ask q2))

; (watch all)

; (run)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module startup

(defmodule startup)

(defrule print-banner
=>
(printout t "Type your name and press Enter> ")
(bind ?name (read))
(printout t crlf "*****************************" crlf)
(printout t "Hello, " ?name "." crlf)
(printout t "Welcome to the tax forms advisor" crlf)
(printout t "Please answer the questions and" crlf)
(printout t "I will tell you what tax forms" crlf)
(printout t "you may need to file." crlf)
(printout t "*****************************" crlf crlf))

; (reset)

; (focus startup)

; (watch all)

; (run)

; (assert (ask q2))

; (run)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module interview
(defmodule interview)


(defrule request-income
=>
(assert (ask income)))


(defrule request-num-dependents
=>
(assert (ask dependents)))


(defrule request-interest-income
;; If the total income is less than 50000
(answer (ident income) (text ?i&:(< ?i 50000)))
;; .. and there are no dependents
(answer (ident dependents) (text ?d&:(eq ?d 0)))
=>
(assert (MAIN::ask interest)))

(defrule assert-user-fact
(answer (ident income) (text ?i))
(answer (ident dependents) (text ?d))
=>
(assert (user (income ?i) (dependents ?d))))

(defrule request-childcare-expenses
;; If the user has dependents
(answer (ident dependents) (text ?t&:(> ?t 0)))
=>
(assert (ask childcare)))
(defrule request-employee-expenses
=>
(assert (ask employee)))
(defrule request-reimbursed-expenses
;; If there were unreimbursed employee expenses...
(answer (ident employee) (text ?t&:(eq ?t yes)))
=>
(assert (ask reimbursed)))
(defrule request-moving
=>
(assert (ask moving)))
(defrule request-casualty
=>
(assert (ask casualty)))
(defrule request-on-time
=>
(assert (ask on-time)))
(defrule request-charity
=>
(assert (ask charity)))
(defrule request-home-office
=>
(assert (ask home-office)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module recommend
(defmodule recommend)


(defrule form-1040EZ
(user (income ?i&:(< ?i 50000))
(dependents ?d&:(eq ?d 0)))
(answer (ident interest) (text no))
=>
(assert (recommendation
(form 1040EZ)
(explanation "Income below threshold, no dependents"))))

(defrule form-1040A-excess-interest
(user (income ?i&:(< ?i 50000)))
(answer (ident interest) (text yes))
=>
(assert (recommendation
(form 1040A)
(explanation "Excess interest income"))))

(defrule form-1040A
(user (income ?i&:(< ?i 50000))
(dependents ?d&:(> ?d 0)))
=>
(assert (recommendation
(form 1040A)
(explanation "Income below threshold, with dependents"))))

(defrule form-1040-income-above-threshold
(user (income ?i&:(>= ?i 50000)))
=>
(assert (recommendation
(form 1040)
(explanation "Income above threshold"))))

(defrule form-2441
(answer (ident childcare) (text yes))
=>
(assert (recommendation
(form 2441)
(explanation "Child care expenses"))))

(defrule form-4684
(answer (ident casualty) (text yes))
=>
(bind ?ex "Losses due to casualty or theft")
(assert
(recommendation (form 4684) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))

(defrule combine-recommendations
?r1 <- (recommendation (form ?f) (explanation ?e1))
?r2 <- (recommendation (form ?f) (explanation ?e2&~?e1))
=>
(retract ?r2)
(modify ?r1 (explanation (str-cat ?e1 "
" ?e2))))

(defrule form-2016EZ
(answer (ident employee) (text yes))
(answer (ident reimbursed) (text no))
=>
(bind ?ex "Unreimbursed employee expenses")
(assert
(recommendation (form 2016EZ) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))
(defrule form-2016
(answer (ident employee) (text yes))
(answer (ident reimbursed) (text yes))
=>
(bind ?ex "Reimbursed employee expenses")
(assert
(recommendation (form 2016) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))
(defrule form-3903
(answer (ident moving) (text yes))
=>
(bind ?ex "Moving expenses")
(assert
(recommendation (form 3903) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))
(defrule form-4868
(answer (ident on-time) (text no))
=>
(assert (recommendation (form 4868)
(explanation "Filing extension"))))
(defrule form-8283
(answer (ident charity) (text yes))
=>
(bind ?ex "Excess charitable contributions")
(assert
(recommendation (form 8283) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))
(defrule form-8829
(answer (ident home-office) (text yes))
=>
(bind ?ex "Home office expenses")
(assert
(recommendation (form 8829) (explanation ?ex))
(recommendation (form 1040) (explanation ?ex))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module report

(defmodule report)
(defrule sort-and-print
?r1 <- (recommendation (form ?f1) (explanation ?e))
(not (recommendation (form ?f2&
:(< (str-compare ?f2 ?f1) 0))))
=>
(printout t "*** Please take a copy of form " ?f1 crlf)
(printout t "Explanation: " ?e crlf crlf)
(retract ?r1))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Test data

(deffunction run-system ()
  (reset)
  (focus startup interview recommend report)
  (run))

(while TRUE
  (run-system))