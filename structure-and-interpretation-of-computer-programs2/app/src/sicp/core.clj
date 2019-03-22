(ns sicp.core
  (:require [clojure.repl :refer :all] )
  )

(defn hi [] "")


(comment

  ;;;; Expressions
  
  486
  
  (+ 137 349)
  
  (- 1000 334)
  
  (* 5 99)
  
  (/ 10 5)
  
  (+ 2.7 10)
  
  (+ 21 35 12 7)
  
  (* 25 4 12)
  
  (+ (* 3 5) (- 10 6)) ; nested
  
  #_(
     asd
     asdasdd asdasdd
     asdasdd asdasdd dasd asd
     
     )

  (+ (* 3 (+ (* 2 4) (+ 3 5))) (+ (- 10 7) 6))
  
  (+ (* 3
        (+ (* 2 4)
           (+ 3 5)))
     (+ (- 10 7)
        6))
  
  ;;;; Naming and the Environment
  
  (def size 2)

  size
  
  (* 5 size)
  
  
  (def pi 3.14159)
  (def radius 10)
  (* pi (* radius radius))
  
  (def circumference (* 2 pi radius))
  circumference
  
  
  ;;;; Evaluating Combinations
  
  (* (+ 2 (* 4 6))
     (+ 3 5 7))

  ;;;; Compound Procedures
  
  (def square (fn [x] (* x x)) )
  
  ; (def <name> <formal parameters> <body> )
  
  (square 21)
  
  (square (+ 2 5))
  
  (square (square 3))
  
  (defn sum-of-squares [x y]
    (+ (square x) (square y)))
  
  (sum-of-squares 3 4)
  
  (defn f [a] (sum-of-squares (+ a 1) (* a 2) ) )
  
  (f 5)
  
  
  
  
  
  
  
  )