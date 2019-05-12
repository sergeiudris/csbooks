(ns plants.ml.mnist
  (:require [plants.mx.core :refer :all]
            [clojure.repl :refer :all]
            [clojure.java.io :refer [file output-stream input-stream]]
            [clojure.pprint  :as pp]))

; http://yann.lecun.com/exdb/mnist/

(def image-size 28)

(def image-count-t60 60000)
(def image-count-t10 10000)

(def t10-images-file-size 7840016)
(def t60-images-file-size 47040016)

(def t10-labels-file-size 10008)
(def t60-labels-file-size 60008)


(def image-file-meta-bits 16)
(def labels-file-meta-bits 8)



(defn bytes->int [bytes]
  "Converts a byte array into an integer."
  (->>
   bytes
   (map (partial format "%02x"))
   (apply (partial str "0x"))
   read-string))

(defn file-szie
  "returns file byte length"
  [filename]
  (.length (file filename)))

(defn read-binary-file
  "returns bute-array , accepts :offset :limit"
  [filename & {:keys [offset limit] :or {offset 0  }  } ]
  (let [f   (file filename)
        lim (or limit (- (.length f) offset))
        buf (byte-array lim)]
    (prn lim offset)
    (with-open [in (input-stream  f)]
      (.skip in offset)
      (.read in buf 0 lim)
      buf
      ;
      )))

(defn read-int-from-file
  "returns the number represented by bytes in range [offset , + offset  limit]"
  [filename offset limit]
  (->   (read-binary-file  filename :offset offset :limit limit)
        bytes->int)
  )

(defn read-MNIST-image
  "returns vector of pixel values for an image at offset"
  [filename offset]
  (->>
   (read-binary-file filename
                     :offset (+ offset image-file-meta-bits)
                     :limit (Math/pow image-size 2))
   vec))

(defn read-MNIST-label
  "returns vector of pixel values for an image at offset"
  [filename offset]
  (->>
   (read-binary-file filename
                     :offset (+ offset labels-file-meta-bits)
                     :limit 1)
   vec))

(defn read-MNIST-image-nth
  "returns nth image from the file"
  [filename  n]
  (read-MNIST-image filename (* n (Math/pow image-size 2))))

(defn read-MNIST-label-nth
  "returns nth image from the file"
  [filename  n]
  (read-MNIST-label filename (* n 1)))

(defn prn-nth-image
  "prints nth image from file as matrix 28x28, using prnmx "
  [filename n]
  (prnmx (read-MNIST-image-nth filename n) image-size)
  nil)


(comment

  (count (read-binary-file "data/t10k-images-idx3-ubyte"))

  (as-> (read-binary-file "data/t10k-labels-idx1-ubyte") x
    (bytes x)
    (take 4 x))

  (int (bytes (take 4 (read-binary-file "data/t10k-images-idx3-ubyte"))))

  (subvec (read-binary-file "data/t10k-images-idx3-ubyte") 4 4)

  (def x (byte-array [(byte 0x43)
                      (byte 0x6c)
                      (byte 0x6f)
                      (byte 0x6a)
                      (byte 0x75)
                      (byte 0x72)
                      (byte 0x65)
                      (byte 0x21)]))

  (String. x)

  ; (clojure.main/repl :print pprint)

  (pp/pprint x)

  (apply str "0x" "00")

  (read-string (format "%02x" (byte 0x00)))

  (apply (partial str "0x") ["00" "02"])
  (apply (partial str "0x") ["asd"])
  (str "0x"  ["asd"])
  
  (file-szie "data/t10k-images-idx3-ubyte")
  (file-szie "data/train-images-idx3-ubyte")
  (file-szie "data/t10k-labels-idx1-ubyte")
  
  


  ;;;
  )


(comment

    ; number of iamges in t10
  (read-int-from-file "data/t10k-images-idx3-ubyte" 4 4)

    ; number of images rows
  (read-int-from-file "data/t10k-images-idx3-ubyte" 8 4)

    ; number of images rows
  (read-int-from-file "data/t10k-images-idx3-ubyte" 12 4)

    ; number of images in train
  (read-int-from-file "data/train-images-idx3-ubyte" 4 4)


  (count)

  (->
   (prnmx (read-MNIST-image "data/t10k-images-idx3-ubyte" 0) 28)
   nil?)

  (->
   (prnmx (read-MNIST-image-nth "data/t10k-images-idx3-ubyte" 0) 28)
   nil?)

  (prn-nth-image "data/t10k-images-idx3-ubyte" 0)

  (prn-nth-image "data/t10k-images-idx3-ubyte" 0)

  (prn-nth-image "data/t10k-images-idx3-ubyte" 1555)

  (read-MNIST-label "data/t10k-labels-idx1-ubyte" 0)


  (prn-nth-image "data/t10k-images-idx3-ubyte" 1555)
  (read-MNIST-label-nth "data/t10k-labels-idx1-ubyte" 1555)

  (prn-nth-image "data/t10k-images-idx3-ubyte" 1000)
  (read-MNIST-label-nth "data/t10k-labels-idx1-ubyte" 1000)




  ;;;
  )