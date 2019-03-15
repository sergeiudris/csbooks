(ns chapter3.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [chapter3.core :as core]
            [ring.util.response :as ring-response]
            ))

(def my404 
  (->
   (ring-response/response "Not Found")
   (ring-response/status 404)
   (ring-response/content-type "text/html")
   (ring-response/charset "utf-8")
   )
  )

(defn wrap-500-catchall
  "Wrap the given handler in a try/catch expression, returning a 500 response if any exceptions are caught"
  [handler]
  (fn [request]
    (try (handler request)
         (catch Exception e
           (->
            (ring-response/response (.getMessage e) )
            (ring-response/status 500)
            (ring-response/content-type "text/plain")
            (ring-response/charset "utf-8")
            )
           )
         )
    )
  )

(defn wrap-slurp-body
  "slurps BiteArrayInputStream into e.g. string"
  [handler]
  (fn [request]
    (if (instance? java.io.InputStream (:body request) )
      (let [prepared-request (update request :body slurp) ]
        (handler prepared-request)
        )
      (handler request)
      )
    )
  )

(defn body-echo-handler
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

(def body-echo-app
    (->
   body-echo-handler
   wrap-500-catchall
   wrap-slurp-body
   )
  )


(def routesv [
              (GET "/ch3" [] "Hello World!!!")
              (GET "/film" []
                (core/get-favorite-film)
                )
              (GET "/hw" []
                {:status 200
                 :headers {"Content-Type" "text/html; charset=utf-8"}
                 :body "Hello World"})
              
              (GET "/redirect" []
                {:status 302
                 :headers {"Location" "http://example.com"}
                 :body ""})
              
              (GET "/trouble" []
                (/ 1 0)
                )
              ])

(def app-routes (apply routes routesv) )

(def app
  (-> 
   app-routes
   (wrap-defaults api-defaults)
   wrap-json-response
   wrap-slurp-body
   wrap-500-catchall
   )
)

(comment
  api-defaults
  (+)
  
  (refresh)
  
  my404
  
  ; https://ring-clojure.github.io/ring/ring.util.response.html
  
  )