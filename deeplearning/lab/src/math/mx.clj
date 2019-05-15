(ns math.mx
  (:require [clojure.repl :refer :all]
            [io.print :refer [cprn]]))



(defn make-mx
  "Returns a mx given width, height and element"
  [wid hei el]
  (vec (repeat (* wid hei) el)))

(defn make-mx-with
  "Returns a matrix given width, height and make-el"
  [wid hei make-el]
  (->>
   (range (* wid hei))
   (map-indexed #(make-el %1  %2))
   vec))

(defn rows->mx
  "Returns a vector. flattens the A"
  [A]
  (->
   (flatten A)
   vec))

(defn mx->rows
  "Returns two dim vector of rows of A"
  [wid A]
  (->>
   (partition wid A)
   (mapv vec)))

(defn prn-mx
  "Prtints A"
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
  "Returns the row index given el index, row length"
  [i wid]
  (int (/ i wid)))


(defn index->col
  "returns the col index given el index, row length"
  [i wid]
  (mod i wid))

(defn index->pos
  "Returns the position [i,j] el in the mx of given width"
  [i wid]
  (vector (index->row) (index->col)))

(defn pos->index
  "Returns el index given row, col and row length"
  [i j wid]
  (->
   (* (inc i) wid)
   (- (- wid j))))


(defn mx->el
  "Returns the i,j el of A"
  [i j wid A]
  (A (pos->index i j wid)))

(defn mx->row
  "Returns i-th row of the mx"
  [i wid A]
  (->
   (keep-indexed  #(if (= (index->row %1 wid) i) %2 nil) A)
   vec))

(defn mx->col
  "Returns i-th row of the mx"
  [i wid A]
  (->
   (keep-indexed  #(if (= (index->col %1 wid) i) %2 nil) A)
   vec))

(comment
  
  (mx->row 1 3 [1 2 3 4 5 6] )
  (mx->col 1 3 [1 2 3 4 5 6])
  
  ;;;
  )

(defn make-v
  "Returns a vector of specified size and el"
  [size el]
  (vec (repeat size el)))



(defn iden-mx
  "Returns an identity mx of size"
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


(defn diag
  "Returns a square mx with the vector as the diagonal "
  ([v]
   (->
    (let [size (count v)
          A    (make-mx size size 0)]
      (map-indexed (fn [i x]
                     (let [row-i (index->row i size)
                           col-i (index->col i size)]
                       (cond
                         (= row-i col-i) (nth v row-i)
                         :else x))) A))
    vec))
  ([wid hei v]
   (->
    (let [size (count v)
          A    (make-mx wid hei 0)]
      (map-indexed (fn [i x]
                     (let [row-i (index->row i wid)
                           col-i (index->col i wid)]
                       (cond
                         (and (<= row-i size) (= row-i col-i) (and (< row-i size))) (nth v row-i)
                         :else x))) A))
    vec)))

(comment

  (diag [1 2 3])
  (diag  4 4 [1 2 3])
  (get [1 2 3]  4)

  ;;;
  )


(defn elwise-prod
  "Returns a vector (mx), multiplies a,b element-wise "
  [a b]
  (mapv * a b))

(defn elwise-sum
  "Returns a vector (mx), multiplies a,b element-wise "
  [a b]
  (mapv + a b))

(defn dot-prod
  "Retruns the sum of products of corresponding els"
  [a b]
  (reduce + 0 (map * a b)))

(defn scalar-prod
  "Returns vector of scalar el products"
  [x v]
  (mapv #(* x %)  v))


(defn mx-prod
  "Returns the product of multiplying AB.
   Left mx must have cols as right mx rows.
   Result mx has A-rows, B-cols
  "
  [A B]
  []
  )



