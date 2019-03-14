(defproject app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [cider/piggieback "0.3.10"]
                 [figwheel-sidecar "0.5.16"]
                 [nrepl "0.5.3"]
                 [org.clojure/java.jdbc "0.7.9"]
                 ]
  :plugins [[lein-ring "0.12.5"]
            [cider/cider-nrepl "0.18.0"]]
  :ring {
        ;  :handler chapter2.handler/app
         :auto-reload? true
         :handler app/app
         :port 8080 
         :host "0.0.0.0"  
         :nrepl {:start? true :port 35543 :host "0.0.0.0"}
         }
  ; :main app
  :profiles
  {:dev {:dependencies [[clj-http "2.0.1"]
                        [javax.servlet/servlet-api "2.5"]
                        [org.clojure/tools.namespace "0.2.11"]
                        [ring/ring-mock "0.3.2"]]}}
  :repl-options {:init-ns app
                ;  :main dq.dq
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  )
