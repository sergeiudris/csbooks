(ns ch3shortener.application-test
  (:require [clojure.repl :refer :all]
            [ch3shortener.handler :refer :all]
            [clojure.test :refer :all]
            [ch3shortener.storage.in-memory :refer [in-memory-storage]]
            [ch3shortener.storage :as st]
            [ring.mock.request :as mock]
            [clojure.set :refer [subset?]]
            [cheshire.core :as json]
            [ch3shortener.routes :refer [app]]
            
            ))

(deftest test-app
  (let [url "http://example.com/post"
        id "test"
        path (str "/links/" id)]
    (testing "creating a link"
      (let [response (app (mock/request :post path url))]
        (is (= 200 (:status response)))
        (is (= path (:body response)))))
    (testing "visiting a link"
      (testing "when the link exists"
        (let [response (app (mock/request :get path))]
          (testing "returns a 302"
            (is (= 302 (:status response)))
            (testing "with the correct location"
              (is (= url (get-in response [:headers "Location"])))))))
      
      (testing "when the link does not exist"
        (let [response (app (mock/request :get "/links/nothing"))]
          (testing "returns a 404"
            (is (= 404 (:status response))))))
      )
    )
  
  
  )