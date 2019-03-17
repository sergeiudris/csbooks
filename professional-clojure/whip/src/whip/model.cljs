(ns whip.model
  (:require [reagent.core :as reagent :refer [atom]])
  )

(defonce app-state (atom {:text "Hello world"
                          :projects
                          {"aaa" {:title "Build Whip"
                                  :stories {1 {:title "Design a data model for projects and stories"
                                               :status "done"
                                               :order 1}
                                            2 {:title "Create a story title entry form"
                                               :order 2}
                                            3 {:title "Implement a way to finsih stories"
                                               :order 3}}}}}))
