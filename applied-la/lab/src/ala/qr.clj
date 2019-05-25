(ns ala.qr
  (:require [clojure.repl :refer :all]
            [ala.print :refer [cprn]]
            [ala.math :refer [mx->cols cols->mx make-mx index->row
                              index->col mx->order mx-transpose
                              mx->el rows->mx ==*
                              ]]))

(defn elwise-subtract
  "Returns a vector (mx), subtracts a,b element-wise "
  [a b]
  (mapv - a b))

(defn scalar-prod
  "Returns vector of scalar el products"
  [x v]
  (mapv #(* x %)  v))

(defn inner-prod
  "Reutrns the sum of products of correspondin els"
  [a b]
  (reduce + 0 (map * a b)))

(defn scalar-divide
  "Returns vector, divides v by scalar element-wise"
  [x v]
  (mapv #(/ % x)  v)
  ; (prn x v)
  ; (mapv #(with-precision 10 (/ (bigdec %) (bigdec x)))  v)
  )

(defn vec-norm
  "Returns the  Euclidean norm (length or magnitude) of a vector.
   L2 norm"
  [a]
  ; (bigdec (Math/sqrt (dot-prod a a)))
  (Math/sqrt (inner-prod a a)))

(defn vec-normalize
  "Returns the unit vector (normalizes) of a"
  [a]
  (scalar-divide (vec-norm a) a))

(defn gram-schmidt-q
  "Returns qi as aprt of orthonogalization step"
  [x qs]
  (as-> nil E
    (reduce-kv (fn [acc i q]
                 (->>
                  (scalar-prod (inner-prod q x) q)
                  (elwise-subtract acc)
                  ;
                  ))x qs)))

(defn gram-schmidt-qs
  "Returns [qs- qs] an orthonormal collection of vectors qi..qk.
   Each xi is a linear comb of qs and each qi is a lincomb of xi..xk.
    "
  ([xs]
   (gram-schmidt-qs xs [] []))
  ([xs qs- qs]
   (if (empty? xs) [qs- qs]
       (let [xi (first xs)
             qi (gram-schmidt-q xi qs)]
        ;  (prn xi qs qi)
         (cond
           (every? #(==* 0 %) qi) (do (cprn qi) false)
          ;  (every? #(== 0 %) qi) (do (cprn qi) false)
           :else (recur (vec (rest xs))
                        (vec (conj qs- qi))
                        (vec (conj qs (vec-normalize qi))))
         ;
           )))))

(defn mx->qs
  "Returns [qs- qs] the Q mx with cols as orthonormal vecs prodcued by applying Gram-Schmidt
   to A's cols.
   "
  [wid A]
  (as-> nil E
    (mx->cols wid A)
    (gram-schmidt-qs E)))

(defn QR-factorization->Q
  "Returns Q mx formed by Gram-Schmidt alg"
  [wid A]
  (as-> nil E
    (mx->cols wid A)
    (second (gram-schmidt-qs E))
    (cols->mx  E)))

(defn QR-factorization->R
  "Returns the R triangular mx for A = QR"
  [widA A]
  (let [widR     widA
        R        (make-mx widR widR nil)
        xs       (mx->cols widA A)
        [qs- qs] (mx->qs widA A)]
    (->
     (map-indexed (fn [i _]
                    (let [i-row (index->row i widR)
                          i-col (index->col i widR)
                          qi    (qs i-row)
                          xj    (xs i-col)
                          qi-   (qs- i-row)]
                      (cond
                        (= i-row i-col) (vec-norm qi-)
                        (< i-row i-col)  (inner-prod qi xj)
                        (> i-row i-col) 0))) R)
     vec)))

(defn back-substitution-xi
  "Returns the value of x ith"
  [R bi xs]
  (let [order (mx->order R)
        i-b   (dec (- order (count xs)))]
    (as-> nil E
      (reduce-kv (fn [acc i x]
                   (->>
                    (mx->el  i-b (+ i-b i 1)  order R)
                    (* x)
                    (+ acc))
                       ;
                   )0 xs)
      (- bi E)
      (/ E (mx->el  i-b i-b order R)))))

(defn back-substitution
  "Return vec x.
   Rx = b
  "
  ([widR R bs]
   (back-substitution widR R bs []))
  ([widR R bs xs]
   (cond
     (empty? bs) xs
     :else (recur widR
                  R
                  (vec (drop-last bs))
                  (vec (cons  (back-substitution-xi R (last bs) xs) xs))))))

(defn mx-inverse
  "Returns the inverse of A"
  ([A]
   (mx-inverse A (mx->order A)))
  ([A widA]
   (let [; [qs- qs] (mx->qs widA A)
         heiA     (/ (count A) widA)
         Q        (QR-factorization->Q widA A)
         Q'T      (mx-transpose widA Q)
         Q'T-cols (mx->cols heiA Q'T)
         R        (QR-factorization->R widA A)]
    ; (prn widA)
    ; (cprn-mx widA R)
    ; (cprn-mx widA Q)
    ; (cprn-mx widA Q'T)
     (as-> nil E
       (map-indexed (fn [idx q]
                    ; (prn (back-substitution widA R q))
                      (->
                       (back-substitution widA R q)
                    ;  reverse
                       vec)) Q'T-cols)
    ;  (do (prn E) E)
       (cols->mx E))
    ;
     )))

(comment

  (def A (rows->mx [[1 2 3]
                    [4 5 6]
                    [7 8 9]]))

  (gram-schmidt-qs (mx->cols (mx->order A) A))

  ;;;
  )



(comment

  (def A (rows->mx [[1 2 3]
                    [4 5 6]
                    [7 8 9]]))

  (gram-schmidt-qs (mx->cols  3  A))

  (mx-inverse A)

  (mx-invertible? A)

  (def a1 [-1 1 -1 1])
  (def a2 [-1 3 -1 3])
  (def a3 [1 3 5 7])

  (def A (cols->mx [a1 a2 a3]))
  (cprn-mx 3 A)
  (def qs (second (gram-schmidt-qs [a1 a2 a3])))
  (def Q (cols->mx  qs))
  (cprn-mx 3 Q)
  (cprn-mx 3 (mx-prod 4 3 (mx-transpose 3 Q) Q))

  (def ATA (mx-prod  4  3 (mx-transpose 3 A) A))

  (prn-mx 3 ATA)

  (mx-invertible? ATA)

  (boolean [])

  (mx-linearly-indep-cols? 3 A)

  (mx-singular? 3 A)


  ;;;
  )