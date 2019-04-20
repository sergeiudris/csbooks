(ns dev
  (:require [tools.nrepl]
            [tools.core]
            [tools.pedestal.server]
   
            [lab.pail.main]
            [lab.bigdata.main]
            [lab.supweb.main]
            [lab.cascalog.main]
            [lab.wordcount.main]
            [lab.virgil.virgil]
   
   ;
            )

  ; (:import (jva.casc Examples ))
  
  ;
  )


(defn -main  [& args]
  (tools.nrepl/-main)
  (tools.pedestal.server/run-dev))


(comment
  
  ; (Examples/hello)
  
  )