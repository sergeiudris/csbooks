(ns chapter4.simple-api-handler-test
  (:require [clojure.test :refer :all]
            [peridot.core :refer :all]
            [chapter4.simple-api-handler :refer :all]))


(deftest test-app
  
  (testing "main route logged in user"
    (let [response (:response (-> (session app)
                                  (request "/login"
                                           :request-method :post
                                           :params {:user "Jeremy"})
                                  (request "/")))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "main route"
    (let [response (:response (-> (session app)
                                  (request "/")))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World")))))