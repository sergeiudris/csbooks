(ns tools.nrepl
  (:require [nrepl.server :refer [start-server stop-server]]
            [clojure.repl :refer :all]))

(defn -main []
  (prn "--running REPL on 35543 ")
  (defonce server (start-server :bind "0.0.0.0" :port 35543)))

(comment

  (+ 1 1)
  
  ;
  )