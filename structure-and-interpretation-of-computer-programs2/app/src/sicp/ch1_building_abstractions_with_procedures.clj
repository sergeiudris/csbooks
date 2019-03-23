(ns sicp.ch1-building-abstractions-with-procedures
    (:require [clojure.repl :refer :all]
              ; [clojure.math.numeric-tower]
     )
  )


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


  ;;;; Square Roots by Newton's Method
  
  "
      Guess Quotient Average
1 (2/1) = 2 ((2 + 1)/2) = 1.5
1.5 (2/1.5) = 1.3333 ((1.3333 + 1.5)/2) = 1.4167
1.4167 (2/1.4167) = 1.4118 ((1.4167 + 1.4118)/2) = 1.4142
1.4142 ... ...
"

  (defn good-enough? [guess x]
    (< (abs (- (square guess) x)) 0.001))

  (defn average [x y]
    (/ (+ x y)  2))

  (defn improve [guess x]
    (average guess (/ x guess)))


  (defn sqrt-iter [guess x]
    (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))






  (float (sqrt-iter 1 2))

  (sqrt-iter 1.0 2)


  (defn sqrt [x]
    (sqrt-iter 1.0 x))

  (sqrt 2)
  
  (sqrt (+ 100 37))

  (square (sqrt 2) )
  
  (defn square2 [x] (* x x))
  (defn square3 [x] (Math/pow x 2 ))
  (defn double- [x] (+ x x))
  (defn square4 [x] (Math/exp (double- (Math/log x) ) ))
  
  (square3 2 )
  (square2 2)
  (Math/log  10)
  (square4 2)
  (. Math E )
  

  (Math/exp 3)
  (Math/pow (. Math E) 3)
  
  

  

  
  

