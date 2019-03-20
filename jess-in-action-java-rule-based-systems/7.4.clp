
(printout t "---Bsckward chainings" crlf)
(clear)

(reset)
(run)

(printout t "---" crlf)

; (deftemplate factorial (slot base ) (slot val ))

(do-backward-chaining factorial)



(defrule print-factorial-10
(factorial 10 ?r1)
=>
(printout t "The factorial of 10 is " ?r1 crlf))

(defrule do-factorial
(need-factorial ?x ?) ; whenver someone need factorial, need-factorila will fire and compute
=>
;; compute the factorial of ?x in ?r
(bind ?r 1)
(bind ?n ?x)
(while (> ?n 1)
(bind ?r (* ?r ?n))
(bind ?n (- ?n 1)))
(assert (factorial ?x ?r)))

(printout t "--- Multilevel backward chaining" crlf)

(clear)
(do-backward-chaining item-number)
(do-backward-chaining price)

(deffunction fetch-price-from-database (?number)
(printout t "fetching price for number " ?number crlf)
40
)

(deffunction fetch-number-from-database (?name)
(printout t "fetching number for name " ?name crlf)
3
)

(defrule price-check
(do-price-check ?name)
(price ?name ?price)
=>
(printout t "Price of " ?name " is " ?price crlf))

(defrule find-price
(need-price ?name ?) ; when 'price' is required, find-price will fire
(item-number ?name ?number)
=>
(bind ?price (fetch-price-from-database ?number))
(assert (price ?name ?price)))

(defrule find-item-number
(need-item-number ?name ?); when 'item-number' is required,  find-item-number will fire
=>
(bind ?number (fetch-number-from-database ?name))
(assert (item-number ?name ?number)))

(reset)
(assert (do-price-check waffles))
(run)


(defrule defer-exit-until-agenda-empty
(declare (salience -100))
(command exit-when-idle)
=>
(printout t "exiting..." crlf))