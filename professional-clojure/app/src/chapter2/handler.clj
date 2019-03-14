(ns chapter2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.core :as tasks]
            ))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/api/tasks" [] {:body (tasks/get-tasks)} )
  (POST "/api/tasks" {{task :task} :params} {:body (tasks/add-task task)})
  (DELETE "api/tasks/:task-id" [task-id] {:body (tasks/remove-task (Integer/parseInt task-id) )} )
  (route/not-found "Not Found"))

(def app
  (-> 
   app-routes
   (wrap-defaults site-defaults)
   wrap-json-response
   )
)

(comment
  
  (+)
  
  )