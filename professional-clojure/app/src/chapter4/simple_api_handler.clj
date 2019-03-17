(ns chapter4.simple-api-handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.session :as session]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


(defn login [req]
  (let [user (get-in req [:params :user])
        session (get-in req [:session])]
    {:body "Success"
     :session (assoc session :user user)}))


(defn say-hello [{session :session}]
  (if (:user session)
    {:body (str "Hello, " (:user session))}
    {:body "Hello World"}))


(defroutes app-routes
  (GET "/" req say-hello)
  (POST "/login" req login)
  (route/not-found "Not Found"))


(def app
  (-> app-routes session/wrap-session))