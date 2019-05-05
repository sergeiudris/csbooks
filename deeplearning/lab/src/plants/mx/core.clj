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
 
  
  )