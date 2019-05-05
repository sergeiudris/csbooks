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