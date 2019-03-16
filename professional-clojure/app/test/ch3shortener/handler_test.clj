(ns ch3shortener.handler-test
  (:require [clojure.repl :refer :all]
            [ch3shortener.handler :refer :all]
            [clojure.test :refer :all]
            [ch3shortener.storage.in-memory :refer [in-memory-storage]]
            [ch3shortener.storage :as st]
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


(comment
  
  (doc is)
  
  (is (= 2 2))
  
  )