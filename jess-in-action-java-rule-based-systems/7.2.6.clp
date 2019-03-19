
(printout t "---Literal constraints" crlf)
(clear)

(deftemplate client1 "client"
(slot name (default CLIENT))
(slot city)
(slot balance)
(multislot hobbies))

(deffacts multifield-fact
(client1  (name "J") (city "NY") (balance 100 ))
(client1  (name "K") (city "NY") (balance 110 ))
(client1  (name "O") (city "CHI" )  (balance 200 ) )
(client1  (name "M") (city "BOS" )(balance 50 ))
(client1  (name "Z") (city "LA" ) (balance 500 ) )
)

(defrule balance1
(client1 (name ?name1) (balance ?x))
(client1 (name ?name2) (balance ?y&:(eq ?y (* ?x 2) ) ))
=>
(printout t t "twice blance " ?name2 " 2x "  ?name1 crlf))




(reset)
(run)
