(ns plants.mx.core-test
  (:require [clojure.test :refer :all]
            [clojure.repl :refer :all]
            [plants.mx.core :refer :all]
   ;
            ))


(deftest vec-s-test
  (is (= (vec-s [:a :b :c] #{1 3 7}) [:b]))
  (is (= (vec-s [:a :b :c] #{3 7}) [])))

(deftest vec-s--test
  (is (= (vec-s- [:a :b :c] #{1 3 7}) [:a :c]))
  (is (= (vec-s- [:a :b :c] #{3 7}) [:a :b :c])))


(deftest mx-mn-test
  (is (= (mx-mn 3 1 (fn [i x] 2) ) [2 2 2]))
  (is (= (mx-mn 2 3 (fn [i x] 2) ) [2 2 2 2 2 2]))
  )

(deftest mx->mn-test
  (is (= (mx->mn [1 2 3 4 5 6 7 8 9] 3 2 1) 8))
  (is (= (mx->mn [1 2 3 4 5 6 7 8 9] 3 0 1) 2))
  (is (= (mx->mn [1 2 3 4 5 6 7 8 9] 3 1 0) 4)))

(deftest multiply-vec-test
  (is (= (multiply-vec [1 2 3 4 5 6] [0 1 2] ) [8 17]))
  (is (= (vec-by-vec [1 2 3] [0 1 2] ) 8 ))
  )

(deftest multiply-test
  (is (= (multiply [1 2 3 4 5 6]  [0 1 2 3 4 5] 3 2) [16 22 34 49]))
  (is (= (multiply [1 2 3 4 5 6 7 8 9 10 11 12]  [-2 1 0] 3 1) [0 -3 -6 -9]))
  (is (= (multiply [1 2 3 4 5 6]  [1 2 3 4 5 6] 3 2) [22 28 49 64]))
  (is (= (multiply [1 2 3 4 5 6]  [1 2 3 4 5 6] 2 3) [9 12 15 19 26 33 29 40 51])))

(deftest add-test
  (is (= (add [1 2 3 4 5 6] [1 2 3 4 5 6]) [2 4 6 8 10 12]))
  (is (= (subtract [1 2 3 4 5 6] [2 4 6 8 10 12]) [-1 -2 -3 -4 -5 -6])))
 
(deftest transpose-test
  (is (= (transpose [1 2 3 4 5 6] 3) [1 4 2 5 3 6]))
  (is (= (transpose [1] 1) [1])))

(deftest broadcast-test
  (is (= (broadcast [1 2 3 4 5 6] 3 [1 2 3]) [2 4 6 5 7 9])))

(deftest add-scalar-test
  (is (= (add-scalar [1 2 3 4 5 6] -1) [0 1 2 3 4 5]))
  )

(deftest multiply-scalar-test
  (is (= (multiply-scalar [1 2 3 4 5 6] 2) [2 4 6 8 10 12])))


(deftest dot-product-test
  (is (= (dot-product [1 3 -5] [4 -2 -1]) [3])))

(deftest element-wise-product-test
  (is (= (element-wise-product [1 2 3 4] [1 2 3 4]) [1 4 9 16])))

(deftest vec-unit-test
  (is (=  (->>
           [1 3 0]
           vec-length
           first
           (divide-scalar [1 3 0])
           vec-length) [ 0.9999999999999999] )))

(deftest matrix-properties-test
  (let [A [1 2 3 4 5 6 7 8 9 10 11 12]
        B [1 2 3 4 5 6]
        C [2 3 7 8 1 0]]
    (testing "mx multiplication is distributive"
      (is (= (multiply A (add B C) 3 2) (add (multiply A B 3 2) (multiply A C 3 2)))))
    (testing "mx multiplication is associative.
      https://en.wikipedia.org/wiki/Matrix_multiplication#Associativity
      the products (AB) C and A (BC) are defined if and only if 
      the number of columns of A equals the number of rows of B 
      and the number of columns of B equals the number of rows of C
      "
      (is (=  (multiply (multiply A B 3 2) C 2 3) (multiply A (multiply B C 2 3) 3 3))))
    (testing "transpose of a product AB is reverse product (BA) of transposes   "
      (is (= (transpose (multiply A B 3 2) 2) (multiply (transpose B 2) (transpose A 3) 3 4))))
    ;;;
    ))

(deftest vector-properties
  (let [a [1 2 3]
        b [4 5 6]]
    (testing "dot product is commutative"
      (is (= (dot-product a b) (dot-product b a))))

    (testing "However, the dot product between two
vectors is commutative.
      the value
of such a product is a scalar and therefore equal to its own transpose
      "
      (is (= (dot-product a b) (transpose (dot-product a b) 1))))
    ;;;
    ))


(deftest iden-test
  (is (= (iden 3) [1 0 0 0 1 0 0 0 1])))


(deftest sort-steps-v2-test
  (is (= (sort-steps-v2 [2 1 5 4 0] nil 0) '[(0 1 2  4 5) 6]))
  (is (= (sort-steps-v2 [1 1 0 0] nil 0) '[(0 0 1 1) 4]))
  (is (= (sort-steps-v2 [0 9 9 0 3] nil 0) '[(0 0 3 9 9) 4]))
  (is (= (sort-steps-v2 [9 7 3 2 0 1] nil 0) '[(0 1 2 3 7 9 ) 14]))
  
  
  
  ;;;
  )

(deftest sgn-test
  (is (= (sgn [1 2 3]) 1))
  (is (= (sgn [2 1 3]) -1))
  (is (= (sgn [1 3 2]) -1))
  (is (= (sgn [2 3 1]) 1))
  (is (= (sgn [3 1 2]) 1))
  (is (= (sgn [3 2 1]) -1))
  ;;;
  )


(deftest permutations-test
  (is (= (permutations 2 1 [[1]]) [[3 2 1] [2 3 1] [2 1 3] [3 1 2] [1 3 2] [1 2 3]])))


(comment

  (run-tests)
  (remove-ns 'plants.mx.core-test)


  (def A (mkmx [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

  (def B (mkmx
          [[1 2 3]
           [4 5 6]]))

  (= (transpose (multiply A B 3 2) 2) (multiply (transpose B 2) (transpose A 3) 3 4))

  (prnmx   (transpose A 3) 4)

  (def C (mkmx
          [[0 1 2]
           [3 4 5]]))

  (def M1 (multiply A (multiply B C 2 3) 3 3))

  (def M2 (multiply  (multiply A B 3 2) C 2 3))

  (= M1 M2)

  (transpose (dot-product [1 2 3] [0 22 3]) 1)



  ;;;
  )