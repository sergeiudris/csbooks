(ns app.jcascalog
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
   ;
            )
  (:import 
   (jcascalog  Api Option Playground Subquery)
   (jcascalog.op Count  GT LT Multiply)
   (com.twitter.maple.tap StdoutTap)
   (app.java Split)
   )
  )

(comment
  (clojure-version)
  (Playground/SENTENCE)
  java.lang.String
  
  Subquery
  
  (.execute jcascalog.Api)
  
  ;
  )


(comment
 
  (.execute Api (StdoutTap.)
           (as-> (Subquery. "?word" "?count") v
             (.predicate v Playground/SENTENCE "?sentence")
             (.predicate v (Split.) "?sentence" )
             (.out v "?word")
             (.predicate v (Count.) "?count")
             )
            )
  
  
  ;
  )