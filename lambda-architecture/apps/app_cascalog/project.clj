(defproject la.app_cascalog "0.1.0-SNAPSHOT"
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo/"
                                    ; :creds :gpg
                                   }
                 "conjars" "https://conjars.org/repo"
                 "clojars" "https://clojars.org/repo"
                 "oracle"  "https://download.oracle.com/maven"
                 
                 }
            
  :min-lein-version "2.0.0"
  
  :plugins [[cider/cider-nrepl "0.18.0"]
            [lein-cloverage "1.0.6"]
            [lein-kibit "0.1.2"]
            [lein-bikeshed "0.2.0"]
            [lein-ancient "0.6.15"]
            [com.jakemccrary/lein-test-refresh "0.12.0"]
            ]
  :dependencies [
                 [org.clojure/clojure "1.10.1-beta1"]
                 [org.clojure/core.async "0.4.490"]
                 [nrepl "0.5.3"]

                 [io.pedestal/pedestal.service       "0.5.5"]
                 [io.pedestal/pedestal.jetty         "0.5.5"]


                 [cascalog/cascalog-core "3.0.0"]
                 ]
  
  :resource-paths ["config", "resources"]
  
  :repl-options {:init-ns app.app
                 :main app.app
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "app.app/-main"]}
                   :dependencies [
                                  [org.apache.hadoop/hadoop-core "1.2.1"]
                                  ]}
             :uberjar {:aot [app.app]}
             }
  
  :main ^{:skip-aot true} app.app
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :java-source-paths ["src/java", "src/manning"]
  ;
  )
