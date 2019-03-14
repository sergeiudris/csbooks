(ns chapter2.core2
  (:require [clojure.repl :refer :all]
            [dev]
            [clojure.java.javadoc :refer [javadoc]]
            [clj-http.client :as client]
            [clojure.tools.namespace.repl :refer [refresh]]
            ))



(def id-atom (atom 0))
(defn next-id [] (swap! id-atom inc))

(def tasks (atom (sorted-map)))

(defrecord Task [task creation-time] )

(defn now 
  "returns java.util.Date object representing the current time"
  []
  (new java.util.Date)
  )

(defn get-task-lists
  "Get the names of all created task lists"
  []
  (keys @tasks)
  )


(defn get-tasks
  "Get all tasks on the to-do list"
  [list-name]
  (get @tasks list-name)
  )


(defn add-task
  "Add a task to the specified to-do list. Accepts the name of the list and a string describing the task"
  [list-name task]
  (swap! tasks assoc-in [list-name (next-id)] (Task. task (now)) )
  )

(defn remove-task
  "Removes a task from the specified to-do list. Accepts the name of the list and the id of the task to remove"
  [list-name task-id]
  (swap! tasks update-in [list-name]  dissoc task-id)
  )

(defn remove-list 
  "Delete an entire list of tasks at a time. Accepts the name of the list ot delete"
  [list-name]
  (swap! tasks dissoc list-name)
  )


(comment
  (refresh)
  
  )


(comment
  
  (add-task "File Taxes.")
  
  
  (defn api-url [path] (str "http://0.0.0.0:8080/api2" path))

  (:body (client/get (api-url "/tasks/chores") {:as :json}))


  (:body (client/post  (api-url "/tasks/chores")  {:form-params {:task "take out the trash"} :as :json}))
  (:body (client/post  (api-url "/tasks/chores")  {:form-params {:task "mow the lawn"} :as :json}))
  
  
  
  )
