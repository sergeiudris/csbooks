(ns chapter4.core
  (:require [clojure.repl :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as str]))

(comment
 
 (doc is)

 (is (= 4 (+ 2 2)))
 
 (is (= 4 (+ 2 2 3)))
 
 (is (= 3 (+ 1 1)) "Only for large values of 1")
 
 (with-test 
   (defn my-add [x y]
     (+ x y)
     )
   (is (= 4 (my-add 2 2)))
   (is (= 7 (my-add 3 4)))
   
   )
 
 (run-tests)
 
 (defn my-add [x y]
   (+ x y)
   )
 
 (deftest addition
   (testing
    (is (= 4 (my-add 2 2)))
     (is (= 7 (my-add 3 4))))
   )
 
 (deftest addition2
   (testing "using let to bind x to 2"
     (let [x 2]
       (is (= 4 (my-add x 2)))
       (is (= 7 (my-add x 5)))
       (testing "and y to 3"
         (let [y 3]
           (is (= 5 (my-add x y)))))))
   (testing "adding negative numbers"
     (is (= -3 (my-add -10 7)))
     (is (= 5 (my-add 10 -5)))))
 

 (deftest addition3
   (testing
    (are [expected actual] (= expected actual)
      7 (my-add 2 5)
      4 (my-add 2 2)))
   (testing "adding negative numbers"
     (are [expected actual] (= expected actual)
       5 (my-add 10 -5)
       -3 (my-add -10 7))))
 
 (run-tests)
 
 
 ;; fixtures
 
 (defn my-add [x y]
   (+ x y))
 
 (defn my-sub [x y]
   (- x y))
 
 (deftest addition
   (is (= 4 (my-add 2 2))))
 
 (deftest subtraction
   (is (= 3 (my-sub 7 4))))
 
 (defn once-fixture [f]
   (println "setup once")
   (f)
   (println "teardown once"))
 
 (defn each-fixture [f]
   (println "setup each")
   (f)
   (println "teardown each"))
 
 (use-fixtures :each each-fixture)
 
 (use-fixtures :once once-fixture)
 
 (run-tests)
 
 
 )