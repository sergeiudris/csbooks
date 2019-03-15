(ns chapter3.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [chapter3.core :as core]
            ))


(def routesv [
              (GET "/ch3" [] "Hello World!!!")
      (GET "/film" []
        (core/get-favorite-film))
              
              ])

(def app-routes (apply routes routesv) )

(def app
  (-> 
   app-routes
   (wrap-defaults api-defaults)
   wrap-json-response
   )
)

(comment
  api-defaults
  (+)
  
  (refresh)
  
  )