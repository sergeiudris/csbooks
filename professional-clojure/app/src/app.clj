
(ns app
  (:require [clojure.repl :refer :all]
            [chapter1.core]
            [chapter2.core]
            [chapter2.core2]
            [chapter2.handler]
            [chapter2.handler2]
            [handler]
            [dev]
            ))

(def app handler/app)


(defn -main []
  3
  )

(comment
  
  (+)
  (chapter1.core/hi)
  
  )