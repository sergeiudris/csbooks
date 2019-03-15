(ns handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.handler]
            [chapter2.handler2]
            [chapter3.handler]
            

            [clojure.tools.namespace.repl :refer [refresh]]))


(def app-routes
  (apply routes (concat
                 chapter2.handler/routesv
                 chapter2.handler2/routesv
                 chapter3.handler/routesv
                 [(route/not-found "Not Found")])))

(def app
  (->
   app-routes
   chapter3.handler/wrap-500-catchall
   (wrap-defaults api-defaults)
   wrap-json-response))

(comment
  api-defaults
  (+)

  (refresh))