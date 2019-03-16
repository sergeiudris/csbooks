(ns application-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [application :refer :all]
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



(comment


  (app (mock/request :get "/links/123"))
  
  
  (app (mock/request :get "/links/123/2"))
  
  
  

  )