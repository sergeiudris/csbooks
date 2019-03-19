
(printout t "---Variable onstraints" crlf)
(clear)

(deftemplate client1 "client"
(slot name (default CLIENT))
(slot city)
(slot balance)
(multislot hobbies))

(deffacts multifield-fact
(client1  (name "J") (city "NY") (balance 100 ) (hobbies "retract me"))
(client1  (name "K") (city "NY") (balance 110 ))
(client1  (name "O") (city "CHI" )  (balance 200 ) )
(client1  (name "M") (city "BOS" )(balance 50 ))
(client1  (name "Z") (city "LA" ) (balance 500 )  )
)

(defrule balance1
(client1 (name ?name1) (balance ?x))
(client1 (name ?name2) (balance ?y&:(eq ?y (* ?x 2) ) ))
=>
(printout t t "twice balance1 " ?name2 " 2x "  ?name1 crlf))

(defrule balance2
(client1 (name ?name1) (balance ?x))
(client1 (name ?name2) (balance  =(* ?x 2) ))
=>
(printout t t "twice balance2 " ?name2 " 2x "  ?name1 crlf))


(defrule pattern-binding
?fact <- (client1 (name ?name) (hobbies "retract me"))
=>
(printout t t "retracting " ?name  crlf)
(retract ?fact)
)

(defrule call-fact-methods
?fact <- (initial-fact)
=>
(printout t "Name is " (call ?fact getName) crlf)
(printout t "Id is " (call ?fact getFactId) crlf))

(reset)
(run)



