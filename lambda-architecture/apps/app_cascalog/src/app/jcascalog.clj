(ns app.jcascalog
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [app.playground :as pg]
            [clojure.pprint :refer [print-table] :as pp]
   ;
            )
  (:import 
   (jcascalog  Api Option Playground Subquery)
   
   (jcascalog.op Count  GT LT Multiply)
   (com.twitter.maple.tap StdoutTap)
   (app.java Split Examples)
   )
  )

(comment
  (clojure-version)
  (Playground/SENTENCE)
  java.lang.String
  
  Subquery
  
  jcascalog.Subquery
  
  pg/person
  
  (Examples/sentenceUniqueWords)
  
  (Examples/hello)
  
  (Examples/wordCount)
  
  (Examples/lessThanThirtyYearsOld)
  (Examples/lessThanThirtyYearsOldWithAge)
  
  
  (.execute jcascalog.Api)
  
  ;
  )


(comment
  
  
  (jcascalog.Subquery. "?word" "?count")
 
  (.execute Api (StdoutTap.)
           (as-> (Subquery. "?word" "?count") v
             (.predicate v Playground/SENTENCE "?sentence")
             (.predicate v (Split.) "?sentence" )
             (.out v "?word")
             (.predicate v (Count.) "?count")
             )
            )
      ; public static void wordCount () {Api.execute (new StdoutTap ()
      ;                                                   new Subquery ("?word", "?count")
      ;                                                   .predicate (Playground.SENTENCE, "?sentence")
      ;                                                   .predicate (new Split (), "?sentence") .out ("?word")
      ;                                                   .predicate (new Count (), "?count"));
      ;                                  }
  
  ;
  )