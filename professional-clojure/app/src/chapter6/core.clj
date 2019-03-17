(ns chapter6.core
  (:require [datomic.api :as d]
            [clojure.pprint :as pp]
            [clojure.repl :refer :all]))

(def  db-uri "datomic:free://datomicdb:4334/tracker")

(d/create-database db-uri)

(def conn (d/connect db-uri))
(defn db [] (d/db conn))






(comment

(doc d/q)

  (d/q '[:find ?e :in $] db)

; first query!
  (map first
       (d/q '[:find ?repo
              :where [?e :repo/uri ?repo]]
            db))

  (map firstf
       (d/q '[:find ?ns
              :where
              [?e :clj/ns ?n]
              [?n :code/name ?ns]]
            db))

  (reduce (fn [agg [o d]]
            (update-in agg [o] (fnil conj []) d))
          {}
          (d/q '[:find ?op ?def
                 :where
                 [?e :clj/def ?d]
                 [?e :clj/defop ?op]
                 [?d :code/name ?def]]
               db))

  (+ 3 4)



  (pp/pprint (keys (ns-publics 'datomic.api)))

  (doc datomic.api/tempid)

  (d/tempid :db/user 12))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "I don't do a whole lot."))