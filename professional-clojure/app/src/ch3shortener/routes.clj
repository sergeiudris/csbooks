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
            [ch3shortener.storage.in-memory :refer [in-memory-storage]]
            [clj-http.client :as client]
            )
  )



; (defroutes app-routes
;   (context "/ch3shortener" []
;     (GET "/" [] "Hello World")
;     (GET "/links/:id" [id] (str "The id is: " id))
;     )
;   )

(defn app-routes
  [stg]
  (routes
   (GET "/links/:id" [id] (handler/get-link stg id))
   (GET "/links" [id :as request] (handler/list-links stg))
   (POST "/links/:id" [id :as request] (handler/create-link stg id request) )
   (PUT "/links/:id" [id :as request] (handler/update-link stg id request))
   )
  )


(def app
  (let [stg (in-memory-storage)]
    (->
     (app-routes stg)
     (wrap-routes mw/wrap-slurp-body) ;; only use mw when a route matches
  ;  ring-json/wrap-json-response
     core/wrap-500-catchall
     (wrap-defaults api-defaults)
     )
    )
  )


(comment
  (+)
  (:body (client/post  "http://0.0.0.0:8080/links/3"  {:body "http://example.com/3"}))
  (:body (client/put  "http://0.0.0.0:8080/links/3"  {:body "http://example.com/3"}))
  
  
  
  
  )