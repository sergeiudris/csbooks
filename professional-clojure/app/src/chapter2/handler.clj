(ns chapter2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [chapter2.core :as tasks]
            [clojure.tools.namespace.repl :refer [refresh]]
            [core]
            ))

(def app-routes
  (-> 
   (routes
    (GET "/" [] "Hello World!!!")
    (GET "/api/tasks" [] {:body (tasks/get-tasks)})
    (GET "/api/tasks1" [] {:body (tasks/get-tasks)})
    (POST "/api/tasks" {{task :task} :params} {:body (tasks/add-task task)})
    (DELETE "/api/tasks/:task-id" [task-id] {:body (tasks/remove-task (Integer/parseInt task-id))})
    )
   )
  
  )

(def app
  (-> 
   app-routes
   wrap-json-response
  ;  core/wrap-json-response
   core/wrap-500-catchall
   (wrap-defaults api-defaults)
   )
)

(comment
  api-defaults
  (+)
  
  (refresh)
  
  )