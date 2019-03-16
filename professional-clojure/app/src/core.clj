(ns core
  (:require    [clojure.repl :refer :all]
              ;  [compojure.core :refer :all]
              ;  [compojure.route :as route]
               [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
               [ring.middleware.keyword-params :refer [wrap-keyword-params]]
              ;  [ring.middleware.json :refer [wrap-json-response]]
               [clojure.tools.namespace.repl :refer [refresh]]
               
               [ring.util.response :as ring-response]
               [cheshire.core :as json]
               ))


(def my404
  (->
   (ring-response/response "Not Found")
   (ring-response/status 404)
   (ring-response/content-type "text/html")
   (ring-response/charset "utf-8")))

(defn wrap-500-catchall
  "Wrap the given handler in a try/catch expression, returning a 500 response if any exceptions are caught"
  [handler]
  (fn [request]
    (try (handler request)
         (catch Exception e
           (->
            (ring-response/response (.getMessage e))
            (ring-response/status 500)
            (ring-response/content-type "text/plain")
            (ring-response/charset "utf-8"))))))

(defn wrap-slurp-body
  "slurps BiteArrayInputStream into e.g. string"
  [handler]
  (fn [request]
    (if (instance? java.io.InputStream (:body request))
      (let [prepared-request (update request :body slurp)]
        (handler prepared-request))
      (handler request))))


(defn wrap-json
  [handler]
  (fn [request]
    (if-let [prepd-request (try (update request :body json/decode )
                                (catch com.fasterxml.jackson.core.JsonParseException e nil)
                                )]
      (handler prepd-request)
      (->
       (ring-response/response "Sorry, that's not JSON" )
       (ring-response/status 400)
       )
      )
    
    )
  )

(defn handle-clojurefy
  [request]
  (->
   (:body request)
   str
   ring-response/response
   (ring-response/content-type "application/edn")
   )
  )

(defn wrap-json-response 
  [handler]
  (fn [request]
    (->
     (handler request)
     (update :body json/encode)
     (ring-response/content-type "application/json")
     )
    )
  )


(defn handle-info
  [handler]
  (->
   (fn [_]
     (-> 
      {"Java Version" (System/getProperty "java.version")
       "OS Name" (System/getProperty "os.name")
       "OS Version" (System/getProperty "os.version")
       "Clojure Version" *clojure-version*
       }
      ring-response/response
      )
     )
   wrap-json-response
   )
  )