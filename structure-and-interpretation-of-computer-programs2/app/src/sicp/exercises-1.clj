(ns sicp.exercises-1
  (:require [clojure.repl :refer :all]
            )
  )


(comment
  
  ;; Exercise 1.3
  
  (def square (fn [x] (* x x)))
  
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
  
  (test-xy 0 (p) )
  
  ; applicative - inifine loop
  ; normal - 0
  
  
  
  
  
  
  
  )