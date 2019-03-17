(ns  whip.main
    (:require [whip.a-init]
              [reagent.core :as reagent :refer [atom]]))


(println "This text is printed from src/whip/core.cljs. Go ahead and edit it and see reloading in action.!!")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {
                          :text "Hello world"
                          :projects
                          {"aaa" {:title "Build Whip"
                                  :stories {1 {:title "Design a data model for projects and stories"
                                               :status "done"
                                               :order 1
                                               }
                                            2 {:title "Create a story title entry form"
                                               :order 2
                                               }
                                            3 {:title "Implement a way to finsih stories"
                                               :order 3}
                                            }
                                  }
                           }
                          }))

;; error
; (+ "hello" 1)
; (defn (inc 1))
; (rand-nth (range 0))
; (defn choose []
;   (rand-nth (range 0)))
; (choose)
; always give functions names, even if anonymous - for readable stack trace

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

 (defn story-card [app-state id & {:keys [title status]}]
   [:li.card
    (if (= status "done")
      [:del title]
      [:span
       id
       title
       " "
       [:button
        {:on-click
         (fn done-click [e]
           (swap! app-state assoc-in [:projects "aaa" :stories id :status]
                  "done"))}
        "done"]])])

(defn project-board [app-state project-id]
 (into [:ul]
  (for [[id {:keys [title status ]}] (get-in @app-state [:projects project-id :stories])]
    [story-card app-state id :title title  :status status ]
    ; [:div 
    ;  [:li title]
    ;  [:span status]
    ;  [:button
    ;   {:on-click
    ;    (fn done-click [e]
    ;      (swap! app-state assoc-in [:projects "aaa" :stories id :status] "done")
    ;      )
    ;    }
    ;   "done"]
    ;  ]
    
    )
)
)

(defn whip-main [app-state]
  [:div
   [:h1 "Whip project management tool"]
   [project-board app-state "aaa"]])

(reagent/render-component [whip-main app-state ]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)


(comment
  
  (+ 1 1)
  
  )