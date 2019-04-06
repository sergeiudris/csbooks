(ns app.hadoop  
  (:require [clojure.repl :refer :all]
            [cascalog.api :as cs]
            ; [cascalog.playground :as csp]
            ; [com.backtype.hadoop.pail]
            ; [org.apache.hadoop/hadoop-core]
   ;
   )
  (:import [com.backtype.hadoop.pail Pail])
  )


(comment
  (doc doc)
  (use 'cascalog.api)
  (use 'cascalog.playground)
  
  (def pail (Pail/create "/tmp/mypail") )
  (def os (.openWrite pail) )
  (.writeObject os (bytes (byte-array [1 2 3] )) )
  (.writeObject os (bytes (byte-array [1 2 3 4])))
  (.writeObject os (bytes (byte-array [1 2 3 4 5])))
  (.close os)
  
  (defn simpleIO
    []
    (let
     [pail (Pail/create "/tmp/mypail2")
      os   (.openWrite pail)]
      (.writeObject os (bytes (byte-array [1 2 3])))
      (.writeObject os (bytes (byte-array [1 2 3 4])))
      (.writeObject os (bytes (byte-array [1 2 3 4 5])))
      (.close os)
      os
      ))
  
  (def os (simpleIO) )
  
  
  
  

  (.getName Pail)
  ; (.create Pail)
  
  (doc bytes)
  (doc byte-array)
  
  
  (bytes (byte-array [1 2 3] ))
  
  sentence
  ;
  )