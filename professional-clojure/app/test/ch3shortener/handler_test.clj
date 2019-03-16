(ns ch3shortener.handler-test
  (:require [clojure.repl :refer :all]
            [ch3shortener.handler :refer :all]
            [clojure.test :refer :all]
            [ch3shortener.storage.in-memory :refer [in-memory-storage]]
            [ch3shortener.storage :as st]
            [ring.mock.request :as mock]
            [clojure.set :refer [subset?]]
            [cheshire.core :as json]
            ))

(deftest get-link-test
  (let [stg (in-memory-storage)
        id  "test"
        url "http://test.gov"
        ]
    (st/create-link stg id url)
    (testing "when the ID exists"
      (let [response (get-link stg id)]
        (testing "the result is 302"
          (is (= 302 (:status response)))
          (testing "with the expected URL in the Location header"
            (is (= url (get-in response [:headers "Location"] )))
            )
          )
        )
      )
    (testing "when the ID does not exist"
         (let [response (get-link stg "bogus")]
           (testing "the result is a 404"
             (is (= 404 (:status response) ) )
             )
           )
      )
    )
  )


(deftest create-link-test
  (let [stg (in-memory-storage)
        id "test"
        url "http://example.com"
        request (-> 
                 (mock/request :post "/links/test" url )
                 (update :body slurp)
                 )
        ]
    (testing "when ID does not exist"
      (let [response (create-link stg "test" request )]
        (testing "the result is 200"
          (is (= 200 (:status response)))
          
          (testing "with the expected body"
            (is (= "/links/test" (:body response)))
            )
          
          (testing "and the link is actually created"
            (is (= url (st/get-link stg "test" ) ))
            )
          
          )
        )
      )
    ;; so it means testing share context ? (at this point "test" already exists from previous assertion )
    (testing "when the ID does exist"
      (let [response (create-link stg "test" request)]
        (testing "the result is a 422"
          (is (= 422 (:status response )))
          )
        )
      
      )
    )
  )


(deftest update-link-test
  (let [stg (in-memory-storage)
        id "test"
        url "http://example.com"
        request (->
                 (mock/request :put "/links/test" url)
                 (update :body slurp))]
    
    (testing "when the ID does not exist"
      (let [response (update-link stg "test" request)]
        (testing "the result is a 404"
          (is (= 404 (:status response))))))
    
    (testing "when ID does exist"
      (st/create-link stg "test" url)
      (let [new-url "http://example.gov"
            request (assoc request :body new-url )
            response (update-link stg "test" request)]
        (testing "the result is 200"
          (is (= 200 (:status response)))

          (testing "with the expected body"
            (is (= "/links/test" (:body response))))

          (testing "and the link is actually created"
            (is (= new-url (st/get-link stg "test")))))))
    )
  
  )

(deftest delete-link-test
  (let [stg (in-memory-storage)
        id "test"
        url "http://example.com/foo"
        ]
    (testing "when the link exists"
      (st/create-link stg id url)
      (let [response (delete-link stg id )]
        (testing "the response is a 204"
          (is (= 204 (:status response) ))
          )
        (testing "the link is deleted"
          (is (nil? (st/get-link stg id)))
          )
        )
      )
    (testing "when the link does not exist"
      (let [response (delete-link stg "bogus")]
        (testing "the response is still 204"
          (is (= 204 (:status response)))
          )
        )
      )
    )
  
  )

(deftest list-links-test-my
    (let [stg (in-memory-storage)
          id "test"
          url "http://example.com/foo"
          ]
      (testing "contains a link")
      (st/create-link stg id url)
      (let [response ((list-links stg ) {} )]
        (is (subset? #{"test"} (->
                               (:body response)
                               json/decode
                               keys
                               set
                               ) ) )
        
        )
      )
  )


(comment
  
  (doc is)
  
  (is (= 2 2))
  
  )