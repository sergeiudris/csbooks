
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


; connective constraints
(printout t "---Literal constraints" crlf)

(deftemplate client1 "client"
(slot name (default CLIENT))
(slot city)
(slot civ)
(multislot hobbies))

(deffacts multifield-fact
(client1  (name "Ori") (city "Dale" ) (civ "gnomes"))
(client1  (name "Legolas") (city "Woodland" )  (civ "elves"))
(client1  (name "Aragorn") (city "Minas" )  (civ "humans"))
(client1  (name "Bilbo") (city "Shire" ) (civ "hobbit"))
(client1  (name "Thorin") (city "Erebor" ) (civ "gnomes"))
)

(defrule client-not-from-erebor
(client1 (name ?name) (city ~"Erebor"))
=>
(printout t "not from erebor " ?name crlf))

(defrule client-gnome-human
(client1 (name ?name) (civ "gnomes" | "humans"))
=>
(printout t "gnome or human " ?name crlf))

(reset)
(run)
