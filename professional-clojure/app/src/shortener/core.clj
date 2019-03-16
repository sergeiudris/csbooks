(ns shortener.core
  (:require [clojure.repl :refer :all]
            [dev]
            [clojure.java.javadoc :refer [javadoc]]
            [clj-http.client :as client]))




(comment
  
  
  (:body (client/post  "http://0.0.0.0:8080/echo"  {:body "some body"}))
  
  (:body (client/post  "http://0.0.0.0:8080/echo"  {}))
  
  )