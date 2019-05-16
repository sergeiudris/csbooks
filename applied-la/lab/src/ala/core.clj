(ns ala.core
  (:require [clojure.repl :refer :all]
            [ala.mx :as mx]
            [ala.print :refer [cprn]]))

; applied linear algebra
; https://www.seas.ucla.edu/~vandenbe/ee133a.html
; Introduction to Applied Linear Algebra  Stephen Boyd  Lieven Vandenberghe

; page 449 pdf - notaion 

(def e1 [1 0 0])

(def e2 [0 1 0])

(def e3 [0 0 1])


(defn nnz
  "Returns the count of non-zero els of a"
  [a]
  (count (filterv #(not (= 0 %)) a)))

(defn make-vec
  "Returns a vec given size, el"
  [size el]
  (vec (repeat size el)))

(defn sum
  "Returns the sum of numbers.
   Sum is a linear combintaion of scalars"
  [a]
  (reduce + 0 a))

(defn fact
  "Returns factorial of x"
  [x]
  (reduce * 1 (range 1 (inc x))))





(comment

  (nnz [1 2 0 0 5 6])
  (make-vec 3 0)
  (mx/iden-mx 3)
  
  ;;;
  )


(comment

  (mx/elwise-sum [0 7 3] [1 2 0])

  (mx/scalar-add 2 [1 2 3])

  (mx/scalar-prod -2 [1 9 6])

  (mx/vec-linear-comb [[1 2 3] [0 0 1]] [1 2])

  
  ;;;
  )