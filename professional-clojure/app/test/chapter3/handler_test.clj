(ns chapter3.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [chapter3.handler :refer :all]))

(deftest chapter3-test
  (testing "main route"
    (let [response (app (mock/request :get "/ch3"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World!!!"))))

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