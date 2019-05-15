(ns math.mx_test
  (:require [clojure.test :refer :all]
            [math.mx :refer :all]))

(deftest make-mx-test
  (is (= (make-mx [[1 2 3] [4 5 6]]) [1 2 3 4 5 6])))


(comment
  
  ;;;
  )