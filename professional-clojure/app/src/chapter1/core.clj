(ns chapter1.core
  (:require [clojure.repl :refer :all]))

  (defn hi [] "hi")


(comment
  
  (hi)
  
  ;; ch1 p3
  ; substitution model for procedure applications
  
  (defn square [a] (* a a) )
  (defn sum-of-squares [a b] (+ (square a) (square b) ))
  
  (sum-of-squares 4 5)
  (+ (square 4) (square 5) )
  (+ (* 4 4) (* 5 5) )
  (+ 16 25)
  41
  
  
  ;; fib and memoization
  
  (defn fib [n]
    (cond 
      (= n 0) 0
      (= n 1) 1
      :else (+ (fib (- n 1))
               (fib (- n 2)))
      )
    )
  
  (time (fib 5))
  
  (time (fib 42))
  
  (def memoized-fib
    (memoize (fn [n]
               (cond
                 (= n 0) 0
                 (= n 1) 0
                 :else (+ (fib (- n 1))
                          (fib (- n 2))
                          )
                 )
               
               ))
    )
  
  (time (memoized-fib 38))
  
  ;; thinking recursively
  
  (defn factorial [n]
    (if (= n 1)
      1
      (* n (factorial (- n 1)))
      )
    )
  
  (factorial 6)
  
  (factorial 6)
  (* 6 (factorial 5))
  (* 6 (* 5 (factorial 4)))
  (* 6 (* 5 (* 4 (factorial 3))))
  (* 6 (* 5 (* 4 (* 3 (factorial 2)))))
  (* 6 (* 5 (* 4 (* 3 (* 2 (factorial 1))))))
  (* 6 (* 5 (* 4 (* 3 (* 2 1)))))
  (* 6 (* 5 (* 4 (* 3 2))))
  (* 6 (* 5 (* 4 6)))
  (* 6 (* 5 24))
  (* 6 120)
  720
  
  (defn factorial2 [n]
    (loop [count n acc 1]
      (if (zero? count)
        acc
        (recur (dec count) (* acc count) )
        )
      )
    )
  
  (factorial2 6)

  (factorial2 6)
  (loop 6 1)
  (loop 5 6)
  (loop 4 30)
  (loop 3 120)
  (loop 2 360)
  (loop 1 720)
  720

    
  
  
  )