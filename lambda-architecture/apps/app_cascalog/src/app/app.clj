(ns app.app
  (:require [shared.nrepl]
            [shared.core]
            [app.cascalog]
            [app.jcascalog]
            [app.wordcount]
            [shared.pedestal.server]))

(defn -main []
  (shared.nrepl/-main)
  (shared.pedestal.server/run-dev))

