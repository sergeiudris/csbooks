(ns plants.mx.num-test
  (:require [clojure.test :refer :all]
            [plants.mx.num :refer :all])
  )

(deftest softmax-test
  (is (== (reduce + (softmax [1 2 3 4 5]))  1)))


(comment

  (run-tests)

  ;;;
  )