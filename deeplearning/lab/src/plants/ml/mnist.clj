(ns plants.ml.mnist
  (:require [plants.mx.core :refer :all]
            [clojure.repl :refer :all]
            [clojure.java.io :refer [file output-stream input-stream]]
            [clojure.pprint  :as pp]))

; http://yann.lecun.com/exdb/mnist/



(defn bytes->int [bytes]
  "Converts a byte array into an integer."
  (->>
   bytes
   (map (partial format "%02x"))
   (apply (partial str "0x"))
   read-string))

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





  ;;;
  )