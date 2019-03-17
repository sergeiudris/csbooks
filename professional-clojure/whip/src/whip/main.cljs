(ns  whip.main
    (:require [whip.a-init]
              [reagent.core :as reagent :refer [atom]]
              [goog.dom :as dom]
              [whip.model :as model]
              
              ))


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


(defn navbar [app-state]
  
  [:nav
   [:ul.nav-list
    [:li
     [:a
      {:href "#/"}
      ; [:img
      ;  {:src "img/logo.png"}]
      [:span
       {:style {:font-family "fantasy" :font-size "3em"}}
       " Whip"]]]
    [:ul.nav-list
     {:style {:float "right"}}
     [:li
      [:a {:href "#/"} "About"]]
     [:li
      (if-let [username (:username @app-state)]
        [:a {:href "#/settings"} username]
        [:a {:href "#/login"} "Login"])]]]])


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

;  (defn add-story-form [app-state project-id status]
;    [:form
;     {:on-submit
;      (fn add-story-submit [e]
;        (.preventDefault e)
;        (model/add-story!
;         app-state
;         project-id
;         (forms/getValueByName (.-target e) "story-title")
;         status))}
;     [:input
;      {:type "text"
;       :name "story-title"}]
;     [:input
;      {:type "submit"
;       :value "Add story"}]]
;    )


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
   [navbar app-state]
   [:h1 "Whip project management tool"]
   [project-board app-state "aaa"]
   ])

(when-let [app (dom/getElement "app")]
  (reagent/render-component [whip-main app-state]
                            (. js/document (getElementById "app")))
  )

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)


(comment
  
  (+ 1 1)
  
  )