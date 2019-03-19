(printout t "Hello, World!" crlf)


(printout t "---" crlf)
; watching
(watch facts)
(reset)
(unwatch facts)

(printout t "---" crlf)
(assert (groceries milk eggs bread))
(facts)

(printout t "---" crlf)
(retract 1)
(facts)
(bind ?f (fact-id 0))
(retract ?f)
(facts)

(printout t "---" crlf)
(deffacts catalog "Product catalog"
(product 354 sticky-notes "$1.99")
(product 355 paper-clips "$0.99")
(product 356 blue-pens "$2.99")
(product 357 index-cards "$0.99")
(product 358 stapler "$5.99"))
(facts)
(reset)
(facts)




(printout t "---" crlf)

; unordered fact
; (person (name "John Q. Public") (age 34) (height 5 10) (weight 170))

; ordered fact
; (person "John Q. Public" 34 5 10 170)


(deftemplate person "People in actuarial database"
(slot name)
(slot age)
(slot gender))

(assert (person (age 34) (name "Bob Smith")
(gender Male)))

; default slot values - nil
(assert (person (age 30) (gender Female)))

; slot qualifier
(deftemplate person2 "People in actuarial database"
(slot name (default "OCCUPANT"))
(slot age)
(slot gender))

(assert (person2 (age 30) (gender Female)))

(facts)


(printout t "---" crlf)
(ppdeftemplate number)
(show-deftemplates)

(printout t "---" crlf)

(deftemplate person3 "People in actuarial database"
(slot name (default OCCUPANT))
(slot age)
(slot gender)
(multislot hobbies))

(assert (person3 (name "Jane Doe") (age 22)
(hobbies snowboarding "restoring antiques" 3)
(gender Female)))


(facts)

(printout t "---" crlf)

(modify 9 (age 2123123))

(duplicate 9 (name "John Doe") (gender Male))

(facts)


