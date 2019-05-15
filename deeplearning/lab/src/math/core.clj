(ns math.core
  (:require [clojure.repl :refer :all]))

(defn argmin
  "returns the argmin of  ..."
  []
  0)

(defn fact
  "returns factorial of x"
  [x]
  (reduce * 1 (range 1 (inc x))))

(comment
  
  (fact 20)
  
  
  ;;;
  )