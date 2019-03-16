
(ns application
  (:require [clojure.repl :refer :all]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [chapter1.core]
            [chapter2.core]
            [chapter2.core2]
            [chapter2.handler]
            [chapter2.handler2]
            [chapter3.handler]
            [ch3shortener.routes]
            [dev]
            ))


(def app
  (->
   (routes
    chapter2.handler/app
    chapter2.handler2/app
    chapter3.handler/app
    ch3shortener.routes/app
    (route/not-found "Not Found"))))



(defn -main []  ""  )

(comment
  
  (+)
  (chapter1.core/hi)
  
  )