(ns handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.handler]
            [chapter2.handler2]
            [chapter3.handler]
            [ch3shortener.handler]
            [core]
            [clojure.tools.namespace.repl :refer [refresh]]))




(def app
  (->
   (routes
    chapter2.handler/app
    chapter2.handler2/app
    chapter3.handler/app
    ch3shortener.handler/app
    (route/not-found "Not Found")
    )
   )
  )

(comment
  api-defaults
  (+)

  (refresh)
  
  )