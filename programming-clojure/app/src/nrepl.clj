(ns nrepl 
  (:require [nrepl.server :refer [start-server stop-server]]
            [clojure.repl :refer :all]))

(defn nrepl-handler []
  (require 'cider.nrepl)
  (ns-resolve 'cider.nrepl 'cider-nrepl-handler))

(defn -main []
  (prn "--running REPL on 35543 ")
  (defonce server (start-server :bind "0.0.0.0" :port 35543 :handler (nrepl-handler))))

(comment
  
  (+ 1 1)
  (+)
  
  
  )