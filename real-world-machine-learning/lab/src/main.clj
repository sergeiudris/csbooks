(ns main
  (:require [tools.nrepl]
            [tools.core]
            [tools.pedestal.server]
            [ml.core]
            [mxnet.ndarray]
            [mxnet.module]
            [mxnet.bert-qa.infer]
            [mxnet.bert-qa.ask]
   
   ;
            )

  ; (:import (jva.casc Examples ))
  
  ;
  )


(defn -dev  [& args]
  (tools.pedestal.server/run-dev)
  (tools.nrepl/-main)
  )

(defn -main  [& args]
  (tools.nrepl/-main)
  (tools.pedestal.server/run-dev)
  )

(comment
  
  
  ; (Examples/hello)
  -dev

  (System/getProperty "java.vm.version")
  (System/getProperty "java.version")
  (System/getProperty "java.specification.version")
  (clojure-version)
  (ml.core/hello)

  ;
  )