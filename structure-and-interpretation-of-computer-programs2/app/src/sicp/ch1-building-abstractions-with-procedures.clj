(ns sicp.ch1-building-abstractions-with-procedures
    (:require [clojure.repl :refer :all])
  )


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
  
  #_(asd
     asdasdd asdasdd
     asdasdd asdasdd dasd asd)

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
  
  (def square (fn [x] (* x x)))

  ; (def <name> <formal parameters> <body> )
  
  (square 21)

  (square (+ 2 5))

  (square (square 3))

  (defn sum-of-squares [x y]
    (+ (square x) (square y)))

  (sum-of-squares 3 4)

  (defn f [a] (sum-of-squares (+ a 1) (* a 2)))

  (f 5)


  ;;;;  Conditional Expressions and Predicates
  
  (def abs (fn [x]
             (cond
               (> x 0) x
               (= x 0) 0
               (< x 0) (- x))))

  (abs -3)

  (def abs (fn [x]
             (cond
               (< x 0) (- x)
               :else x)))

  (abs -3)


  (def abs (fn [x]
             (if
              (< x 0) ; predicate
               (- x) ; consequent
               x))) ; alternative
  
  (abs -3)

  (def x 7)
  (and (> x 5) (< x 10))

  (def gte (fn [x y]
             (or (> x y) (= x y))))

  (gte 3 4)

  (def gte (fn [x y]
             (not (< x y))))

  (gte 3 4)
  
  )