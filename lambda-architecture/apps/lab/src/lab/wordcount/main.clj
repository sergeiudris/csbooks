(ns lab.wordcount.main
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
   ;
            )
  (:import
   (lab.wordcount.java WordCount))
  ;
  )

(defn -main  [& args]
  (WordCount/main (into-array [
                               "/user/joe/wordcount/input"
                               (str "/user/joe/wordcount/" (first args))])))




(comment
  (clojure-version)
  ;
  )


(comment

  WordCount/main

  (defn word-count
    []
    (let [uri-base "hdfs://namenode:9820"
          uri-in   (str uri-base "/user/joe/wordcount/input")
          uri-out  (str uri-base "/user/joe/wordcount/output100")]
      (WordCount/main (into-array [uri-in uri-out]))))


  (try
    (word-count)
    (catch Exception e (str "caught exception: " (.getMessage e))))

  ; (WordCount/main (into-array ["hdfs://namenode:9820/user/joe/wordcount/input" " /user/joe/wordcount/output7"]))

  ; (.main WordCount)

  ;
  )