(ns sicp.exercises-1
  (:require [clojure.repl :refer :all]
            [sicp.ch1-building-abstractions-with-procedures :refer [square average improve sqrt good-enough?  abs]]
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
    (sqrt-iter 1.0 x) 
    )

; (sqroot 2) ; will fail, as new-if will evaluate second argument, will be StackOverflow


(sqrt 2)
(Math/sqrt 2)

(sqrt 0.1)
(Math/sqrt 0.1)


(sqrt 0.001)
(Math/sqrt 0.001)


; (sqroot2 10000000000000) stackoverflow
(Math/sqrt 10000000000000)


(defn ok-enough?
  [guess  old-guess]
  ; (prn guess " , " old-guess)
  (< (abs (- (/ guess old-guess) 1)) 0.001))


(defn  sqrt-iter2
  [guess old-guess  x]
  (if (ok-enough? guess old-guess)
    guess
    (sqrt-iter2 (improve guess x) guess x)
    ;  (recur (improve guess x) guess x)
    ))


  (defn sqroot2
    [x]
    (sqrt-iter2 1.0 0.1 x))

(ok-enough? 1.0 0.9 )

(Math/sqrt 2) ; 1.4142135623730951
(sqrt 2)      ; 1.4142156862745097
(sqroot2 2)   ; 1.4142135623746899

(Math/sqrt 0.1) ; 0.31622776601683794
(sqrt 0.1)      ; 0.316245562280389
(sqroot2 0.1)   ; 0.31622776651756745

(Math/sqrt 0.001) ; 0.03162277660168379
(sqrt 0.001)      ; 0.04124542607499115
(sqroot2 0.001)   ; 0.03162278245070105

; (sqrt   10000000000000) ; stackoverflow
(sqroot2   10000000000000) 
(Math/sqrt 10000000000000)

  
  
  
  
  
  