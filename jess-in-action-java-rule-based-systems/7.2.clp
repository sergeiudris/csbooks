
(printout t "---Literal constraints" crlf)
(clear)

(defrule literal-values
(letters b c)
=>)

(watch activations)

(assert (letters b d)) ;; no activation
(assert (letters b c)) ; activated

; (defrule literal-values2
; (letters (__data b c))
; =>)

(defrule simple-variables
(a ?x ?y)
=>
(printout t "Saw 'a " ?x " " ?y "'" crlf))


; (deffacts simple-var-facts 
; (a b c)
; (a 1 "asd")
; (b 1 "asd")
; ) 

(assert (a b c)) 
(assert (a 1 "asd"))
(assert (b 1 "asd")) 



(defrule repeated-variables
(a ?x)
(b ?x)
=>
(printout t "?x is " ?x crlf))


(assert (a 2) (b 2) )

(run)

(deffacts repeated-variable-facts
(a 1)
(a 2)
(b 2)
(b 3))

(reset)

(run)