(ns ch3shortener.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :as ring-json]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer :all]
            [ring.util.response :as ring-response]
            [core]
            ))




(comment

  
  
  )