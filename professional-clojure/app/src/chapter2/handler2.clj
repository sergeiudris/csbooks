(ns chapter2.handler2
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.core2 :as tasks]
            [core]
            ))




(def app-routes 
  (->
   (routes 
    (GET "/api2/tasks/:list-name" [list-name]
      {:body (tasks/get-tasks list-name)})
    (POST "/api2/tasks/:list-name" {{list-name :list-name task :task} :params}
      {:body (tasks/add-task list-name task)})
    (DELETE "/api2/tasks/:list-name" [list-name]
      {:body (tasks/remove-list list-name)})
    (DELETE "/api2/tasks/:list-name/:task-id" [list-name task-id]
      {:body (tasks/remove-task list)})
    )
   )
  )

(def app
  (-> 
   app-routes
   wrap-json-response
   core/wrap-500-catchall
   (wrap-defaults api-defaults)
   )
  )


(comment
  api-defaults
  (+)
  
  )