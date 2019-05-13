(ns plants.ml.sdss
  (:require [plants.mx.core :refer :all]
            [clojure.repl :refer :all]
            [clojure.pprint :as pp]
            [puget.printer :as pug]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:import
   (org.apache.commons.io.input BOMInputStream)))


; https://www.kaggle.com/lucidlenn/sloan-digital-sky-survey

(defn read-column [filename column-index]
  (with-open [reader (io/reader filename)]
    (let [data (csv/read-csv reader)]
      ; (map #(nth % column-index) data) ; lazy
      (mapv #(nth % column-index) data)
      )))

(defn read-csv-file
  [filename]
  (with-open [reader (io/reader filename)]
    (let [data (doall (csv/read-csv reader))]
      data)))


(def sdss-filename "data/Skyserver_SQL2_27_2018 6_51_39 PM.csv")

(defn try-str->num
  "returns number or nil"
  [strin]
  (try (Float/parseFloat strin) (catch Exception e strin)))

(defn parse-numbers
  [data]
  (mapv #(mapv try-str->num %) data ))


(defn read-csv-data
  "reads and parses csv data"
  [filename]
  (-> (read-csv-file sdss-filename)
      parse-numbers))

(def SDSS-DATA-CSV-RAW (drop 1 (read-csv-file sdss-filename)))
(def SDSS-DATA-CSV (parse-numbers SDSS-DATA-CSV-RAW))

(comment

  (read-column sdss-filename 2)

  (with-open [reader (-> sdss-filename
                         io/input-stream
                        ;  BOMInputStream.
                         io/reader)]
    (doall (csv/read-csv reader)))


  (count (read-csv-file sdss-filename))

  (as-> nil x
    (nth  (read-csv-file sdss-filename) 9999)
    (update x 1 #(Float/parseFloat %)))

  (try-str->num "123a")

  (try-str->num "0.11")

  (time (-> (read-csv-file sdss-filename)
            parse-numbers
            count))

  (as-> nil x
    (read-csv-data sdss-filename)
    (nth x 5))

  (nth SDSS-DATA-CSV 9888)
  
  (count (drop 1 SDSS-DATA-CSV-RAW))

  ;;;
  )




(comment
  (type (nth (nth SDSS-DATA-CSV 1) 13))
  (nth SDSS-DATA-CSV-RAW 1)

  (nth SDSS-DATA-CSV 1)
  
  (count (nth SDSS-DATA-CSV 1))

  (try-str->num "STAR")
  (read-string "STAR")

  ;;;
  )

(defn keep-columns
  "returns vector of examples with slected column indices"
  [data idxs]
  (mapv  (fn [row]
           (->
            (keep-indexed #(when ((set idxs) %1) %2) row)
            vec))   data))


(def SDSS-SET (keep-columns SDSS-DATA-CSV [0 1 2 3 4 5 6 7 8 9 10 11 12 14 15 16 17] ) )
(def SDSS-LABELS (-> (keep-columns SDSS-DATA-CSV [13]) flatten vec ))


(def SDSS-TRAIN-SET (subvec  SDSS-SET 0 8000) )
(def SDSS-TEST-SET  (subvec  SDSS-SET 8000 10000))

(def SDSS-TRAIN-LABEL (subvec SDSS-LABELS 0 8000))
(def SDSS-TEST-LABEL (subvec  SDSS-LABELS 8000 10000))

(defn prn-nth
  [items labels i]
  (pug/cprint (nth items i))
  (pug/cprint (nth labels i)))

(comment

  (keep-columns [[1 2 3] [1 2 3]] [0 1])
  (first SDSS-DATA-CSV)
  (first SDSS-SET)
  (count SDSS-TRAIN-SET)
  (count SDSS-TEST-SET)
  (count SDSS-LABELS)

  (first SDSS-LABELS)

  ((set [0 1 2 3 4 5 6 7 8 9 10 11 13 14 15 16 17]) 12)

  (subvec SDSS-TRAIN-SET  0 3)

  (prn-nth SDSS-TRAIN-SET SDSS-TRAIN-LABEL 7999)

  ;;;
  )


(comment
  
  
  
  ;;;
  )

