(ns ala.math-test
  (:require [clojure.test :refer :all]
            [ala.math :refer :all]))

(deftest rms-test
  (testing "root-mean-squared squared equals sum of mean squared and stadndard deviation squared"
    (let [a [1 -2 3 2]]
      (is (==* (with-precision 1 (sq (root-mean-square a)) ) (+ (sq (vec-avg a)) (sq (vec-standard-deviation a))))))))


(comment
  (with-precision 1 (sq (root-mean-square [1 -2 3 2])))
  ;;;
  )