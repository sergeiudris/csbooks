(defproject la.app_cascalog "0.1.0-SNAPSHOT"
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo/"
                                    ; :creds :gpg
                                   }
                 "conjars" "https://conjars.org/repo"
                 "clojars" "https://clojars.org/repo"
                 "oracle"  "https://download.oracle.com/maven"
                 
                 }
            
  :min-lein-version "2.0.0"
  
  :plugins [[cider/cider-nrepl "0.21.1" :exclusions [org.clojure/tools.logging]]
            [lein-cloverage "1.0.6"]
            [lein-kibit "0.1.2"]
            [lein-bikeshed "0.2.0"]
            [lein-ancient "0.6.15"]
            [com.jakemccrary/lein-test-refresh "0.12.0"]
            [lein-auto "0.1.3"]
            [lein-virgil "0.1.9"]
            ]
  :dependencies [[org.clojure/clojure "1.10.1-beta2"]
                 [org.clojure/core.async "0.4.490"]
                 [nrepl "0.6.0"]

                 [io.pedestal/pedestal.service       "0.5.5"  :exclusions [org.slf4j/slf4j-api joda-time org.clojure/core.incubator]]
                 [io.pedestal/pedestal.jetty         "0.5.5"]

                 [com.twitter/carbonite "1.3.2"]
                 [com.twitter/maple "0.2.2"]
                 [com.twitter/chill-hadoop "0.3.5"]
                 [com.esotericsoftware/kryo "4.0.2"]
                 [cascalog/cascalog-core "3.0.0" :exclusions [org.clojure/tools.logging]] 
                ;  [cascalog/cascalog-core "2.1.1"]
                 [org.apache.storm/storm-core "0.9.4" :exclusions [org.slf4j/log4j-over-slf4j clj-time commons-fileupload  clj-time org.slf4j/slf4j-api]]

                 [commons-io/commons-io "2.5"]
                ;  [org.apache.hadoop/hadoop-hdfs "3.1.2"]
                 [org.apache.hadoop/hadoop-client "3.1.2" :exclusions [com.google.guava/guava commons-lang org.slf4j/slf4j-log4j12 log4j org.slf4j/slf4j-log4j12 org.slf4j/slf4j-api] ]
                 [org.apache.hadoop/hadoop-hdfs "3.1.2" :exclusions [org.eclipse.jetty/jetty-util commons-lang log4j]]]
  
  :resource-paths ["config", "resources"]
  
  :repl-options {:init-ns app.app
                 :main app.app
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "app.app/-dev"]}
                   :dependencies [
                                  [io.pedestal/pedestal.service-tools "0.5.5"]
                                  ; [org.apache.hadoop/hadoop-core "1.2.1"]
                                  ]}
             :uberjar {:aot [app.app]}
             }
  
  :main ^{:skip-aot true} app.app
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :java-source-paths ["src"]
  ; :auto {"javac" {
  ;                 ; :file-pattern #"\.(clj|cljs|cljx|cljc|edn)$"
  ;                 :paths ["src"]
  ;                 :wait-time 200
  ;                 }}
  ; ;
  )
