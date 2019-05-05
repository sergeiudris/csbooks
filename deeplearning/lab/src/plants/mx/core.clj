(ns plants.mx.core
  (:require [clojure.set]
            [clojure.repl :refer :all]
   ;
            )
  )


(defn iden
  "returns an identity matrix"
  []
  [])

(defn mul
  "retruns a result of matrix multiplication"
  [mx1 mx2 & mxs]
  [])

(defn vec-s
  "returns vector including only elems with indices in i-set"
  [v i-set]
  (->>
   v
   (keep-indexed (fn pred [i x]  (if (i-set i) x)))
   vec))

(defn vec-s-
   "returns vector excluding elems with indices in i-set"
  [v i-set]
  (->>
   v
   (keep-indexed (fn pred [i x]  (if (not (i-set i)) x)))
   vec))


(defn index->row
  "returns the row number given elem index and row length"
  [i row-len]
  (int (/ i row-len))
  )

(defn index->col
  "returns the col number given elem index, row-len"
  [i row-len]
  (mod i row-len))

(defn mx-mn
  [m n mk-elem]
  "returns the matrix of (mk-elem m n) with m rows, n cols"
  (->>
  ; (repeatedly (* m n) #(mk-elem m n) )
  ;  (repeatedly (* m n))
   (range (* m n))
   (map-indexed #(mk-elem (index->row %1 n) (index->col %2 n)))
   )
  )


(defn mx->mn
  [mx]
  "returns the m,n elem of mx"
  
  )

(comment
  (iden)
  
  ([:a :b] 1)
  
  (#{1 } [:a :b])


 (filter #{1 2} [:a :b] ) 
  
  (source filter)
  
    (->>
     [:a :b]
     (filterv (fn filter-fn [x] (#{1 2} x) ))
     )
  
  (source filterv)
  
  
  (->>
   [:a :b]
   (keep-indexed (fn pred [i x]  (if (#{1 2} i) x) ))
   vec
   )
 
  (vec-s [:a :b :c ] #{  3 7})
  (vec-s- [:a :b :c] #{3 7})
 
  (mx-mn 3 3 #(str %1 "," %2))
  (take 5 (repeatedly (* 3 3)))
  
  (mod 0 3)
  
  (/  5 3)
  
    ;;;
  )