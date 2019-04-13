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

(defn -main  [& args]
  ; (WordCount/main (into-array ["/user/joe/wordcount/input" "/user/joe/wordcount/output101"]))
  (shared.nrepl/-main)
  (shared.pedestal.server/run-dev)
  )

