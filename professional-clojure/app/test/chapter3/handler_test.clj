(ns chapter3.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]
            [clojure.set :refer [subset?]]
            [chapter3.handler :refer :all]
            ))

(deftest chapter3-test
  (testing "main route"
    (let [response (app (mock/request :get "/ch3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World!!!"))))
  (testing "echo route"
    (let [response (app (mock/request :post "/echo" "Echo!"))]
      (is (= 200 (:status response)))
      (is (= "Echo!" (:body response))))
    (let [response (app (mock/request :post "/echo" "Hello"))]
      (is (= 200 (:status response)))
      (is (= "Hello" (:body response))))
    (let [response (app (mock/request :post "/echo" "Goodbye"))]
      (is (= 200 (:status response)))
      (is (= "Goodbye" (:body response))))
    (let [response (app (mock/request :post "/echo"))]
      (is (= 400 (:status response)))))
  ; (testing "not-found route"
  ;   (let [response (app (mock/request :get "/invalid"))]
  ;     (is (= (:status response) 404))))
  )

(deftest catchall-test
  (testing "when a handler throws an exception"
    (let [response (app (mock/request :get "/trouble" ))]
      (testing "the status code is 500"
        (is (= 500 (:status response)))
        )
      (testing "and the body only contains the exception message"
        (is (= "Divide by zero" (:body response) ))
        )
      )
    
    )
  )

(deftest slurp-body-test
  (testing "when a handler requires a request body"
    (testing "and a body is provided"
      (let [response (body-echo-app (mock/request :post "/" "Echo!" )) ]
        (testing "the status code is 200"
          (is (= 200 (:status response)))
          (testing "with the request body in the response body"
            (is (= "Echo!" (:body response) ))
            )
          )
        )
      )
  (testing "and a body is not provided"
    (let [response (body-echo-app (mock/request :get "/") )]
      (testing "the status code is 400"
        (is (= 400 (:status response)))
        )
      )
    )
    )
  
  )

(deftest links-test
  (testing "the /ch3/links/:id edpoint"
    (testing "when an id is provied"
      (let [response (app (mock/request :get "/ch3/links/foo123"))]
        (testing "returns a 200"
          (is (= 200 (:status response)))
          (testing "with id in the body"
            (is (re-find #"foo123" (:body response)))))))
    (testing "when id is omitted"
      (let [response (app (mock/request :get "/ch3/links"))]
        (testing "returns a 404"
          ; (is (= 404 (:status response)))
          (is (= response nil))
          )))
    (testing "when the path is too long"
      (let [response (app (mock/request :get "/ch3/links/foo123/extra-segment"))]
        (testing "returns a 404"
          ; (is (= 404 (:status response)))
          (is (= response nil))
          )))))


(deftest json-test
  (testing "the /clojurefy endpoint"
    (testing "when provided with some valid JSON"
      (let [example-map {"hello" "json"}
            example-json (json/encode example-map)
            response (app (-> (mock/request :post "/clojurefy" example-json)
                              (mock/content-type "application/json")))]
        (testing "returns a 200"
          (is (= 200 (:status response)))
          (testing  "with a clojure map in the body"
            (is (= (str example-map) (:body response)))))))
    (testing "when provided with invalid json"
      (let [response (app (->
                           (mock/request :post "/clojurefy" ";!:")
                           (mock/content-type "application/json")))]
        (testing "returns 400"
          (is (= 400 (:status response))))))))


(deftest info-test
  (testing "the /info endpoint"
    (let [response (app (mock/request :get "/info"))]
      (testing "returns 200"
        (is (= 200 (:status response)))
        (testing "with a valid JSON body"
          (let [info (json/decode (:body response))]
            (testing "containing the exprected keys"
              (is (subset? #{"Java Version"} (set (keys info)))))))))))


(comment
  
  ; https://ring-clojure.github.io/ring-mock/ring.mock.request.html
  
  (mock/request :get "/ch3")
  
  (app (mock/request :get "/ch3"))
  
  (app (mock/request :get "/trouble"))
  
  
  (mock/request
   :post "/foo" "Hello, this request has a body")
  
  (def request-with-body (mock/request :post "/" "This is the request body"))
  
  (:body request-with-body)
  
  (slurp (:body request-with-body))
  
  (slurp (:body request-with-body))
  
  
  
  
  )