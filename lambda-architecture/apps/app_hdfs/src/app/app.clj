(ns app.app
  (:require [shared.nrepl]
            [shared.core]
            [app.hadoop]
            [shared.pedestal.server]))

(defn -main []
  (shared.nrepl/-main)
  (shared.pedestal.server/run-dev)
  )

