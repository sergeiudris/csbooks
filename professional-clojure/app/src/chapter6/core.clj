(ns chapter6.core
  (:require [datomic.api :as d]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [clojure.repl :refer :all]
            [setup :refer [db conn]]
            [clojure.pprint :refer (pprint)]
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
  
  (d/touch (:user/account jane))
  
  (d/entity (db) (:user/account jane))
  
  (doc d/entity)
  
  (d/q '[:find ?login 
         :in $ ?email
         :where [?user :user/email ?email]
         [?user :user/login ?login]]
       (db) "everywoman123@gmail.com")
  
  
  (d/q '[:find ?login :in $ [?email ...]
         :where [?user :user/email ?email]
         [?user :user/login ?login]]
       (db)
       ["everywoman123@gmail.com" "john.doe@nowhere.com" "postmaster@google.com"])
  
  
  (d/q '[:find (pull ?user [:user/login
                            {:user/account [{:account/type [:db/ident]}]}])
         :in $ ?email
         :where [?user :user/email ?email]] 
       (db) "everywoman123@gmail.com")
  
  (pprint @(d/transact conn [{:db/id (d/tempid :db.part/user)
                              :task/title "Hello world"}]))
  
  
  (def db1 (:db-after @(d/transact conn [{:db/id (d/tempid :db.part/user)
                                          :task/issue-id "Hello"}])))
  
  
  (def db2 (:db-after @(d/transact conn [{:db/id (d/tempid :db.part/user)
                                          :task/issue-id "Hello"
                                          :task/description "First description"}])))
  
  (def db3 (:db-after @(d/transact conn [{:db/id (d/tempid :db.part/user)
                                          :task/issue-id "Hello"
                                          :task/description "Second description"}])))
  
  (def now-db (d/db conn))
  
  (:task/description (d/entity now-db [:task/issue-id "Hello"]))

  (:task/description (d/entity (d/as-of now-db (d/basis-t db2))
                               [:task/issue-id "Hello"]))
  
  
  (def description-query '[:find ?i . :in $ ?desc
                           :where [?i :task/description ?desc]])
  
  (d/q description-query now-db "Second description")
  
  (d/q description-query (d/as-of now-db (d/basis-t db2)) "First description")
  
  (def future-db (-> now-db
                     (d/with [{:db/id (d/tempid :db.part/user)
                               :task/issue-id "Hello"
                               :task/description "Third description"}])
                     :db-after
                     (d/with [{:db/id (d/tempid :db.part/user)
                               :task/issue-id "Hello"
                               :task/title "Hello world"}])
                     :db-after))
  
   (d/touch (d/entity future-db [:task/issue-id "Hello"]))
  
  )

