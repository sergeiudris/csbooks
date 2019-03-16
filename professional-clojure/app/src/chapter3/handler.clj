(ns chapter3.handler
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


(defn echo-body-handler
  [request]
  (if-let [body (:body request)]
    (-> (ring-response/response body)
        (ring-response/content-type "text/plain")
        (ring-response/charset "utf-8")
        )
    (-> 
     (ring-response/response "You must submit a body with your request!")
     (ring-response/status 400)
     )
    )
  )

(defn echo 
  [body]
  (if (not-empty body )
    (->
     (ring-response/response body)
     (ring-response/content-type "text/plain")
     (ring-response/charset "utf-8")
     )
    (->
     (ring-response/response "you must submit a body with your request!")
     (ring-response/status 400)
     )
    )
  )

(def body-echo-app
    (->
   echo-body-handler
   core/wrap-500-catchall
   core/wrap-slurp-body
   )
  )


(def json-routes
  (->
   (routes
    (POST "/clojurefy" []  (ring-json/wrap-json-body core/handle-clojurefy)))
   ))

(def body-routes 
  (-> 
   (routes 
    (ANY "/echo" [:as {body :body}] (echo body))
    )
   (wrap-routes  core/wrap-slurp-body)
   )
  )

(def non-body-routes
  (->
   (routes
    (GET "/ch3" [] "Hello World!!!")
    (GET "/film" []
      (ch3-core/get-favorite-film))
    (GET "/hw" []
      {:status 200
       :headers {"Content-Type" "text/html; charset=utf-8"}
       :body "Hello World"})

    (GET "/redirect" []
      {:status 302
       :headers {"Location" "http://example.com"}
       :body ""})

    (GET "/trouble" []
      (/ 1 0))
    (GET "/ch3/links/:id" [id] (str "ch3: The id is: " id))
    (GET "/info" [] core/handle-info )
    )
   )
  )

(def app-routes 
  (routes json-routes body-routes non-body-routes )
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
  api-defaults
  (+)
  
  (refresh)
  
  (doc not-empty)
  
  (doc wrap-routes)
  
  *clojure-version*
  
  my404
  
  json/decode
  
  ; https://ring-clojure.github.io/ring/ring.util.response.html
  
  )