(ns chapter2.core
  (:require [clojure.repl :refer :all]
            [dev]
            [clojure.java.javadoc :refer [javadoc]]))

(defn hi [] "hi")

(comment
  (+)
  (hi)
  
  (find-doc #"map.*parallel")
  
  (source pmap)
  (doc javadoc)
  (source javadoc)
  (javadoc Runtime)
  (dev/javadoc-print-url Runtime)
  
  )
