(ns ala.math-test
  (:require [clojure.test :refer :all]
            [ala.math :refer :all]))

(deftest rms-test
  (let [a [1 -2 3 2]]
    (testing "root-mean-squared squared equals sum of mean squared and stadndard deviation squared"
      (is (==*
           (with-precision 1 (sq (root-mean-square a)))
           (+ (sq (vec-mean a))
              (sq (vec-standard-deviation a))))))

    (testing "standardized vec has mean 0"
      (is (== (vec-mean (vec-standardized a)) 0)))
    (testing "standardized vec has standard deviation 1"
      (is (== (vec-standard-deviation (vec-standardized a)) 1))))
  ;
  )

(deftest vec-andgle-test
  (is (== (rad->deg (vec-angle [1 0 0] [0 0 1])) 90))
  )

(deftest mx-test
  (testing "transpose of sum is th esum of transposes")
  (let [A (rows->mx [[1 2 3]
                     [2 1 4]
                     [3 4 1]])
        B (rows->mx [[3 1 0]
                     [2 1 4]
                     [-1 9 3]])]
    (is (=   (mx-transpose 3 (elwise-sum A B))
             (elwise-sum (mx-transpose 3 A) (mx-transpose 3 B)))))
  
  )


(comment
  (with-precision 1 (sq (root-mean-square [1 -2 3 2])))
  (run-tests)
  ;;;
  )