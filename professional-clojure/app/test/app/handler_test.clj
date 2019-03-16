(ns app.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [handler :refer :all]
            [cheshire.core :as json]
            [clojure.set :refer [subset?]]
            ))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World!!!"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))
  
  (testing "echo route"
    (let [response (app (mock/request :post "/echo" "Echo!" ))]
      (is (= 200 (:status response) ))
      (is (= "Echo!" (:body response)))
      )
    (let [response (app (mock/request :post "/echo" "Hello"))]
      (is (= 200 (:status response)))
      (is (= "Hello" (:body response))))
    (let [response (app (mock/request :post "/echo" "Goodbye"))]
      (is (= 200 (:status response)))
      (is (= "Goodbye" (:body response))))
    (let [response (app (mock/request :post "/echo")) ]
      (is (= 400 (:status response)))
      )
    )
    
  )

(deftest links-test
  (testing "the /ch3/links/:id edpoint"
    (testing "when an id is provied"
      (let [response (app (mock/request :get "/ch3/links/foo123" ))]
      (testing "returns a 200"
        (is (= 200 (:status response)))
        (testing "with id in the body"
          (is (re-find #"foo123" (:body response) ))
          )
        ))
      )
    (testing "when id is omitted"
      (let [response (app (mock/request :get "/ch3/links" ))]
        (testing "returns a 404"
          (is (= 404 (:status response) ))
          )
        )
      )
    (testing "when the path is too long"
      (let [response (app (mock/request :get "/ch3/links/foo123/extra-segment" ))]
        (testing "returns a 404"
          (is (= 404 (:status response)))
          )
        )
      )
    )
  
  )


(deftest json-test
  (testing "the /clojurefy endpoint"
    (testing "when provided with some valid JSON"
      (let [example-map {"hello" "json"}
            example-json (json/encode example-map)
            response (app (-> (mock/request :post "/clojurefy" example-json )
                              (mock/content-type "application/json")
                              ))
            ]
        (testing "returns a 200"
          (is (= 200 (:status response)))
          (testing  "with a clojure map in the body"
            (is (= (str example-map) (:body response)  )) 
            )
          )
        )
      )
    (testing "when provided with invalid json"
      (let [response (app (-> 
                           (mock/request :post "/clojurefy" ";!:")
                           (mock/content-type "application/json")
                           ))]
        (testing "returns 400"
          (is (= 400 (:status response)))
          )
        )
      )
    )
  )


(deftest info-test
  (testing "the /info endpoint"
    (let [response (app (mock/request :get "/info" ))]
      (testing "returns 200"
        (is (= 200 (:status response)))
        (testing "with a valid JSON body"
          (let [info (json/decode (:body response))]
            (testing "containing the exprected keys"
              (is (subset? #{"Java Version" } (set (keys info)) ))
              )
            )
          )
        )
      )
    )
  )


(comment


  (app (mock/request :get "/links/123"))
  
  
  (app (mock/request :get "/links/123/2"))
  
  
  

  )