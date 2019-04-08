(defproject la.app "0.1.0-SNAPSHOT"
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
    :exclusions [log4j/log4j org.slf4j/slf4j-log4j12]
  :dependencies [[org.clojure/clojure "1.10.1-beta1"]
                 [cider/piggieback "0.3.10"]
                 [figwheel-sidecar "0.5.16"]
                 [nrepl "0.5.3"]

                 [com.datomic/datomic-free "0.9.5656"]

                 [cheshire "5.5.0"]
                 [peridot "0.5.1"]
                 [crypto-password "0.1.3"]


                 [commons-codec "1.7"]
                 [io.pedestal/pedestal.service       "0.5.5"]
                ;  [io.pedestal/pedestal.service-tools "0.5.5"] ;; Only needed for ns-watching; WAR tooling
                 [io.pedestal/pedestal.jetty         "0.5.5"]
                        ;; [io.pedestal/pedestal.immutant "0.5.5"]
                ;  [io.pedestal/pedestal.tomcat "0.5.5"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                ;  [org.slf4j/jul-to-slf4j "1.7.25"]
                ;  [org.slf4j/jcl-over-slf4j "1.7.25"]
                ;  [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [ring-cors/ring-cors "0.1.13"]
                ;  [log4j/log4j "1.2.17"]
                 [log4j "1.2.16"]
                 [org.slf4j/slf4j-log4j12 "1.6.6"]

                 [org.clojure/java.jdbc "0.7.8"]
                 [org.postgresql/postgresql "42.2.5.jre7"]
                 [org.clojure/core.async "0.4.490"]


                 [com.backtype/dfs-datastores "1.3.6"]
                 [backtype/dfs-datastores-cascading "1.2.0"]
                 [cascalog/cascalog-core "3.0.0"]
                 [cascading/cascading-hadoop "2.5.5"]
                ;  [cascalog/cascalog "1.10.0"]
                ;  [cascalog/cascalog-core "3.0.0"]
                ;  [backtype/cascading-thrift "0.2.3" :exclusions [org.apache.thrift/libthrift]]
                 [javax.annotation/javax.annotation-api "1.3.2"]
                 [org.apache.thrift/libthrift "0.9.3"]
                 [org.apache.storm/storm-core "0.9.4"]
                 [com.clearspring.analytics/stream "2.7.0"]
                 [org.apache.zookeeper/zookeeper "3.4.6"]
                 [org.hectorclient/hector-core "2.0-0"]
                 [elephantdb/elephantdb-cascalog "0.4.5"]
                 [org.apache.kafka/kafka_2.9.2 "0.8.1.1"]
                 [org.apache.storm/storm-kafka "0.9.4"]
                 [elephantdb/elephantdb-bdb "0.4.5"]


                 [org.apache.hadoop/hadoop-client "2.7.1"]
                 [org.apache.hadoop/hadoop-hdfs "2.7.1"]
                 [commons-io/commons-io "2.5"]
                 [commons-fileupload/commons-fileupload "1.3"]
                ;  [org.apache.commons/commons-fileupload "1.4"]
                ;  [org.apache.commons/commons-io "1.3.2"]
                 [org.apache.hadoop/hadoop-common "2.7.1"]]
  
  :resource-paths ["config", "resources"]
  
  :repl-options {:init-ns app.app
                 :main app.app
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "app.app/-main"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.5"]
                                  [org.apache.hadoop/hadoop-core "1.2.1"]
                                  ]}
             :uberjar {:aot [app.app]}
             }
  
  :main ^{:skip-aot true} app.app
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :java-source-paths ["src/java", "src/manning"]
  ;
  )
