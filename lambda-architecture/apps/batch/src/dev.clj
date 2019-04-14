(ns dev
  (:require [tools.nrepl]
            [tools.core]
            [tools.pedestal.server]
   
            [sandbox.main]
            [bigdata.main]
            [supweb.main]
            [cas.main]
            [wordcount.main]
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