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
  
  
  
  
  
  
  
  
  
  )