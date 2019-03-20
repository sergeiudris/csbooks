
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

; (ppdefrule "rule-name&6")



(deftemplate auto (slot price) (slot color))

; (defrule no-red-cars
; (auto (color ~red))
; =>)

(defrule no-red-cars-2
(not (auto (color red)))
=>)

(defrule no-odd-numbers
(not (number ?n&:(oddp ?n)))
=>
(printout t "There are no odd numbers." crlf))


; (defrule forall-example
; (not (and (car (color ?c)) (not (bus (color ?c)))))
; =>)

(defrule exists-an-honest-man
(exists (honest ?))
=>
(printout t "There is at least one honest man!" crlf))

; (defrule find-trustworthy-people-1
; (person (age ?x))
; (test (< ?x 30))
; =>
; (printout t ?x " is under 30!" crlf))

; (defrule find-trustworthy-people-2
; (person (age ?x&:(< ?x 30)))
; =>
; (printout t ?x " is under 30!" crlf))

(import java.util.Date)
(defrule fire-next-century
(test ((new Date) after (new Date "Dec 31 2099")))
=>
(printout t "Welcome to the 22nd century!" crlf))




(defrule turn-water-on
(faucet open)
=>
(assert (water flowing)))

(defrule turn-water-off
(not (faucet open))
?water <- (water flowing)
=>
(retract ?water))


(clear)

(defrule water-flows-while-faucet-is-open
(logical (faucet open))
=>
(assert (water flowing)))

(assert (faucet open))

(run)
(facts)
(watch facts)
(retract 0)
(facts)