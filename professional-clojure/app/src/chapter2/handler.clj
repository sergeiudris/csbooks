(ns chapter2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.core :as tasks]
            [chapter2.core2 :as tasks2]
            [clojure.tools.namespace.repl :refer [refresh]]
            
            ))

(defroutes app-routes
  (GET "/" [] "Hello World!!!")
  (GET "/api/tasks" [] {:body (tasks/get-tasks)} )
  (GET "/api/tasks1" [] {:body (tasks/get-tasks)})
  (POST "/api/tasks" {{task :task} :params} {:body (tasks/add-task task)})
  (DELETE "/api/tasks/:task-id" [task-id] {:body (tasks/remove-task (Integer/parseInt task-id) )} )
  
  
  (GET "/api2/tasks/:list-name" [list-name]
    {:body (tasks2/get-tasks list-name)})
  (POST "/api2/tasks/:list-name" {{list-name :list-name task :task} :params}
    {:body (tasks2/add-task list-name task)})
  (DELETE "/api2/tasks/:list-name" [list-name]
    {:body (tasks2/remove-list list-name)})
  (DELETE "/api2/tasks/:list-name/:task-id" [list-name task-id]
    {:body (tasks2/remove-task list)})
  
  (route/not-found "Not Found")
  
  )

(def app
  (-> 
   app-routes
   (wrap-defaults api-defaults)
   wrap-json-response
   )
)

(comment
  api-defaults
  (+)
  
  (refresh)
  
  )