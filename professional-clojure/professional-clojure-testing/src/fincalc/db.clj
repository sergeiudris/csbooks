(ns fincalc.db
  (:require [clojure.java.jdbc :as sql]))

(def db-spec "postgresql://postgres:postgres@postgresdb:5432/fincalc")

(defn get-symbols [db-spec]
  (map :symbol (sql/query db-spec ["select * from stocks"])))

(defn get-symbol [db-spec sym]
  (map :symbol (sql/query db-spec ["select * from stocks where symbol = ?" sym])))

(defn add-symbol [db-spec sym]
  (sql/insert! db-spec :stocks {:symbol sym}))

(defn remove-symbol [db-spec sym]
  (sql/delete! db-spec :stocks ["symbol = ?" sym]))


(comment
  
  ; (def db db-spec)
  
  (sql/db-do-commands db-spec (sql/create-table-ddl :stocks [:symbol "varchar(10)"]))
  
  )