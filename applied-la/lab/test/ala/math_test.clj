(ns ala.math-test
  (:require [clojure.test :refer :all]
            [ala.math :refer :all]))

(deftest rms-test
  (testing "root-mean-squared squared equals sum of mean squared and stadndard deviation squared"
    (let [a [1 -2 3 2]]
      (is (==* (with-precision 1 (sq (root-mean-square a))) (+ (sq (vec-avg a)) (sq (vec-standard-deviation a)))))))
  (testing "standardized vec has mean 0"
    (is (== (vec-avg (vec-standardized a)) 0)))
  (testing "standardized vec has standard deviation 1"
    (is (== (vec-standard-deviation (vec-standardized a)) 1)))
  
  ;
  )

(deftest vec-andgle-test
  (is (== (rad->deg (vec-angle [1 0 0] [0 0 1])) 90))
  )



(comment
  (with-precision 1 (sq (root-mean-square [1 -2 3 2])))
  ;;;
  )