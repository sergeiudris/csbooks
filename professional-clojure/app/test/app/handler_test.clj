(ns app.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [handler :refer :all]))

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
  (testing "the links/:id edpoint"
    (testing "when an id is provied"
      (let [response (app (mock/request :get "/links/foo123" ))]
      (testing "returns a 200"
        (is (= 200 (:status response)))
        (testing "with id in the body"
          (is (re-find #"foo123" (:body response) ))
          )
        ))
      )
    (testing "when id is omitted"
      (let [response (app (mock/request :get "/links" ))]
        (testing "returns a 404"
          (is (= 404 (:status response) ))
          )
        )
      )
    (testing "when the path is too long"
      (let [response (app (mock/request :get "/links/foo123/extra-segment" ))]
        (testing "returns a 404"
          (is (= 404 (:status response)))
          )
        )
      )
    )
  
  )



(comment


  (app (mock/request :get "/links/123"))
  
  
  (app (mock/request :get "/links/123/2"))
  
  
  

  )