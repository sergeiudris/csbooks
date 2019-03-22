(ns sicp.exercises-1
  (:require [clojure.repl :refer :all]
            [sicp.ch1-building-abstractions-with-procedures :refer [square good-enough? improve]]
            )
  )


  
  ;; Exercise 1.3
  
  
  (def sum-squares-of-two-max-numbers 
    (fn [x y z]
      (cond
        (and (> x y) (> y z) ) (+ (square x) (square y)) 
        ( and (< x y) (< y z) ) (+ (square y) (square z) )
        :else (+ (square x) (square z))
        )
      
      )
    )
  
  (sum-squares-of-two-max-numbers 1 2 3 )
  
  (sum-squares-of-two-max-numbers 1 1 3)
  
  (sum-squares-of-two-max-numbers -2 1 3)
  
  (sum-squares-of-two-max-numbers 4 1 2)
  
  
  ;; Exercise 1.4
  
  (def a 4)
  (def b -3)
  
  (- b 1)
  
  (- a b)
  
  ;; Exercise 1.5 Ben Bitdiddle
  
  (def p (fn [] (p) ) )
  (def test-xy (fn [x y]
                 (if (= x 0) 0 y )
                 ))
  
  ; (test-xy 0 (p) ) will be infinite loop
  
  ; applicative - inifine loop
  ; normal - 0
  
  ;; Exercise 1.6 
  
  (defn new-if 
    [predicate then-clause else-clause ]
    (cond
      predicate then-clause
      :else else-clause
      )
    )
  
  (new-if (= 2 3) 0 5 )
  (new-if (= 1 1) 0 5)
  
  (defn sqrt-iter
    [guess x]
    (new-if (good-enough? guess x) 
            guess
            (sqrt-iter (improve guess x ) x ) 
            )
    
    )

  (defn sqroot
    [x]
    (sqrt-iter 1.0 2) 
    )

; (sqroot 2) ; will fail, as new-if will evaluate second argument, will be StackOverflow



  
  
  
  
  
  