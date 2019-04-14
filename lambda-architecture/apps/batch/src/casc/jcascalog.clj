(ns casc.jcascalog
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [app.playground :as pg]
            [clojure.pprint :refer [print-table] :as pp]
            [clojure.java.shell :as shell]
   ;
            )
  (:import 
   (jcascalog  Api Option Playground Subquery)
   
   (jcascalog.op Count  GT LT Multiply)
   (com.twitter.maple.tap StdoutTap)
  ;  (app.java Split Examples)
   (casc.java Examples Split )
   
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
  
  (compile 'jva.Examples1)
  
  *compile-path*
  
  (Examples/hello)
  
  (Examples/wordCount)
  
  (Examples/lessThanThirtyYearsOld)
  (Examples/lessThanThirtyYearsOldWithAge)
  
  (shell/sh "ls")
  
  (shell/sh "pwd")
  
  (shell/sh "echo 3")
  shell/*sh-dir*
  
  (shell/with-sh-dir )
  
  (shell/sh "lein compile")
  
  
  
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