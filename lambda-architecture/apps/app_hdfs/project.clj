(defproject la.app-hdfs "0.1.0-SNAPSHOT"
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo/"
                                    ; :creds :gpg
                                   }
                 "conjars" "https://conjars.org/repo"
                 "clojars" "https://clojars.org/repo"
                 "oracle"  "https://download.oracle.com/maven"
                 
                 }
            
  :min-lein-version "2.0.0"
  
  :plugins [[cider/cider-nrepl "0.18.0"]
            [com.jakemccrary/lein-test-refresh "0.12.0"]
            ]
            
  :dependencies [[org.clojure/clojure "1.10.1-beta1"]
                 [nrepl "0.5.3"]
                 [org.clojure/core.async "0.4.490"]

                 [io.pedestal/pedestal.service       "0.5.5"]
                 [io.pedestal/pedestal.jetty         "0.5.5"]

                 [org.apache.hadoop/hadoop-client "3.1.2"]
                 [org.apache.hadoop/hadoop-hdfs "3.1.2" :exclusions [org.eclipse.jetty/jetty-util]]
                 [commons-io/commons-io "2.5"]
                ;  [commons-fileupload/commons-fileupload "1.3"]
                ;  [org.apache.commons/commons-fileupload "1.4"]
                ;  [org.apache.commons/commons-io "1.3.2"]
                ;  [org.apache.hadoop/hadoop-common "2.7.1"]
                 ]
  
  :resource-paths ["config", "resources"]
  
  :repl-options {:init-ns app.app
                 :main app.app
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "app.app/-main"]}
                   :dependencies [
                               
                                  ]}
             :uberjar {:aot [app.app]}
             }
  
  :main ^{:skip-aot true} app.app
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :java-source-paths []
  ;
  )
