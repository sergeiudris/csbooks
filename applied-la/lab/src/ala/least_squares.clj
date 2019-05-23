(ns ala.least-squares
  (:require [clojure.repl :refer :all]
            [ala.print :refer [cprn]]
            [ala.math :refer :all])
  ;
  )


(defn least-squares-approximate
  "Returns the approximate solution to 
  Ax=b
  "
  [widA A b]
  ; (QR-linear-equation widA A b)
  (linear-equation-pseudo-inverse widA A b))


(defn least-squares-X
  "Returns the solution of
  AX = B
  "
  [widA widB A B]
  (as-> nil E
    (mx-pseudo-inverse widA A)
    (mx-prod (/ (count A) widA) widB E B)))

(comment

  ; residual -  remaining after the greater part or quantity has gone.

  (def A (rows->mx [[2 0]
                    [-1 1]
                    [0 2]]))

  (QR-linear-equation 2 A [1 0 -1])

  (least-squares-approximate 2 A [1 0 -1])

  (linear-equation-pseudo-inverse 2 A [1 0 -1])

  (least-squares-approximate 2 A [2 1 0])


  (def B (cols->mx [[1 0 -1]
                    [2 1 0]]))

  (prn-mx 2 (least-squares-X 2 2 A B))

  (def B (cols->mx [[1 0 -1]
                    [2 1 0]
                    [-1 3 1]]))
  (least-squares-approximate 2 A [-1 3 1])
  

  (prn-mx 3 (least-squares-X 2 3 A B))

  ;;;
  )