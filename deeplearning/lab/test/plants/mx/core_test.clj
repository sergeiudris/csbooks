(ns plants.mx.core-test
  (:require [clojure.test :refer :all]
            [clojure.repl :refer :all]
            [plants.mx.core]
   ;
            ))


(deftest iden-test
  (is (= (plants.mx.core/iden) []))
  )


(deftest mul-test
  (is (= (plants.mx.core/mul [] []) [])))


(deftest vec-s
  (is (= (plants.mx.core/vec-s [:a :b :c] #{1 3 7}) [:b]))
  (is (= (plants.mx.core/vec-s [:a :b :c] #{3 7}) [])))

(deftest vec-s-
  (is (= (plants.mx.core/vec-s- [:a :b :c] #{1 3 7}) [:a :c]))
  (is (= (plants.mx.core/vec-s- [:a :b :c] #{3 7}) [:a :b :c])))


(deftest mx-mn
  (is (= (plants.mx.core/mx-mn 3 1 (fn [i x] 2) ) [2 2 2]))
  (is (= (plants.mx.core/mx-mn 2 3 (fn [i x] 2) ) [2 2 2 2 2 2]))
  )

(deftest mx->mn
  (is (= (plants.mx.core/mx->mn [1 2 3 4 5 6 7 8 9] 3 2 1 ) 5 ))
  (is (= (plants.mx.core/mx->mn [1 2 3 4 5 6 7 8 9] 3 0 1) 2))
  (is (= (plants.mx.core/mx->mn [1 2 3 4 5 6 7 8 9] 3 1 0) 1))
  )


 
(comment
  
  (run-tests)
  
  ;;;
  )