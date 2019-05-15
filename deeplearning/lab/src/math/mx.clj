(ns math.mx
  (:require [clojure.repl :refer :all]
            [io.print :refer [cprn]]))



(defn make-mx
  "returns a mx given width, height and element"
  [wid hei el]
  (vec (repeat (* wid hei) el)))

(defn make-mx-with
  "returns a matrix given width, height and make-el"
  [wid hei make-el]
  (->>
   (range (* wid hei))
   (map-indexed #(make-el %1  %2))
   vec))

(defn rows->mx
  "returns a vector. flattens the A"
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

  (rows->mx [[1 2 3]
            [4 5 6]])

  (prn-mx 3 (rows->mx [[1 2 3]
                      [4 5 6]]))

  ;;;
  )

(defn index->row
  "returns the row index given el index, row length"
  [i wid]
  (int (/ i wid)))


(defn index->col
  "returns the col index given el index, row length"
  [i wid]
  (mod i wid))

(defn ij->index
  "returns el index given row, col and row length"
  [i j wid]
  (->
   (* (inc i) wid)
   (- (- wid j))))


(defn mx->ij
  "returns the i,j el of A"
  [i j wid A]
  (A (ij->index i j wid)))

(defn make-v
  "returns a vector of specified size and el"
  [size el]
  (vec (repeat size el)))



(defn iden-mx
  "returns an identity mx of size"
  [size]
  (let [A  (make-mx size size nil)]
    (->>
     A
     (map-indexed (fn mpr [i x]
                    (let [row-i (index->row i size)
                          col-i (index->col i size)]
                      (cond
                        (= row-i col-i) 1
                        :else 0))))
     vec)))


