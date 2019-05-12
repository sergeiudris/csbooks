(ns main
  (:require [dev.nrepl]
            [dev.core]
            [plants.mx.core]
            [plants.mx.colors]
            [plants.mx.pca]
            [plants.mx.prob]
            [plants.mx.num]
            [plants.ml.linreg]
            [plants.ml.xor]
            [plants.ml.mnist]
            [deeplearning.core]

   ;
            )
  ;
  )


(defn -dev  [& args]
  (dev.nrepl/-main)
  )

(defn -main  [& args]
  (dev.nrepl/-main)
  )

(comment
  
  ; (Examples/hello)
  {:hello "world"}

  (System/getProperty "java.vm.version")
  (System/getProperty "java.version")
  (System/getProperty "java.specification.version")
  (clojure-version)

  
  ;
  )