;;

;;

(defproject org.apache.mxnet.contrib.clojure/clojure-mxnet "1.5.0-SNAPSHOT"
  :description "Clojure package for MXNet"
  :url "https://github.com/apache/incubator-mxnet"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [t6/from-scala "0.3.0"]

                 ;; To use with nightly snapshot
                 ;[org.apache.mxnet/mxnet-full_2.11-osx-x86_64-cpu "<insert-snapshot-version>"]
                 [org.apache.mxnet/mxnet-full_2.11-linux-x86_64-cpu "1.5.0-SNAPSHOT"]
                 ;[org.apache.mxnet/mxnet-full_2.11-linux-x86_64-gpu "<insert-snapshot-version"]

                 ;;; CI
                 ;[org.apache.mxnet/mxnet-full_2.11 "INTERNAL"]

                 [org.clojure/tools.logging "0.4.0"]
                 [org.apache.logging.log4j/log4j-core "2.8.1"]
                 [org.apache.logging.log4j/log4j-api "2.8.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.25" :exclusions [org.slf4j/slf4j-api]]]
  :pedantic? :skip
  :plugins [[lein-codox "0.10.6" :exclusions [org.clojure/clojure]]
            [lein-cloverage "1.0.10" :exclusions [org.clojure/clojure]]
            [lein-cljfmt "0.5.7"]]
  :codox {:namespaces [#"^org\.apache\.clojure-mxnet\.(?!gen).*"]}
  :aot [dev.generator]
  :repositories [["staging" {:url       "https://repository.apache.org/content/repositories/staging"
                             :snapshots true
                             :update    :always}]
                 ["snapshots" {:url       "https://repository.apache.org/content/repositories/snapshots"
                               :snapshots true
                               :update    :always}]])