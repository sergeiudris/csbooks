(ns chapter2.core
  (:require [clojure.repl :refer :all]
            [dev]
            [clojure.java.javadoc :refer [javadoc]]
            [clj-http.client :as client]
            ))



(def id-atom (atom 0))
(defn next-id [] (swap! id-atom inc))

(def tasks (atom (sorted-map)))

(defn get-tasks
  "Get all tasks on the to-do list"
  []
  @tasks
  )

(defn add-task
  "Add a task to the to-do list. Accept a string describing the task"
  [task]
  (swap! tasks assoc (next-id) task )
  )

(defn remove-task
  "Removes a task from the to-do list. Accepts the id of the task to remove"
  [task-id]
  (swap! tasks dissoc task-id)
  )





(comment
  (defn hi [] "hi")
  (+)
  (hi)

  (find-doc #"map.*parallel")

  (source pmap)
  (doc javadoc)
  (source javadoc)
  (javadoc Runtime)
  (dev/javadoc-print-url Runtime )
  
  
  (doc add-task)
  
  (add-task "Buy more tofu")
  (add-task "get up")
  (add-task "file taxes")
  
  (doc remove-task)
  
  (remove-task 3)
  
  (defn api-url [path] (str "http://0.0.0.0:8080" path )  )
  
  (api-url "/api/tasks" )
  
  (:body (client/get (api-url "/api/tasks") {:as :json}))
  (:body (client/get (api-url "/api/tasks1") {:as :json}))
  
  
  (:body (client/post  (api-url "/api/tasks")  {:form-params {:task "connect"} :as :json}))
  (:body (client/post  (api-url "/api/tasks")  {:form-params {:task "gather"} :as :json}))
  (:body (client/post  (api-url "/api/tasks")  {:form-params {:task "ppush"} :as :json}))
  
  
  
  (:body (client/delete  (api-url "/api/tasks/2")  {:as :json}))
  

  
  
  
  
  )
