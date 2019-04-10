(ns app.app
  (:require [shared.nrepl]
            [shared.core]
            [app.cascalog]
            [app.jcascalog]
            [shared.pedestal.server]))

(defn -main []
  (shared.nrepl/-main)
  (shared.pedestal.server/run-dev))

