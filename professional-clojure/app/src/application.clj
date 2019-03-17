
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
            [chapter4.core]
            [ch3shortener.routes]
            [ch3shortener.storage]
            [ch3shortener.storage.in-memory]
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