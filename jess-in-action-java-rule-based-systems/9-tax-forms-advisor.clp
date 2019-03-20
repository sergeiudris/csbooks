
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


(reset)
(run)