(ns setup
  (:require [clojure.java.io :as io]
            [clojure.pprint :refer (pprint)]
            [datomic.api :as d]
            [clojure.repl :refer :all]
            )
  (:import datomic.Util))


(def  db-uri "datomic:free://datomicdb:4334/tracker")

(d/create-database db-uri)
(def conn (d/connect db-uri))
(defn db [] (d/db conn))

(defn read-schema1 []
  (read-string (slurp "resources/schema1.edn"))
  )
(defn read-schema2 []
  (read-string (slurp "resources/schema2.edn")))

(defn read-data1 []
  (read-string (slurp "resources/data1.edn")))

(defn read-tx-data [path-to-file]
  (read-string (slurp path-to-file)))

(def schema1- (io/resource "schema1.edn"))
(defn read-txs
  [tx-resource]
  (with-open [tf (io/reader tx-resource)]
    (Util/readAll tf)))

(defn transact-all
  ([conn txs]
   (transact-all conn txs nil))
  ([conn txs res]
   (if (seq txs)
     (transact-all conn (rest txs) @(d/transact conn (first txs)))
     res)))

(defn initialize-db
  "Creates db, connects, transacts schema and example data, returns conn."
  []
  (d/create-database db-uri)
  (let [conn (d/connect db-uri)]
    (transact-all conn [(read-tx-data "resources/schema1.edn")])
    (transact-all conn [(read-tx-data "resources/schema2.edn")])
    (transact-all conn [(read-tx-data "resources/data1.edn")])
    ; (transact-all conn (read-txs schema))
    ; (transact-all conn (read-txs example-data))
    conn))

(defonce conn nil)

(defn go
  []
  (alter-var-root #'conn (constantly (initialize-db))))

(defn stop
  []
  (alter-var-root #'conn
                  (fn [c] (when c (d/release c)))))


(comment
  
  (go)
  
  
  (d/create-database db-uri)
  (def conn (d/connect db-uri))
  (defn db [] (d/db conn))
  
  (d/transact conn   (read-tx-data "resources/schema1.edn"))
  
  (d/transact conn schema1)
  (d/transact conn schema2)
  (d/transact conn data1)

  (db)
  
  (-> 
   (read-schema1)
   (nth 10)
   )

  (read-txs schema1-)

  (doc d/transact)
  (doc d/attribute)

  (doc io/resource)

  (d/attribute (db) :task/description)
  (d/attribute (db) :task/title)


  (pp/pprint (keys (ns-publics 'datomic.api)))

  (doc datomic.api/tempid)

  (d/tempid :db/user 12))

