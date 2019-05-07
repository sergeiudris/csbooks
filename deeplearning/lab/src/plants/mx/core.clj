(ns plants.mx.core
  (:require [clojure.set]
            [clojure.repl :refer :all]))
   ;
            
  


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
  (int (/ i row-len)))
  

(defn index->col
  "returns the col number given elem index, row-len"
  [i row-len]
  (mod i row-len))

(defn mnwidth->index
  "returns index given m,n, width"
  [width m n]
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
    (- (- width n))))
   
  (comment
    
    (mnwidth->index 3 2 1)
    
    ;;;
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
   vec))
   
  


(defn mx->mn
  "returns the m,n elem of mx"
  [mx width m n]
  (mx (mnwidth->index width m n)))
  

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

  (mx->mn [1 2 3 4 5 6 7 8 9] 3 1 0)

    ;;;
  )


(defn vec-of-len
  "returns vecctor os specified len and elem"
  [len elem]
  (vec (repeat len nil))
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
                   (assoc res new-i x))) (vec-of-len len nil) mx)))


(defn prnmx
  "prints the matrix"
  [mx width]
  (doseq [row (partition width mx)]
    (println row))
  (println)
  mx)

(comment

  (source mapv)

  (transpose [1 2 3 4 5 6] 3)

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
  
  

(defn vec-by-vec
  "returns a sum of multiplying v1 elems by v2 elems"
  [v1 v2]
  (reduce-kv (fn rcr [acc i x] 
               (->>
                (v2 i)
                (* x)
                (+ acc)))
                
             0 v1))
  
(defn mx->rows
  "partitions mx into vector of rows"
  [width mx]
  (mapv vec (partition width mx))
  )

(defn rows->mx
  "returns mx from rows"
  [rows]
  (->>
   rows
   flatten
   vec))

(comment
  (->>
   (mx->rows 3 [1 2 3 4 5 6])
   flatten
   vec)
  
  (rows->mx [[1 2 3] [4 5 6]])

  ;;;
  )

(defn multiply-vec
  "Returns the matrix, resulting from multiplying mx by vec. mx width should be equal to vec length"
  [mx v]
  (let [width (count v)
        rows  (mx->rows width mx)]
     
     (mapv (fn mr [row]
             (vec-by-vec row v)) rows)))
             

(defn mx->col
  "returns a  col n of mx"
  [mx width n]
  
  )

(defn mx->row
  "returns a row n of mx"
  [mx width n]
  (->
   (keep-indexed  (fn mr [i x] (if (= (index->row i width) n) x nil)) mx)
   vec))

(defn mx->col
  "returns a row n of mx"
  [mx width n]
  (->
   (keep-indexed  (fn mr [i x] (if (= (index->col i width) n) x nil)) mx)
   vec))


(comment


  (keep-indexed  (fn mr [i x] (if (= (index->row i 3) 0) x nil ) ) [1 2 3 4 5 6])
  
  (mx->row [1 2 3 4 5 6] 3 1)
  (mx->col [1 2 3 4 5 6] 3 1)
  (mx->col [1 2 3 4 5 6] 3 0)
  (mx->col [1 2 3 4 5 6] 3 2)
  
  
  
  ;;;
  )

(defn col-by-row
  "returns the sum of products of mathcing elements"
  [row col]
  (->>
   (mapv * col row)
   (reduce +)))

(comment

  (col-by-row [1 2 3] [4 5 6])

  ;;;
  )


(defn multiply
  "returns the matrix, resulted from multiplying mx1 by mx2.
  The resulting mx has cols as mx2 and rows as mx1"
  [mx1 mx2 w1 w2]
  (let [h1  (/ (count mx1) w1)
        len (* h1 w2)
        mx  (vec-of-len len nil)]
    (->>
     mx
     (map-indexed (fn [i x]
                    (let [m   (index->row i w2)
                          n   (index->col i w2)
                          row (mx->row mx1 w1 m)
                          col (mx->col mx2 w2 n)]
                      (col-by-row row col)
                      ;
                      )))
     vec
     ;
     )))
  




(comment


  (def A [1 2 3 4 5 6])

  (def B [0 1 2 3 4 5])

  (->
   (multiply A B 3 2) ; [16 22 34 49]
   (prnmx 2))


  (plants.mx.core/multiply [1 2 3 4 5 6 7 8 9 10 11 12]  [-2 1 0] 3 1)

  (plants.mx.core/multiply [1 2 3 4 5 6]  [1 2 3 4 5 6] 3 2)
  
  (plants.mx.core/multiply [0 4 -2 -4 -3 0]  [0 1 1 -1 2 3] 3 2) ; [0 -10 -3 -1]
  
  (plants.mx.core/multiply [1 2 3 4]  [1 2 3 4 ] 2 2)
  

  (mx->row mx w2 m)

  (index->row 3 2)
  (index->row 2 2)
  (index->col 0 2)



  ; A
  [1 2 3]
  [4 5 6]

  ; b
  [0]
  [1]
  [2]

  ; * A b

  [8]
  [17]

  (multiply-vec [1 2 3 4 5 6] [0 1 2])

  ([0 1 2] 1)

  (mapv vec '((1 2) (2 3)))

    ; A
  [1 2 3]
  [4 5 6]

  ; B

  [0 1]
  [2 3]
  [4 5]

  ; AxB  2 x 2
  [16 22]
  [34 49]

  ; BxA 3 x 3

  [4 5 6]
  [14 19 24]
  [24 33 42]



  (multiply A B 3 2)

  (map identity [1 2])

  (map * [1 2 3] [4 5 6])

  (reduce + [1 2 3])

  (multiply A B 3 2)


  ;;;
  )
   

(defn add
  "returns the matrix resulting from adding mx1 to mx2"
  [mx1 mx2]
  (mapv + mx1 mx2))


(defn subtract
  "returns the matrix resulting from subtracting mx2 from mx1"
  [mx1 mx2]
  (mapv - mx1 mx2))

(comment

  (->
   (add [1 2 3 4 5 6] [1 2 3 4 5 6])
   (prnmx 3))


  (->
   (subtract [1 2 3 4 5 6] [2 4 6 8 10 12])
   (prnmx 3))

  ;;;
  )


;https://en.wikipedia.org/wiki/Determinant
(defn determinant
  "returns the determinant of a matrix"
  [mx width]
  [])


;https://en.wikipedia.org/wiki/Invertible_matrix
(defn inverse
  "returns the inverse of a matrix"
  [mx]
  [])


(comment

  (mx->mn [1 2 3 4] 2 0 1)

  (source plants.mx.core/mx->mn)

  (transpose [1 2 3] 1)
  
  [1]
  [2]
  [3]

  (transpose [1] 1)
  
  [1]

  
  
  ;;;
  )


(defn broadcast
  "returns the matrix resulting from adding v to mx"
  [mx width v]
  (let [rows (mx->rows width mx)]
    (->>
     rows
     (mapv (fn mr [row]
             (mapv + row v)))
     rows->mx)))

(defn add-scalar
  "returns the matrix after adding a const to each element"
  [mx scalar]
  (mapv #(+ scalar %)  mx))

(defn multiply-scalar
  "returns the matrix after adding a const to each element"
  [mx scalar]
  (mapv #(* scalar %)  mx))

; https://en.wikipedia.org/wiki/Dot_product#Definition
(defn dot-product
  "returns the number , result of dot product of v1 v2"
  [v1 v2]
  (reduce + 0 (map * v1 v2)))

(comment

  (def A [1 2 3 4 5 6])
  
  (def a [1 2 3])
  
  (broadcast A 3 a)
  
  (add-scalar A 3)
  
  (mapv #(+ % 1) [1 2])
  
  (reduce + 0 (map + [1 2] [3 4]) )
  
  (dot-product [1 2] [3 4] )
  
  (multiply-scalar A 2 )

  ;;;
  )