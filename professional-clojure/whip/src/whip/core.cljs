(ns ^:figwheel-always whip.core
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/whip/core.cljs. Go ahead and edit it and see reloading in action.!")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change!!!"]
   [:h4 {:id "stories"
         :class "stories main"
         :style  {:font-family "cursive"}}
    "whip project management tool"
    ]
   [:h4#stories.main
    "- - -"
    ]
   [:svg  
   [:circle {:r 20
             :cx 40
             :cy 40
             :fill "green"
             }] 
    ]
   ])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)


(comment
  
  (+ 1 1)
  
  )