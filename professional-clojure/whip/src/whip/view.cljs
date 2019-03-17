(ns whip.view
  (:require    [whip.model :as model :refer [app-state]]
               [reagent.core :as reagent :refer [atom]]
               [devcards.core :refer-macros [defcard-rg defcard deftest]])
  )




(defn projects-list [app-state]
  [:div "Project List"])

(defcard-rg projects-list-example
  [projects-list model/app-state])



(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change!!!"]
   [:h4 {:id "stories"
         :class "stories main"
         :style  {:font-family "cursive"}}
    "whip project management tool"]
   [:h4#stories.main
    "- - -"]
   [:svg
    [:circle {:r 20
              :cx 40
              :cy 40
              :fill "green"}]]])

(defcard test-card
  "=Hello hello"
  [:div "hello"])
    ; [story-card app-state id :title title  :status status]



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
        (for [[id {:keys [title status]}] (get-in @app-state [:projects project-id :stories])]
          [story-card app-state id :title title  :status status]
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
          )))

(defcard-rg project-board-example
  [project-board model/app-state "aaa"])

(defn story-card-example-component []
  (let [app-state (reagent/atom model/example-projects)]
    (fn a-story-card-example-component []
      [story-card app-state  2  :title "title"  :status "status"
       (get-in @app-state [:projects "aaa" :stories 2])])))

(defcard-rg story-card-example
  [story-card-example-component])


(defn whip-main [app-state]
  [:div
   [navbar app-state]
   [:h1 "Whip project management tool"]
   [project-board app-state "aaa"]])