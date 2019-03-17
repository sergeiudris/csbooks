(ns  whip.main
    (:require [whip.a-init]
              [reagent.core :as reagent :refer [atom]]
              [goog.dom :as dom]
              [whip.model :as model]
              [whip.view :as view]
              ))


(println "This text is printed from src/whip/core.cljs. Go ahead and edit it and see reloading in action.!!")

;; define your app data so that it doesn't get over-written on reload


;; error
; (+ "hello" 1)
; (defn (inc 1))
; (rand-nth (range 0))
; (defn choose []
;   (rand-nth (range 0)))
; (choose)
; always give functions names, even if anonymous - for readable stack trace


(when-let [app (dom/getElement "app")]
  (reagent/render-component [view/whip-main model/app-state]
                            (. js/document (getElementById "app")))
  )

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)


(comment
  
  (+ 1 1)

  ;; JavaScript interop
  
  (. js/console log 3)
  (.log js/console  3)
  (. js/Number parseInt "3")
  
  (.addEventListener js/window "load" (fn [e] (prn "hi") ))
  
  (.-Audio js/window)
  (. js/window -Audio)
  
  (set! (.-Audio js/window) nil)
  
  
  )