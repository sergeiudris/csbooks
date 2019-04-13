(ns app.wordcount
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
   ;
            )
  (:import
   (app.java WordCount)
   )
  )

(comment
  (clojure-version)
  ;
  )


(comment

  WordCount/main
  
  (defn word-count
    []
    (let [uri-base "hdfs://namenode:9820"
          uri-in (str uri-base "/user/joe/wordcount/input" )
          uri-out (str uri-base "/user/joe/wordcount/output8")
          ]
      (WordCount/main (into-array [uri-in uri-out]))
      )
    )
  

  (try
    (word-count)
    (catch Exception e (str "caught exception: " (.getMessage e))))
  
  ; (WordCount/main (into-array ["hdfs://namenode:9820/user/joe/wordcount/input" " /user/joe/wordcount/output7"]))
  
  ; (.main WordCount)
  
  ;
  )