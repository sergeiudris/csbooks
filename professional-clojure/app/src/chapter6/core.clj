(ns chapter6.core
  (:require [datomic.api :as d]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [clojure.repl :refer :all]
            [setup :refer [db]]
            )
  (:import datomic.Util)
  )


  (setup/go)


(comment

  (setup/go)
  
  (d/basis-t (db))
  
  (d/t->tx (d/basis-t (db)))
  
  (first (d/datoms (db) :aevt  ))
  
  (first (d/datoms (db) :aevt :db/doc :task/title ))
  
  (def entid (d/entid (db) :task/title))
  
  (d/ident (db) entid)
  
  
  
  (def jane (d/entity (db) [:user/login "janed"]))
  
  (keys jane)
  
  (:user/login jane)
  
  (vals jane)
  
  (type jane)
  
  (into {} jane)
  
  (get-in jane [:user/account :account/type])
  
  
  
  
  

  
  )

