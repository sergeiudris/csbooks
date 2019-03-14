
(ns app
  (:require [clojure.repl :refer :all]
            [chapter1.core]
            [chapter2.core]
            [chapter2.core2]
            [chapter2.handler]
            [chapter2.handler2]
            
            [dev]
            ))

(def app chapter2.handler/app)


(defn -main []
  3
  )

(comment
  
  (+)
  (chapter1.core/hi)
  
  )