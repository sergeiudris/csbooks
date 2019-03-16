(ns ch3shortener.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer :all]
            [ring.util.response :as ring-response]
            [ring.middleware.json :refer [wrap-json-response]]
            [core]
            [ch3shortener.handler :as handler]
            [ch3shortener.middleware :as mw]
            )
  )

(defroutes app-routes
  (context "/ch3shortener" []
    (GET "/" [] "Hello World")
    (GET "/links/:id" [id] (str "The id is: " id))
    )
  )


(def app
  (->
   app-routes
  ;  ring-json/wrap-json-response
   core/wrap-500-catchall
   (wrap-defaults api-defaults)))


(comment
  (+)
  
  )