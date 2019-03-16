; (ns ch3shortener.middleware
;   (:require [clojure.repl :refer :all]
;             )
;   (:import [java.io.InputStream] )
;   )
(ns ch3shortener.middleware
  (:import [java.io InputStream]))

(defn wrap-slurp-body
  "slurps BiteArrayInputStream into e.g. string"
  [handler]
  (fn [request]
    (if (instance? java.io.InputStream (:body request))
      (let [prepared-request (update request :body slurp)]
        (handler prepared-request))
      (handler request))))

