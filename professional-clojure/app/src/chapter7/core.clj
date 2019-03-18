(ns chapter7.core
  (:require [clojure.repl :refer :all])
  )



(comment
  
  (def project1-bad
    {:title "Build a task tracking solution"
     :team '("Jeremy" "Justin" "Michael" "Nick" "Timothy")
     :stories '({:id "aaa"
                 :title "Build a ClojureScript front end"
                 :status "Ready"}
                {:id "bbb"
                 :title "Build backend services"
                 :status "Done"}
                {:id "ccc"
                 :title "Tune performance"
                 :status "In Progress"})})
  
  (def project1-better
    {:title "Build a task tracking solution"
     :team #{"Jeremy" "Justin" "Michael" "Nick" "Timothy"}
     :stories {"aaa" {:title "Build a ClojureScript front end"
                      :status "Ready"}
               "bbb" {:title "Build backend services"
                      :status "Done"}
               "ccc" {:title "Tune performance"
                      :status "In Progress"}}})
  
  
  )