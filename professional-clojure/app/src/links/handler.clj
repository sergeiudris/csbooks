(ns links.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :as ring-json]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer :all]
            [chapter3.core :as ch3-core]
            [ring.util.response :as ring-response]
            [cheshire.core :as json]
            [core]
            ))



(def link-routes
  (->
   (routes
    (GET "/links/:id" [id] (str "The id is: " id))
    )
   )
  )

(def app-routes 
  (routes link-routes  )
  )

(def app
  (-> 
   app-routes
   ring-json/wrap-json-response
   core/wrap-500-catchall
   (wrap-defaults api-defaults)
   )
)

(comment

  
  
  )