(defproject lab "0.1.0"
  :repositories {
                 "conjars"        "https://conjars.org/repo"
                 "clojars"        "https://clojars.org/repo"
                 "oracle"         "https://download.oracle.com/maven"}

  :min-lein-version "2.0.0"

  :plugins [[cider/cider-nrepl "0.18.0" :exclusions [org.clojure/tools.logging]]
            [lein-cloverage "1.0.6"]
            [lein-kibit "0.1.2"]
            [lein-bikeshed "0.2.0"]
            [lein-ancient "0.6.15"]
            [com.jakemccrary/lein-test-refresh "0.12.0"]
            [lein-auto "0.1.3"]
            [lein-virgil "0.1.9"]
            ]
  :dependencies [;; casaclog
                 [org.clojure/clojure "1.10.1-beta2"]
                 [org.clojure/core.async "0.4.490"]
                 [nrepl "0.5.3"]

                 [io.pedestal/pedestal.service       "0.5.5"  
                  ; :exclusions [org.slf4j/slf4j-api joda-time org.clojure/core.incubator]
                  ]
                 [io.pedestal/pedestal.jetty         "0.5.5"]

                 [com.twitter/carbonite "1.3.2"]
                 [com.twitter/maple "0.2.2"]
                 [com.twitter/chill-hadoop "0.3.5"]
                 [com.esotericsoftware/kryo "4.0.2"]
                 [cascalog/cascalog-core "3.0.0"
                  ; :exclusions 
                  ; [org.clojure/tools.logging com.twitter/carbonite
                  ;  org.clojure/tools.macro
                  ;  org.clojure/tools.logging ]
                  ]
                ;  [cascalog/cascalog-core "2.1.1"]
                 [org.apache.storm/storm-core "0.9.4" 
                  :exclusions 
                  [ commons-fileupload clj-time ]
                  ; [org.slf4j/log4j-over-slf4j clj-time commons-fileupload  clj-time org.slf4j/slf4j-api]
                  ]

                 [commons-io/commons-io "2.5"]
                ;  [org.apache.hadoop/hadoop-hdfs "3.1.2"]
                 [org.apache.hadoop/hadoop-client "3.1.2" 
                  ; :exclusions                   [com.google.guava/guava log4j commons-lang org.slf4j/slf4j-log4j12 org.slf4j/slf4j-api
                  ;  org.apache.zookeeper/zookeeper org.apache.httpcomponents/httpclient
                  ;  org.apache.httpcomponents/httpcore
                  ;  ]
                  ; [com.google.guava/guava commons-lang org.slf4j/slf4j-log4j12 log4j org.slf4j/slf4j-log4j12 org.slf4j/slf4j-api]
                  ]
                 [org.apache.hadoop/hadoop-hdfs "3.1.2" :exclusions [org.eclipse.jetty/jetty-util 
                                                                     ]]

                 ;; pail
                 
                 [com.backtype/dfs-datastores "1.3.6"  :exclusions []]
                 [backtype/dfs-datastores-cascading "1.2.0" :exclusions [cascading/cascading-hadoop 
                                                                         cascading/cascading-core ]]
                ;  [cascalog/cascalog-core "3.0.0"]
                ;  [cascading/cascading-hadoop "2.5.5"]
                ;  [cascalog/cascalog "1.10.0"]
                ;  [cascalog/cascalog-core "3.0.0"]
                ;  [backtype/cascading-thrift "0.2.3" :exclusions [org.apache.thrift/libthrift]]
                 [javax.annotation/javax.annotation-api "1.3.2"]
                 [org.apache.thrift/libthrift "0.9.3" 
                  ; :exclusions [org.slf4j/slf4j-api commons-logging]
                  ]
                ;  [org.apache.storm/storm-core "0.9.4" :exclusions [clj-time commons-fileupload]]
                 [com.clearspring.analytics/stream "2.7.0"]
                 [org.apache.zookeeper/zookeeper "3.4.6"]
                 [org.hectorclient/hector-core "2.0-0" 
                  ; :exclusions [org.slf4j/slf4j-api]
                  ]
                 [elephantdb/elephantdb-cascalog "0.4.5" 
                  ; :exclusions [
                  ;                                                     cascading/cascading-hadoop cascading/cascading-core
                  ;                                                     org.slf4j/slf4j-api org.clojure/tools.macro]
                  ]
                 [org.apache.kafka/kafka_2.9.2 "0.8.1.1" 
                  ; :exclusions [org.slf4j/slf4j-api]
                  ]
                 [org.apache.storm/storm-kafka "0.9.4" 
                  ; :exclusions [org.slf4j/slf4j-api]
                  ]
                 [elephantdb/elephantdb-bdb "0.4.5" :exclusions [
                                                                 cascading/cascading-hadoop
                                                                  cascading/cascading-core
                                                                 ]]
                 
                 ;
                 ]

  :resource-paths ["resources"]

  :repl-options {:init-ns dev
                 :main    dev
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }
  :profiles {:dev       {:main         dev
                         :aliases      {"dev" ["trampoline" "run" "-m" "dev/-main"]}
                         :dependencies [[io.pedestal/pedestal.service-tools "0.5.5" 
                                        ;  :exclusions [joda-time
                                        ;                                                          ch.qos.logback/logback-core
                                        ;                                                          ch.qos.logback/logback-classic
                                        ;                                                          org.clojure/core.incubator
                                        ;                                                          org.slf4j/slf4j-api]
                                         ]
                                        ]}

             :wordcount ^:leaky {:main         lab.wordcount.main
                                 :uberjar-name "wordcount-standalone.jar"
                                 :jar-name "wordcount.jar"
                                 :aot          [lab.wordcount.main]}

             :cascalog ^:leaky  {:main         lab.cascalog.main
                                 :uberjar-name "cascalog-standalone.jar"
                                 :jar-name "cascalog.jar"
                                 :aot          [lab.cascalog.main]}
             :sandbox  ^:leaky {:main         lab.sandbox.main
                                :uberjar-name "sandbox-standalone.jar"
                                :jar-name "sandbox.jar"
                                :aot          [lab.sandbox.main]}
             :supweb1   {:main         lab.supweb.main1
                         :uberjar-name "supweb1-standalone.jar"
                         :jar-name "supweb1.jar"
                         :aot          [lab.supweb.main1]}

             :supweb2   {:main         lab.supweb.main2
                         :uberjar-name "supweb2-standalone.jar"
                         :jar-name "supweb2.jar"
                         :aot          [lab.supweb.main2]}}
  

  :main ^{:skip-aot false} dev
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :javac-opts ["-nowarn "]
  :java-source-paths ["src"]
  :auto-clean false
 
  )
