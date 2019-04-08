(ns app.app
  (:require [core.nrepl]
            [core.core]
            [app.hadoop]
            [app.superwebanalytics]
            [app.cascalog]
            [core.pedestal.server]))

(defn -main []
  (core.nrepl/-main)
  (core.pedestal.server/run-dev)
  )

