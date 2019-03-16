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
    (app (mock/request :delete path url))
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

(deftest link-updating-test
  (let [id "put"
        url "http://example.com/putTest"
        path (str "/links/" id)]
    (app (mock/request :delete path url))
    (testing "when the link does not exist"
      (let [response (app (mock/request :put path url))]
        (testing "the response is a 404"
          (is (= 404 (:status response))))))
    (testing "when the link does exist"
      (app (mock/request :post path "http://example.post"))
      (let [response (app (mock/request :put path url))]
        (testing "the response is a 200"
          (is (= 200 (:status response)))))))
  )


(deftest  delete-link-test
  (let [id "thing"
        url "http://example.com/thing"
        path (str "/links/" id)]
    
    (testing "when the link doesn't exist"
      (let [response (app (mock/request :delete path))]
        (testing "the result is a 204"
          (is (= 204 (:status response))))))
    
    (testing "when the link does exist"
      (app (mock/request :post path url))
      
      (let [response (app (mock/request :delete path))]
        (testing "the response is still a 204"
          (is (= 204 (:status response)))
          (testing "and the link is now a 404"
            (is (= 404 (:status (app (mock/request :get path))))))))))
  )

(deftest list-links-test
 ;; first make sure there are some links to list
  (let [id-urls {"a" "http://example.com/a"
                 "b" "http://example.com/b"
                 "c" "http://example.com/c"}]
    (doseq [[id url] id-urls]
      (app (mock/request :post (str "/links/" id) url)))
    (let [response (app (mock/request :get "/links"))
          parsed-body (json/decode (:body response))]
      (testing "the response is a 200"
        (is (= 200 (:status response)))
        (testing "and the decoded body contains all of the links we added"
          (is (= id-urls
                 (select-keys parsed-body ["a" "b" "c"])))))))
  )