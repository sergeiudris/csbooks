(ns plants.mx.pca
  (:require [plants.mx.core :refer :all :as plants]))

; principal component analysis (PCA)

(defn decode
  "returns the decoded colletion of points"
  [D v]
  (let [len (count v)]
    (multiply D v len len )))

(defn encode
  "returns the encoded vector x"
  [D v]
  (let [len (count v)]
    (multiply (transpose D) v len len)))

(defn pca-reconstruction
  "returns the vector after (decode (encode v))"
  [D v]
  (let [len (count v)]
    (multiply
     (multiply D (transpose D) len len)
     v len len)))


(comment
  
  (transpose [1 2 -1] 3)
  
  ;;;
  )