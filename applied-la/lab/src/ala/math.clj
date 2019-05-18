(ns ala.math
  (:require [clojure.repl :refer :all]
            [ala.print :refer [cprn]]))



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

(defn prn-rows
  "Prtints rows, used by prn-mx"
  [wid A & {:keys [print-fn] :or {print-fn prn}}]
  (as-> nil R
    (mx->rows  wid A)
    (doseq [row R]
      ; (print)
      (print-fn row))))

(defn prn-mx
  "Prtints A"
  ([wid A]
   (prn-rows wid A)
   (println))
  ([wid A return?]
   (prn-rows wid A)
   (println)
   A))

(defn cprn-mx
  "Color-prtints A"
  ([wid A]
   (prn-rows wid A :print-fn cprn)
   (println))
  ([wid A return?]
   (prn-rows wid A :print-fn cprn)
   (println)
   A))

(comment

  (rows->mx [[1 2 3]
            [4 5 6]])

  (cprn-mx 3 (rows->mx [[1 2 3]
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

(defn make-vec
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

(defn iden-mx-3
  "Returns 3x3 identity mx"
  []
  (iden-mx 3))


(defn iden-mx-4
  "Returns 4x4 identity mx"
  []
  (iden-mx 4))


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

  
  (iden-mx-4 )
  ;;;
  )


(defn elwise-prod
  "Returns a vector (mx), multiplies a,b element-wise "
  [a b]
  (mapv * a b))

(defn elwise-sum
  "Returns a vector (mx), adds a,b element-wise "
  [a b]
  (mapv + a b))

(defn elwise-subtract
  "Returns a vector (mx), subtracts a,b element-wise "
  [a b]
  (mapv - a b))

(defn elwise-divide
  "Returns a vector (mx), divides a,b element-wise "
  [a b]
  (mapv / a b))

(defn dot-prod
  "Retruns the sum of products of corresponding els"
  [a b]
  (reduce + 0 (map * a b)))

(defn scalar-prod
  "Returns vector of scalar el products"
  [x v]
  (mapv #(* x %)  v))

(defn scalar-divide
  "Returns vector, divides v by scalar element-wise"
  [x v]
  (mapv #(/ % x)  v))

(defn scalar-add
  "Returns vector, adds a scalar element-wise"
  [x v]
  (mapv #(+ % x)  v))


(defn mx-prod
  "Returns the product of multiplying AB.
   Left mx must have cols as right mx rows.
   Result mx has A-rows, B-cols
  "
  [wid-A wid-B A B]
  (let [hei-A (/ (count A) wid-A)
        M     (make-mx hei-A wid-B nil)]
    (map-indexed (fn [i x]
                   (let [row-i (index->row i wid-B)
                         col-i (index->col i wid-B)
                         row   (mx->row row-i wid-A A)
                         col   (mx->col col-i wid-B B)]
                     (dot-prod row col)
                      ;
                     ))
                 M)))


(comment

  (def A (rows->mx [[1 2 3]
                    [2 5 6]]))

  (def B (rows->mx [[0 1]
                    [2 3]
                    [4 5]]))

  (prn-mx 2 (mx-prod 3 2 A B))
  
  ;;;
  )

(defn vec-broadcast
  "Returns mx with vector added to each row element-wise"
  [wid A a]
  (as-> nil R
    (mx->rows wid A)
    (mapv #(elwise-sum a %)  R)))


(comment

  (def A [1 2 3 4 5 6])

  (def a [1 2 3])

  (vec-broadcast  3 A a)

  ;;;
  )

(defn mx-square->size
  "Returns the size of a square mx"
  [A]
  (int (Math/sqrt (count A))))

(defn vec-norm
  "Returns the  Euclidean norm (length or magnitude) of a vector.
   L2 norm"
  [a]
  (Math/sqrt (dot-prod a a)))

(defn vec-L1-norm
  [a]
  "Returns the L1 norm, that grows at the same rate in all locations.
   Every time an element of x moves
   away from 0 by e , the L 1 norm increases by e"
  (->>
   a
   (mapv #(Math/abs %))
   (reduce +)))

(defn vec-max-norm
  "Returns the absolue value of the element with the largest magnitutde"
  [v]
  (->>
   v
   (mapv #(Math/abs %))
   sort
   last))

(defn mx-frobenius-norm
  "Returns the norm (size) of a matrix.
   Frobenius size of A = sqrt of the sum of a[ij]^2  
  "
  [A]
  (->>
   A
   (mapv  #(* % %))
   (reduce +)
   Math/sqrt))

(defn vec-unit
  "Returns the unit vector (normalizes) of a"
  [a]
  (scalar-divide (vec-norm a) a))


(defn count-non-zero
  "Returns the count of non-zero elemts of a vector.
   Mistakenly called L0 norm.
   It's not a norm, scaling vector does not change the count of non-zero elems
  "
  [a]
  (->>
   a
   (filterv (fn [x] (not (zero? x))))
   count))

(comment
  (vec-norm [0 0 2])
  (vec-unit [0 0 2])
  (count-non-zero [0 0 2])
  ;;;
  )

(defn mx-transpose
  "Returns transposed A - all indices are mirrored, e.g. 1,2 -> 2,1"
  [wid A]
  (let [len (count A)
        hei (/ len wid)]
    (reduce-kv (fn [acc i x]
                 (let [row-i (index->row i wid)
                       col-i (index->col i wid)
                       new-i (pos->index col-i row-i  hei)]
                   (assoc acc new-i x))) (make-mx hei wid nil) A)))




(defn mx-symmetric?
  "Returns true if matrix is equal to its own trasnpose"
  [A]
  (->
   (mx-transpose  (mx-square->size A) A)
   (= A)))

(defn vec-orthogonal?
  "returns true if both vectors are at 90 degree agle"
  [a b]
  (= (dot-prod a b) 0))

(comment
  (as-> nil R
    (make-mx-with 3 3  (fn [i x] i))
    (prn-mx 3 R true)
    (mx-transpose 3 R)
    (prn-mx 3 R )
    )
  
  
  (vec-orthogonal? [0 0 2] [1 0 0])
  ;;;
  )

(defn mx-reciprocal
  "returns the diag matrix with non-zero elems becoming 1/elem"
  [A]
  (mapv #(if (zero? %) %
            (/ 1 %)) A))

(defn mx-trace
  "Returns the sum of all diagonal entries of a matrix"
  [A]
  (let [size (mx-square->size A)]
    (->>
     A
     (keep-indexed #(if
                     (= (index->row %1 size) (index->col %1 size)) %2
                     nil))
     (reduce +))))

(defn vec-linear-comb
  "Returns a linear combination (sum of prodcuts)"
  [vs xs]
  (->>
   (mapv (fn [x v]
           (scalar-prod x v)) xs vs)
   (reduce elwise-sum)
   ;
   ))

(defn elwise-sum+
  "Returns the sum (linear combination) of vectors"
  [& vs]
  (reduce elwise-sum vs))

(defn vecs-mean
  "Returns an average vec, is a linear comb"
  [& vs]
  (scalar-prod (/ 1 (count vs)) (apply elwise-sum+ vs)))

(defn inner-prod
  "Reutrns the sum of products of correspondin els"
  [a b]
  (dot-prod a b))

; affine  - sum of coefficeints in lin com is 1
; convex - affine and all coefs are positive

(defn inner-prod-stacked
  "Returns the inner prod of block vectors. Must have the same size (conform)"
  [a b]
  (reduce + (mapv inner-prod  a b)))

(comment

  (elwise-sum+ [1 2] [2 3] [2 2])

  (scalar-prod 1/3 [5 7])

  (vecs-mean [1 2] [2 3] [2 2])

  (inner-prod [-1 2 2] [1 0 -3])

  (inner-prod [2] [2])

  (inner-prod-stacked [[1 2] [3 4]] [[2 3] [1 1]])
  
  (float (inner-prod [3/4 1/8 1/8] [1 2 3]))

  (* 74 8) ; 74mb vec is 592 in mem
  

  ;;;
  )


(def e1 [1 0 0])

(def e2 [0 1 0])

(def e3 [0 0 1])


(defn nnz
  "Returns the count of non-zero els of a"
  [a]
  (count (filterv #(not (= 0 %)) a)))

(defn make-vecec
  "Returns a vec given size, el"
  [size el]
  (vec (repeat size el)))

(defn vec-sum
  "Returns the sum of numbers.
   Sum is a linear combintaion of scalars"
  [a]
  (reduce + 0 a))

(defn fact
  "Returns factorial of x"
  [x]
  (reduce * 1 (range 1 (inc x))))

(defn vec-mean
  "Returns avg of a vector"
  [v]
  (/ (vec-sum v) (count v)))

(defn vec-max
  "Returns the max el of vec"
  [v]
  (apply max v))

(comment

  (nnz [1 2 0 0 5 6])
  (make-vecec 3 0)
  (mx/iden-mx 3)
  
  (vec-mean [1 2 3])
  

  ;;;
  )

(defn vec-median
  "Returns the median of the vector"
  [a]
  (let [size (count a)
        v    (vec (sort a))]
    (cond
      (odd? size) (v (-> size dec (/ 2) ))
      :else (vec-mean (vector (v (-> size (/ 2) dec)) (v (/ size 2)))))))

(defn mean-square
  "Returns the sum of squared vec els, divided by el count"
  [a]
  (/ (dot-prod a a) (count a)))

(defn root-mean-square
  "Returns suqare root of mean-square.
   Tells what a 'typical'  |el| looks like"
  [a]
  (Math/sqrt (mean-square a))
  ; (/ (vec-norm a) (Math/sqrt (count) ) )
  )


(defn vec-norm-stacked
  "Returns the norm of a vector, formed from norms of subvecs"
  [aa]
  (vec-norm (mapv #(vec-norm %)  aa)))

(defn vec-dist
  "Returns the distance between two vecs (points in n dimension)"
  [a b]
  (vec-norm (elwise-subtract a b)))

(defn rms-deviation
  "Returns the root-mean-square of the difference a - b"
  [a b]
  (root-mean-square (elwise-subtract  a b)))

(defn rms-prediction-error
  "Returns the root-mean-square of y-ye"
  [y ye]
  (root-mean-square (elwise-subtract y ye)))

(defn nearest-neighbor
  "Returns the closest vec z (of zs) to vec x"
  [x zs]
  (as-> nil R
    (map-indexed #(vector %1 (vec-dist x %2))  zs)
    (sort-by second R)
    (ffirst R)
    (vector R (zs R))))


(comment

  (mx/elwise-sum [0 7 3] [1 2 0])

  (mx/scalar-add 2 [1 2 3])

  (mx/scalar-prod -2 [1 9 6])

  (mx/vec-linear-comb [[1 2 3] [0 0 1]] [1 2])

  (inner-prod [0.5 1.5] [0.481 0.736])

  (inner-prod [0.12 0.31 0.26] [0.5 1.1 0.3])
  (inner-prod  [0.12 0.31 0.26] [1.5 0.8 1.2])


  ; house prices regression model

  (+ (inner-prod [148.73 -18.85] [0.846 1]) 54.40)
  (inner-prod [1 148.73 -18.85] [54.40 0.846 1])

  (vec-mean [1 2])

  (vec-median [-7 -6 -5 -4 -3 1  2 3 4 7])

  (root-mean-square [1 2 3 4])

  (dot-prod [1 1 1 2 2 2]  [1 1 1 2 2 2])

  (/ (vec-norm [1 1 1 2 2 2]) (Math/sqrt 6))

  (root-mean-square [1 1 1 2 2 2])

  (vec-norm [1 1 1 1 1 1 1])

  (root-mean-square [1 1 1 1 1 1 1])

  (vec-norm-stacked [[1 0] [0 1]])

  (vec-norm [1 1])
    (prn R)    (prn R)

  (vec-dist [1 0 0] [3 0 0])

  (vec-dist [1 1 0] [2 2 0])


  (nearest-neighbor [5 6] [[2 1] [7 2] [5.5 4] [4 8] [1 5] [9 6]])

  ;;;
  )
(defn sq
  "Returns x squared"
  [x]
  (Math/pow x 2))

(defn vec-demeaned
  "Returns the de-meaned vec - subtract vec-mean from each entry"
  [a]
  (elwise-subtract a (scalar-prod (vec-mean a) (make-vec (count a) 1))))

; 4 flops ?
(defn vec-standard-deviation
  "Returns root-mean-square of the de-meaned vec"
  [a]
  (root-mean-square (vec-demeaned a)))

; 3 flops ?
(defn vec-standard-deviation-2
  "Returns root-mean-square of the de-meaned vec"
  [a]
  (Math/sqrt (- (sq (root-mean-square a)) (sq (vec-mean a)))))



(defn diff-max-min
  "Returns the diff of max nad min els"
  [a]
  (as-> nil R
    (sort a)
    (- (last R) (first R))))

(defn ==** 
  "Returns true, if diff between args is <= 0.00001"
  [& args]
  (apply == args)
  (<= (Math/abs (diff-max-min args)) 0.00001))

(defn ==*
  "Returns true, if diff between x and y is <= 0.00001"
  [x y]
  (<= (Math/abs (- y x)) 0.00001))

(comment

  (vec-mean (vec-demeaned [1 2 3]))

  (vec-standard-deviation (vec-demeaned [1 2 3]))
  ; same eas
  (rms-deviation [1 2 3] (mapv (fn [x] (vec-mean [1 2 3])) [1 2 3]))

  (vec-standard-deviation [2 2 2 2 2])
  (def a [1 -2 3 2])
  (vec-demeaned a)
  (vec-mean a)
  (format  "%.3f"  (vec-standard-deviation a))
  (vec-standard-deviation a)
  (vec-standard-deviation-2 a)
  (root-mean-square (vec-demeaned a))

  (source ==)

  (==* 1 1.0 1)

  (vec-mean [5 4.999999])
  (vec-demeaned [5 4.999999])

  (==* 4.9999999M 4.9999999)

  (diff-max-min [4 3 2 -1 11])

  (==** 5 4.99999)

  (==* -4.999999 -5M)

  ;;;
  )


(defn vec-standardized
  "Returns de-meaned vector, divided by standard deviation"
  [a]
  (scalar-divide (vec-standard-deviation a) (vec-demeaned a)))

(defn rad->deg
  "Returns degrees (from value in radians)"
  [x]
  (* x (/ 180 Math/PI)))

(defn deg->rad
  "Returns radians (from value in degrees)"
  [x]
  (/ (* x Math/PI) 180))

(defn vec-angle
  "Returns the angle (radians) between two non-zero vecs"
  [a b]
  (Math/acos (/ (inner-prod a b) (* (vec-norm a) (vec-norm b)))))

(defn spherical-dist
  "Returns the dist between two 3-vecs on a spehre of radius r"
  [r a b]
  (* r (vec-angle a b)))

(defn latlon->3-D
  "Returns the 3-D coords for lat lon, given radius "
  [r lat lon]
  (let [lat-rad (deg->rad lat)
        lon-rad (deg->rad lon)]
    [(* r (Math/sin lon-rad) (Math/cos lat-rad))
     (* r (Math/cos lon-rad) (Math/cos lat-rad))
     (* r (Math/sin lat-rad))]))

(defn vec-correlation-coef
  "Returns the correlation coefficient (cos of angle) between de-meaned vecs "
  [a b]
  (let [[ad bd] [(vec-demeaned a) (vec-demeaned b)]]
    (/ (inner-prod ad bd) (* (vec-norm ad) (vec-norm bd)))
    ; (Math/cos (vec-angle ad bd))
    ))

(comment

  (def x-standardized (vec-standardized [1 2 3  5]))

  (vec-mean x-standardized)
  (vec-standard-deviation x-standardized)

  (vec-correlation-coef [1 0 0] [-1 0 0])

  (vec-correlation-coef [1 0 0] [2 0 0])

  (rad->deg 0.9661)
  (deg->rad 55.35)

  (def Earth-radius 6367.5)

  (def Beijing (latlon->3-D Earth-radius 39.914 116.392))
  (def Palo-Alto (latlon->3-D Earth-radius 37.429 -122.138))

  (spherical-dist Earth-radius Beijing  Palo-Alto) ; 9093 km


  ;;;
  )

(defn j-clust
  "Returns a scalar J (clustering objective estimate).
   The lower the better clustering is.
   Mean square distance from vecs to their representatives.
   "
  [xs zs cs]
  (->
   (reduce-kv (fn [acc i x]
                (+ acc (sq (vec-dist x (zs (cs i)))))) 0 xs)
   (/ (count xs))))



(defn j-clust-nearest
  "Returns a scalar J.
   Is a version of j-lcust, picks min||x - z|| (distance from vec to representative)
   to minimize J.
  "
  [xs zs]
  (->
   (reduce-kv (fn [acc i x]
                (+ acc (sq (vec-dist x (second (nearest-neighbor x zs)) )))) 0 xs)
   (/ (count xs))))

(defn group-centroid
  "Returns the average of vecs"
  [xs]
  (apply vecs-mean xs))


(defn -k-means-lazy-partition
  "Does not work, take-while is sequential, coll is not sorted"
  [xs zs]
  (lazy-seq
   (when-let [xs-s (seq xs)]
     (let [fst       (first xs-s)
           nrst-neig (second (nearest-neighbor fst zs))
           run       (cons fst (take-while #(= nrst-neig (second (nearest-neighbor % zs))) (next xs-s)))]
       (cprn run)
       (prn)
       (cons run (k-means-lazy (lazy-seq (drop (count run) xs-s))  zs))
        ;
       ))))

(defn k-means-iteration
  "Returns k-means groups after one iteration."
  [xs zs]
  (persistent!
   (reduce-kv
    (fn [acc i x]
      (let [nrst-neig (nearest-neighbor x zs)
            i-zs      (first nrst-neig)]
        (assoc! acc i-zs (conj (get acc i-zs []) x)))
      ;
      )
    (transient {}) xs)))

(defn k-means
  "Returns a group map {i-group [[x] [x] ..] .. }.
   Iterates until J (objective) is minimized"
  ([xs zs]
   (k-means xs zs nil 0))
  ([xs zs J-prev cnt]
   (let    [groups (k-means-iteration xs zs)
            J      (reduce + (mapv (fn [[i g]]
                                     (j-clust-nearest g zs)) groups))]
    ;  (cprn {:groups groups
    ;         :iter   cnt
    ;         :zs     zs
    ;         :J      J})
     (cond
       (= J J-prev) {:J      J
                     :zs     zs
                     :groups groups}
       :else (k-means xs (mapv (fn [[i g]]
                                 (apply vecs-mean g)) groups) J (inc cnt))
       ;
       ))))


(comment

  (def points-A [[0.5 8.5]
                 [2.2 11.5]
                 [2.7 0]
                 [3.7 2.5]
                 [3.5 5.3]
                 [3.5 7.2]
                 [5 5.5]
                 [7.1 5.1]
                 [7.5 4.9]
                 [7.8 6.8]])

  (def points-B [[4.5 12.8]
                 [6.1 13.8]
                 [8 14.9]
                 [8.5 14.6]
                 [7.9 17.1]
                 [12.5 18]
                 [11 12.9]
                 [11.5 10.1]
                 [12.9 11]])
  (def points-C [[10 7.5]
                 [10.9 7.1]
                 [12.1 10]
                 [14.1 9.9]
                 [12 4.5]
                 [12.9 1]
                 [15.8 0.2]
                 [15.7 1.4]
                 [15.9 7]
                 [14.4 7.1]])

  (def points (shuffle (concat points-A points-B points-C)))
  (count points)

  (def points-A-centroid [4 5])
  (def points-B-centroid [9 15])
  (def points-C-centroid [14 5])

  (def zs [points-A-centroid points-B-centroid points-C-centroid])


  (def clusters (vec (k-means-lazy points zs)))
  (count clusters)

  (nearest-neighbor [2.7 0] zs)
  (nearest-neighbor [10 7.5] zs)

  (partition-by odd? [1 2 2 3 1 4 5 6 8 10 9 1 2 7 4])

  (def groups (k-means-iteration points zs))

  (reduce + (mapv (fn [[i g]]
                    (j-clust-nearest g zs)) groups))

  (def zs-2 [[3 3] [8 8] [15 15]])

  (def zs-3 [[0 10] [10 0] [13 15]])


  (def clusters-2 (k-means points zs))

  (def clusters-3 (k-means points zs-2)) ; successfully does 5 iterations, prn progress

  (def clusters-4 (k-means points zs-3))

  (def clusters-5 (k-means points [(rand-nth points) (rand-nth points) (rand-nth points)]))


  (doseq [c [clusters-2 clusters-3 clusters-4 clusters-5]]
    (cprn (c :J))
    (cprn (c :zs)))




  ;;;
  )

