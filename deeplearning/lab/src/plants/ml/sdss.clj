(ns plants.ml.sdss
  (:require [plants.mx.core :refer :all]
            [clojure.repl :refer :all]
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

(def SDSS_DATA_RAW (read-csv-file sdss-filename))
(def SDSS_DATA (parse-numbers SDSS_DATA_RAW))

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

  (nth SDSS_DATA 9888)

  ;;;
  )




(comment
  (type (nth (nth SDSS_DATA 1) 13))
  (nth SDSS_DATA_RAW 1)

  (nth SDSS_DATA 1)

  (try-str->num "STAR")
  (read-string "STAR")

  ;;;
  )

(comment
  
  
  
  ;;;
  )