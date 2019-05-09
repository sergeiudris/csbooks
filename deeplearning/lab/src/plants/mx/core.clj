(ns plants.mx.core
  (:require [clojure.set]
            [clojure.repl :refer :all]))
   ;
            
  
(def e1 [1 0 0])
(def e2 [0 1 0])
(def e3 [0 0 1])



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
  (vec (repeat len nil)))


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
  
  (int (/ 11 5) )
  
  
  ;;;
  )

(defn col-by-row
  "returns the sum of products of mathcing elements"
  [row col]
  (->>
   (mapv * col row)
   (reduce +)))

(defn mkmx
  "returns falttened vector "
  [v]
  (->
   v
   flatten
   vec))

(comment

  (col-by-row [1 2 3] [4 5 6])

  (def A [1 2 3 4 5 6])

  (index->row 6 3)
  (index->col 5 3)

  (index->row 0 3)
  (index->row 1 3)
  (index->row 2 3)
  (index->row 3 3)


  (index->row 3 1)
  (index->col 3 1)

  (def B (flatten [[1 2 3]
                   [4 5 6]
                   [7 8 9]
                   [10 11 12]]))

  (def C [0 1 2])

  (index->row 11 3)

  (mx->row B 3 3)

  (mx->col C 1 0)

  (col-by-row  (mx->row B 3 3) (mx->col C 1 0))

  (multiply B C 3 1)


  ; the products (AB) C and A (BC) are defined if and only if 
  ; the number of columns of A equals the number of rows of B 
  ; and the number of columns of B equals the number of rows of C


  (def A (mkmx [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

  (def B (mkmx
          [[1 2 3]
           [4 5 6]]))

  (def C (mkmx
          [[0 1 2]
           [3 4 5]]))




  (def M1 (multiply A (multiply B C 3 2) 3 2))

  (def M2 (multiply  (multiply A B 3 3) C 3 2))

  (= M1 M2)



  ;;;
  )


(defn multiply
  "returns the matrix, resulted from multiplying A by B .
  A must have the same number of cols as B rows.
  The resulting mx has cols as B and rows as A"
  [A B w1 w2]
  (let [h1  (/ (count A) w1)
        len (* h1 w2)
        mx  (vec-of-len len nil)]
    (->>
     mx
     (map-indexed (fn [i x]
                    (let [m   (index->row i w2)
                          n   (index->col i w2)
                          row (mx->row A w1 m)
                          col (mx->col B w2 n)]
                      (col-by-row row col)
                      ;
                      )))
     vec
     ;
     )))
  

(defn element-wise-product
  " 
  returns a matrix that is the Hadamard product of A and B"
  [A B]
  (mapv * A B))


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


  (element-wise-product A A)
  
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




(defn fact
  "returns the afctorial of n"
  [n]
  (reduce * 1 (range 1 (inc n))))



(defn symmetric-group-oder
  "returns  the number (n!) that is the order (cardinality)
   of a set of n elems 
  "
  [n]
  (fact n))

; (defn sym-group-1
;   "returns the vector of sym groups of a set"
;   [v]
  
;   )


(defn sort-interchange-steps-cycle-col
  "returns [sorted-vec steps] after one cycle of interchaging vector elements
  "
  [v steps total-steps col]
  (cond
    ; (empty? v) (col [] 0 0)
    (= (count v) 1) (col v 0 total-steps)
    (< (second v) (first v))  (sort-interchange-steps-cycle-col (cons (first v) (drop 2 v)) steps total-steps
                                                                (fn col- [v-res steps- steps-total-]
                                                                  (col (cons (second v) v-res) (inc steps-) (inc steps-total-))))
    :else (sort-interchange-steps-cycle-col (rest v) steps total-steps
                                            (fn col- [v-res steps- steps-total-]
                                              (col (cons (first v) v-res) steps- steps-total-)))))



(def sort-steps
  "returns [sorted-vec steps] after sorting the vec by successively intercahnging elements 
  and counting steps"
  ((fn a [mk-sort-steps-fn]
     (mk-sort-steps-fn mk-sort-steps-fn))
   (fn ab [mk-sort-steps-fn]
     ((fn abc [sort-steps-fn]
        (fn abcd [v  steps steps-total]
          (cond
            (and (not (nil? steps)) (zero? steps))  [v steps-total]
            :else (sort-interchange-steps-cycle-col v nil steps-total sort-steps-fn))))
      (fn abcde [v steps steps-total]
        ((mk-sort-steps-fn mk-sort-steps-fn) v steps steps-total)))))
  ;;;
  )

(defn sort-steps-v2
  "returns [sorted-vec steps] after sorting the vec by successively intercahnging elements 
  and counting steps"
  [v  steps steps-total]
  (cond
    (and (not (nil? steps)) (zero? steps))  [v steps-total]
    :else (sort-interchange-steps-cycle-col v nil steps-total sort-steps-v2)))


(comment
  (cons 2 [12])

  (def a   [2 1 5 4 0])

  (sort-interchange-steps-cycle-col [2 1 5 4 0] 0 0 (fn [v steps steps-total]
                                                      [v steps-total]))
  
  (sort-steps [2 1 5 4 0] nil 0)
  
  (sort-steps [7 3 0 9 6 5 5 5 1 4] nil 0)
  
  (sort-steps [7 3 0 9 6 5 5 5 1 4] nil 0)
  
  (sort-steps-v2 [2 1 5 4 0] nil 0)
  
  (not nil)
  (nil)
  (cons (second a) (rest a))

  ;;;
  )

(defn sgn
  "returns the signature of a set (vector) of natural numbers
   a value that is +1 whenever the reordering given by σ can be achieved by 
  successively interchanging two entries an even number of times, 
  and −1 whenever it can be achieved by an odd number of such interchanges.
  This implementations expects elems to form a seq
  "
  [v]
  (cond
    (odd? (second (sort-steps-v2 v nil 0))) -1
    :else 1))



(comment
  
  (sgn [2 1 3] )
  
  ;;;
  )

(defn insert [i x v] (vec (concat (subvec v 0 i) [x] (subvec v i))))



(defn inc-group
  "returns vector of vectors where each has value 
  inserted between elements
  "
  [v]
  (let [new-len (inc (count v))]
    (->>
     (reduce-kv (fn [acc i x]
                  (assoc acc i (insert i (dec new-len) v))) [] (vec (range new-len)))
   ;
     )))

(defn inc-groups
  "returns vector of groups
  [[1]] -> [[1 2] [2 1]] -> [[1 2 3] [3 1 2] ...]
  "
  [v]
  (->>
   v
   ;  (int (/ (fact cnt) (fact (- cnt 1)) ))
   (mapv inc-group)
   (apply concat)
   vec
  ;  (map-indexed (fn [group-i group]

  ;                 (->
  ;                  (reduce-kv (fn [acc el-i el]
  ;                               (assoc acc el-i [el-i])) [] (vec (range (inc (count group)))))
  ;                  vec)))
   ;
   ))

(comment

  (insert-inbetween [[1 2] [2 1]] 1)
  
  (insert 2 3 [1 2]  )
  
  (inc-group [1 2] )
  
  (inc-groups [[1 2] [2 1]])
  

  ;;;
  )

(defn permutations-iter
  "returns all combinations (symmetric group) (vector of vectors) of elemnts of a set of {1... n}"
  [n cnt v]
  ; [[0 1 2][0 2 1][1 0 2][1 2 0][2 0 1][2 1 0]]
  (cond
    (> cnt n) v
    :else (->>
           v
           inc-groups
           (permutations-iter n (inc cnt))
           ;
           ))
  ; (mapv (fn [x] (vector)) (range (fact n)))
  )

(defn permutations
  "returns all combinations (symmetric group) (vector of vectors) of elemnts of a set of {1... n}"
  [n]
  (permutations-iter n 1 [[0]]))

(defn entries-product
  [mx width permutation]
  (reduce-kv (fn [acc i x]
               (* acc (mx->mn mx width i x))) 1 permutation))


(comment


  (def A (mkmx [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

  (entries-product A 3 [1 0 2])

  (fact 4)

  (permutations 4 6 [[1] [2] [3] [4] [5] [6]])

  (->>
   (permutations-iter 2 1 [[0]])
  ;  count
   )

  (permutations-iter 3 1 [[0]])

  (->>
   (permutations 2)
   (map #(list %1 (sgn %1)))
   ;
   )


  ;;;
  )

(defn det2x2
  "returns the determinant of a 2x2 mx"
  [mx]
  (->
   (- (* (mx 0) (mx 3)) (* (mx 1) (mx 2)))
   vector))

;https://en.wikipedia.org/wiki/Determinant
;https://en.wikipedia.org/wiki/Determinant#n_%C3%97_n_matrices
(defn det-leibniz
  "returns the determinant of a matrix.
  (sum-n (* (sgn sigma) (prod i (a i sigmai) ) ) )
  "
  [mx]
  (let [len   (count mx)
        width (int (Math/sqrt len))
        perms (permutations (dec width))]
    (reduce-kv (fn [acc i x]
                 (+ acc (* (sgn x) (entries-product mx width x)))) 0 perms)))

(comment

  (doc reduce-kv)

  (def A (mkmx [[1 2 3]
                [4 5 6]
                [7 8 9]]))

  (permutations 3)


  (det-leibniz  A)
  
  (def I (iden 3))

  (det-leibniz I)
  
  (def I (iden 4))
  
  (det-leibniz I)
  
  (det2x2 (iden 2))
  
  
  

  ;;;
  )


;https://en.wikipedia.org/wiki/Invertible_matrix
(defn inverse
  "returns the inverse of a matrix"
  [mx]
  [])


(comment

  (mx->mn [1 2 3 4] 2 0 1)

  (source plants.mx.core/mx->mn)

  (transpose [1 2 3] 1)
  
  (range 1 6)
  (fact 2)
  (fact 5)
  (fact 6)
  (fact 10)
  (fact 15)
  (fact 20)
  (fact 30)
  
  (concat)
  
  
  (symmetric-group-oder 3)
  
  
  
  
  
  
  [1]
  [2]
  [3]

  (transpose [1] 1)
  
  [1]
  
  (det2x2 [1 2 3 4])

  (det (iden3) )
  
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
  (->
   (reduce + 0 (map * v1 v2))
   vector))

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

(defn vec-sum
  "returns the sum of v1 v2"
  [a b]
  (add a b))

(defn vec-subtract
  "returns the result of subtracting b from a"
  [a b]
  (subtract a b))

(defn vec-divide
  "returns the result of dividing a by a"
  [a b]
  (mapv / a b))

(defn divide-scalar
  "returns the result of dividing v by a"
  [v a]
  (mapv #(/ % a) v ))

;https://en.wikipedia.org/wiki/Euclidean_vector#Length
(defn vec-length
  "returns the length or magnitude of norm of the vector"
  [a]
  (->
   (Math/sqrt (first (dot-product a a)))
   vector))

(defn vec-unit
  "returns the unit vector of v"
  [v]
  (divide-scalar v (vec-length v)))

(comment
  (vec-sum [1 1 0] [0 2 0])

  (vec-subtract [1 1 0] [0 2 0])

  (Math/sqrt 2)

  (vec-length [1 3 0])

  (->>
   [1 3 0]
   vec-length
   (divide-scalar [1 3 0])
   vec-length)


  ;;;
  )

;https://en.wikipedia.org/wiki/Euclidean_vector#Cross_product
(defn cross-product
  "returns the cross product of vectors a and b"
  [a b]
  []
  
  )

(defn iden
  "returns the identity matrix n by n"
  [n]
  (let [len (* n n)
        mx  (vec-of-len len nil)]
    (->>
     mx
     (map-indexed (fn mpr [i x]
                    (let [row-i (index->row i n)
                          col-i (index->col i n)]
                      (cond
                        (= row-i col-i) 1
                        :else 0))))
     vec)))

(defn iden3
  "returns 3x3 identity mx"
  []
  (iden 3))

(defn iden3
  "returns 3x3 identity mx"
  []
  (iden 4))

(comment

  (->
   (iden 3)
   (prnmx 3))
  
  (index->col 8 3)


  (->
   (iden 4)
   (prnmx 4))

  (->
   (iden 1)
   (prnmx 1))

  ;;;
  )


