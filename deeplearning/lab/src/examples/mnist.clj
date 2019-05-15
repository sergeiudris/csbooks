(ns examples.mnist
  (:require [clojure.repl :refer :all]
            [io.print :refer [cprn]]
            [math.mx :refer :all]
            [clojure.java.io :refer [file output-stream input-stream] :as io]))

; http://yann.lecun.com/exdb/mnist/

(defn read-binary-file
  "Returns bute-array , accepts :offset :limit"
  [filename & {:keys [offset limit]
               :or   {offset 0}}]
  (let [f   (file filename)
        lim (or limit (- (.length f) offset))
        buf (byte-array lim)]
    ; (prn lim offset)
    (with-open [in (input-stream  f)]
      (.skip in offset)
      (.read in buf 0 lim)
      buf
      ;
      )))

(defn read-file-to-vec
  "Returns vector with file bytes (ints 8)"
  [filename offset]
  (->
   (if (.exists (io/as-file  filename))
     (read-binary-file filename
                       :offset  offset)
     nil)
   vec))

(defn bytes->int [bytes]
  "Converts a byte array into an integer."
  (->>
   bytes
   (map (partial format "%02x"))
   (apply (partial str "0x"))
   read-string))

(defn file-szie
  "Returns file byte length"
  [filename]
  (.length (file filename)))

(def t10-iamges-filename "data/t10k-images-idx3-ubyte")
(def t10-labels-filename "data/t10k-labels-idx1-ubyte")
(def t60-iamges-filename "data/train-images-idx3-ubyte")
(def t60-labels-filename "data/train-labels-idx1-ubyte")

(def image-size 28)
(def image-size-squared (* 28 28))


(def image-count-t60 60000)
(def image-count-t10 10000)

(def t10-images-file-size 7840016)
(def t60-images-file-size 47040016)

(def t10-labels-file-size 10008)
(def t60-labels-file-size 60008)

(def image-file-meta-bits 16)
(def labels-file-meta-bits 8)

(def  T10-IMAGES  (read-file-to-vec t10-iamges-filename  image-file-meta-bits))
(def  T60-IMAGES  (read-file-to-vec t60-iamges-filename  image-file-meta-bits))
(def  T10-LABELS  (read-file-to-vec t10-labels-filename  labels-file-meta-bits))
(def  T60-LABELS  (read-file-to-vec t60-labels-filename  labels-file-meta-bits))


(defn nth-image
  "Returns nth image from a dataset"
  [i v]
  (subvec v (* i image-size-squared) (+ (* i image-size-squared) image-size-squared)))


(defn prn-image
  "Prints nth image from a dataset"
  [n v]
  (->>
   (nth-image (dec n) v)
   (prn-mx image-size)))

(defn nth-label
  "Returns nth label from a dataset"
  [i v]
  (v i))

(defn prn-nth
  "Prints nth image nad label from a dataset"
  [n images labels]
  (prn-image  n images)
  (cprn {:label (nth-label (dec n) labels)}))

(comment
  
  (prn-nth 2 T10-IMAGES T10-LABELS )
  (prn-nth 9123 T10-IMAGES T10-LABELS)
  
  
  
  ;;;
  )

