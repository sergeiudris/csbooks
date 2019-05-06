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

(defn mnwidth->index
  "returns index given m,n, width"
  [width m n ]
  ; (cond
  ;   (zero? m) n 
  ;   (zero? n) (* m width)
  ;   ; :else (->>
  ;   ;        (* (- m 1) width)
  ;   ;        (+ n))
  ;   :else (- (* (inc m) width) (- width n)  )
  ;   )
  (->
    (* (inc m) width)
     (- (- width n))
   )
  )

(defn mx-mn
  "returns the matrix of (mk-elem m n) with m rows, n cols"
  [m n mk-elem]
  (->>
  ; (repeatedly (* m n) #(mk-elem m n) )
  ;  (repeatedly (* m n))
   (range (* m n))
  ;  (map-indexed #(mk-elem (index->row %1 n) (index->col %2 n)))
   (map-indexed #(mk-elem %1  %2))
   vec
   )
  )


(defn mx->mn
  "returns the m,n elem of mx"
  [mx width m n ]
  (mx (mnwidth->index width m n ) )
  )

(comment
  (iden)

  ([:a :b] 1)

  (#{1} [:a :b])


  (filter #{1 2} [:a :b])

  (source filter)

  (->>
   [:a :b]
   (filterv (fn filter-fn [x] (#{1 2} x))))

  (source filterv)


  (->>
   [:a :b]
   (keep-indexed (fn pred [i x]  (if (#{1 2} i) x)))
   vec)

  (vec-s [:a :b :c] #{3 7})
  (vec-s- [:a :b :c] #{3 7})

  (mx-mn 3 3 #(str %1 "," %2))
  (take 5 (repeatedly (* 3 3)))

  (mod 0 3)

  (/  5 3)

  (mx-mn 3 3 (fn [i x] 3))

  (mx->mn [1 2 3 4 5 6 7 8 9] 3 0 2)

  (mx->mn [1 2 3 4 5 6 7 8 9] 3 2 1)

  (mx->mn [1 2 3 4 5 6 7 8 9] 3 2 0)

  (mx->mn [1 2 3 4 5 6 7 8 9] 3 1 0)



    ;;;
  )

(defn transpose
  "returns transposed matrix - all indices are mirrored, e.g. 1,2 -> 2,1"
  [mx width]
  (let [len    (count mx)
        height (/ len width)]
    (reduce-kv (fn iter [res i x]
                 (let [m     (index->row i width)
                       n     (index->col i width)
                       new-i (mnwidth->index height n m)]
                  ;  (prn i x width m n new-i)
                   (assoc res new-i x))) (vec (repeat len nil)) mx )
    ;;;
    ))


(defn prnmx
  "prints the matrix"
  [mx width]
  (doseq [row (partition width mx)]
    (println row))
  (println)
  mx)

(comment
  
  (source mapv)
  
  (transpose [1 2 3 4 5 6 ] 3)
  
  (assoc [1 2 3] 4 5)
  
  (mnwidth->index 3 1 0)
  
  (assoc (vec (repeat 5 nil)) 3 3)
  
  (prnmx  [1 2 3 4 5 6 7 8 9] 3)
  
  (->
   (mx-mn 3 3  (fn [i x] i))
   (prnmx 3)
   (transpose 3)
   (prnmx 3))
  
  ;;;
  )
