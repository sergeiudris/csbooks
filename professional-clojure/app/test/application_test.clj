(ns application-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [application :refer :all]
            [cheshire.core :as json]
            [clojure.set :refer [subset?]]
            ))

(deftest test-app

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))
    
  )



(comment


  (app (mock/request :get "/links/123"))
  
  
  (app (mock/request :get "/links/123/2"))
  
  
  

  )