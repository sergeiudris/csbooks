(ns dev
  (:require [tools.nrepl]
            [tools.core]
            [tools.pedestal.server]
   
            [lab.sandbox.main]
            [lab.bigdata.main]
            [lab.supweb.main]
            [lab.cascalog.main]
            [lab.wordcount.main]
   ;
            )

  (:import (jva.casc Examples ))
  
  ;
  )


(defn -main  [& args]
  (tools.nrepl/-main)
  (tools.pedestal.server/run-dev))


(comment
  
  (Examples/hello)
  
  )