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
