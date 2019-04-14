(ns dev
  (:require [tools.nrepl]
            [tools.core]
            [tools.pedestal.server]
   
            [sandbox.main]
            [bigdata.main]
            [supweb.main]
            [casc.main]
            [wordcount.main]
   ;
            )

  ;
  )


(defn -main  [& args]
  (tools.nrepl/-main)
  (tools.pedestal.server/run-dev))