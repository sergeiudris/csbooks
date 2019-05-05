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


(deftest filter-vec-by-index-set-test
  (is (= (plants.mx.core/filter-vec-by-index-set [:a :b :c] #{1 3 7}) [:b]))
  (is (= (plants.mx.core/filter-vec-by-index-set [:a :b :c] #{ 3 7}) []))
  )


(comment
  
  (run-tests)
  
  ;;;
  )