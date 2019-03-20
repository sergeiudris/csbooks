(ns core.core
  (:require [clojure.repl :refer :all]
            [clojure.pprint :as pp]

            [core.little-schemer]
            [core.seasoned-schemer]
            [core.reasoned-schemer]
            [core.nrepl]
            ))


(defn -main []
  (core.nrepl/-main)
 )


(comment

  (+)

  (defn hi [] "hi")

  )