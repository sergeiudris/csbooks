(defproject app "0.1.0-SNAPSHOT"
  
  
  :plugins [[cider/cider-nrepl "0.21.1"]
            ]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cider/piggieback "0.3.10"]
                 [nrepl "0.5.3"]
                 [cider/cider-nrepl "0.21.1"]
                 [org.clojure/core.async "0.4.490"]
                 [net.mikera/core.matrix "0.20.0"]
                 ]
  :min-lein-version "2.8.3"
  :resource-paths ["config", "resources"]
  :repl-options {:init-ns main
                 :main main
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "main/-main"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.5"]]}}
  :main ^{:skip-aot true} main
  
  )
