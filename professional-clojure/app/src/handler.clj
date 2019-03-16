(ns handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.handler]
            [chapter2.handler2]
            [chapter3.handler]
            [core]

            [clojure.tools.namespace.repl :refer [refresh]]))




(def app-routes
  (routes 
   chapter2.handler/app-routes 
   chapter2.handler2/app-routes
   chapter3.handler/app-routes
   (route/not-found "Not Found")
   )
  )


(def app
  (->
   app-routes
   wrap-json-response
  ;  core/wrap-json-response
   core/wrap-500-catchall
   (wrap-defaults api-defaults)
   ))

(comment
  api-defaults
  (+)

  (refresh))