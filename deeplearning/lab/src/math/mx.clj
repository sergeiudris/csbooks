(ns math.mx
  (:require [clojure.repl :refer :all]
            [io.print :refer [cprn]]))

(defn make-mx
  "returns a vector. falttens the A"
  [A]
  (->
   (flatten A)
   vec))

(defn mx->rows
  "returns two dim vector of rows of A"
  [wid A]
  (->>
   (partition wid A)
   (mapv vec)))

(defn prn-mx
  "prtints A"
  [wid A]
  (as-> nil R
    (mx->rows  wid A)
    (doseq [row R]
      ; (print)
      (cprn row))))

(comment

  (make-mx [[1 2 3]
            [4 5 6]])

  (prn-mx 3 (make-mx [[1 2 3]
                      [4 5 6]]))

  ;;;
  )