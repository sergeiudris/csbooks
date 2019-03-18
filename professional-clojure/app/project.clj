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
                 [cheshire "5.5.0"]
                 [peridot "0.5.1"]
                 [com.datomic/datomic-free "0.9.5656"]
                 [crypto-password "0.1.3"]
                 ]
  :plugins [[lein-ring "0.12.5"]
            [lein-cloverage "1.0.6"]
            [lein-kibit "0.1.2"]
            [lein-bikeshed "0.2.0"]
            [lein-ancient "0.6.15"]
            [com.jakemccrary/lein-test-refresh "0.12.0"]
            [cider/cider-nrepl "0.18.0"]]
  :ring {
        ;  :handler chapter2.handler/app
         :auto-reload? true
         :handler application/app
         :port 8080 
         :host "0.0.0.0"  
         :nrepl {:start? true :port 35543 :host "0.0.0.0"}
         }
  ; :main application
  :profiles
  {:dev {:source-paths ["setup"]
         :dependencies [[clj-http "2.0.1"]
                        [javax.servlet/servlet-api "2.5"]
                        [org.clojure/tools.namespace "0.2.11"]
                        [ring/ring-mock "0.3.2"]]}}
  :repl-options {:init-ns application
                ;  :main dq.dq
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  )
