
(printout t "---Conditional elements" crlf)
(clear)

(defrule ready-to-fly
(and (flaps-up)
(engine-on))
=>)

(reset)
(run)

(printout t "---" crlf)

(clear)

(deftemplate used-car (slot price) (slot mileage))
(deftemplate new-car (slot price) (slot warrantyPeriod))

(defrule might-buy-car
?candidate <- (or (used-car (mileage ?m&:(< ?m 50000)))
(new-car (price ?p&:(< ?p 20000))))
=>
(printout t "candidate: " ?candidate  crlf)
(assert (candidate ?candidate))
)

(assert (new-car (price 18000)))
(assert (used-car (mileage 30000)))

(run)

(defrule prepare-sandwich
(and (or (mustard)
(mayo))
(bread))
=>)

(defrule prepare-sandwich
(or (and (mustard) (bread))
(and (mayo) (bread)))
=>)

; (not (or (x) (y))) is the same as (and (not (x)) (not (y)))
; (not (and (x) (y))) is the same as (or (not (x)) (not (y)))