(ns main
  (:require [dev.nrepl]
            [dev.core]
            [plants.mx core colors pca prob num]
            [plants.ml linreg xor]
            [math core mx num prob]
            [ml pca]
            [examples pca]
            ; [plants.ml.mnist]

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