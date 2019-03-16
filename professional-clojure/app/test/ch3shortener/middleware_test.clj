(ns ch3shortener.middleware-test
  (:require [clojure.repl :refer :all]
            [ch3shortener.middleware :refer :all]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]
            )
  )

(deftest wrap-slurp-body-test
  (let [body "this is a body"
        request (mock/request :post "/foo" body)
        expected-request (assoc request :body body ) 
        indentity-handler (wrap-slurp-body identity )
        
        ]
    
    (testing "when givena request with a ByteArrayInputStream body"
      (let [prepared-request (indentity-handler request ) ]
        
        (testing "the body is turned into a string"
          (is (= body (:body prepared-request)))
          (testing "and the rest of the request is unchanged"
            (is (= expected-request prepared-request))
            )
          )
        )
      )
    
    (testing "when given a request that has no body"
      (let [no-body (mock/request :get "/" ) ]
        (testing "there's no effect"
          (is (= no-body (indentity-handler no-body ) ))
          )
        )
      )
    
    (testing "applying the middleware a second time has no effect"
      (let [request (mock/request :post "/foo"  body )]
        (is (= expected-request 
              (->
               request
               indentity-handler
               indentity-handler
               ) 
               ))
        
        )
      )
    
    )
  )