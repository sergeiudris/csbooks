(ns plants.mx.core
  (:require [clojure.set]
            [clojure.repl :refer :all]
   ;
            )
  )


(defn iden
  "returns identity matrix"
  []
  [])

(defn mul
  "multiplies matrices"
  [mx1 mx2 & mxs]
  [])

(defn filter-vec-by-index-set
  [v iset]
  (->>
   v
   (keep-indexed (fn pred [i x]  (if (iset i) x)))
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
 
  (filter-vec-by-index-set [:a :b :c ] #{  3 7})
  
  )