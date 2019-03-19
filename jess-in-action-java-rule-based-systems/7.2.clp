
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



(printout t "---Literal constraints" crlf)


(deffacts repeated-variable-facts
(a 1)
(a 2)
(b 2)
(b 3))

(reset)
(run)



(printout t "---Literal constraints" crlf)


(deftemplate shopping-cart "cart"
(multislot contents))

(deffacts multifield-fact
(shopping-cart (contents veggies greens beans fruit))
)


(defrule any-shopping-cart
(shopping-cart (contents $?items))
=>
(printout t "The cart contains " ?items crlf))


(defrule cart-containing-greens
(shopping-cart (contents $?before greens $?after))
=>
(printout t "The cart contains greens." crlf))

(defrule cart-containing-blanks1
(shopping-cart (contents veggies $? fruit))
=>
(printout t "using blank ($?) as palceholder" crlf))


(defrule cart-containing-blanks2
(shopping-cart (contents veggies ? ? fruit))
=>
(printout t "using blank (?) as palceholder " crlf))

(reset)
(run)