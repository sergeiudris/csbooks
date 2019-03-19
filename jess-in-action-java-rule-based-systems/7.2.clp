
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