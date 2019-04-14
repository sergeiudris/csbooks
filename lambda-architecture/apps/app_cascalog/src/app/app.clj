(ns app.app
  (:gen-class)
  (:require [shared.nrepl]
            [shared.core]
            [app.cascalog]
            [app.jcascalog]
            [app.wordcount]
            [shared.pedestal.server])
  (:import 
   (app.java WordCount)
   )
  )

(defn -dev [& args]
  (shared.nrepl/-main)
  (shared.pedestal.server/run-dev)
  )

(defn -main  [& args]
  (WordCount/main (into-array ["/user/joe/wordcount/input" "/user/joe/wordcount/output102"]))
  )

