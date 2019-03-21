(defproject app "0.1.0-SNAPSHOT"
  
  
  :plugins [[cider/cider-nrepl "0.18.0"]
            ]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cider/piggieback "0.3.10"]
                 [nrepl "0.5.3"]
                 [org.clojure/core.async "0.4.490"]
                 [net.mikera/core.matrix "0.20.0"]
                 ]
  :min-lein-version "2.8.3"
  :resource-paths ["config", "resources"]
  :repl-options {:init-ns core
                 :main core
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "core/-main"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.5"]]}}
  :main ^{:skip-aot true} core
  
  )
