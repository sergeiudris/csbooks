(defproject whip "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  
  
  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async  "0.4.474"]
                 [reagent "0.8.1"]
                 [devcards "0.2.6"]
                 [lein-ancient "0.6.15"]
                 ]

  :plugins [[lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; The presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "whip.main/on-js-reload"
                           ;; :open-urls will pop open your application
                           ;; in the default browser once Figwheel has
                           ;; started and compiled your application.
                           ;; Comment this out once it no longer serves you.
                          ;  :open-urls ["http://localhost:3449/index.html"]
                           :websocket-host "0.0.0.0"}

                :compiler {:main whip.main
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/whip.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}
               
               {:id "devcards"
                :source-paths ["src"]

                ;; The presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "whip.main/on-js-reload"
                           :devcards true
                           ;; :open-urls will pop open your application
                           ;; in the default browser once Figwheel has
                           ;; started and compiled your application.
                           ;; Comment this out once it no longer serves you.
                          ;  :open-urls ["http://localhost:3449/index.html"]
                           :websocket-host "0.0.0.0"}

                :compiler {:main whip.main
                           :output-to "resources/public/js/compiled/devcards.js"
                           :output-dir "resources/public/js/compiled/devcards"
                           :asset-path "js/compiled/devcards"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}
               
               
               
               ;; This next build is a compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/whip.js"
                           :main whip.main
                           :optimizations :advanced
                           :source-map "resources/public/js/compiled/whip.js.map"
                           :output-dir "resources/public/js/compiled/min"
                           :pretty-print false}}]
              ; :warning-handlers
              ; [(fn treat-warnings-as errors [warning-type env extra]
              ;    (when-let [s (cljs.analyzer/error-message warning-type extra)]
              ;      (binding [*out* *err*]
              ;        (println "WARNING:" (cljs.analyzer/message env s)))
              ;      (System/exit 1)))]
              }

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"
             
             :css-dirs ["resources/public/css"] ;; watch and update CSS
             
             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888
             
             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             
             ;; doesn't work for you just run your own server :) (see lein-ring)
             
             ;; :ring-handler hello_world.server/handler
             
             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"
             
             ;; if you are using emacsclient you can just use
             ;; :open-file-command "emacsclient"
             
             ;; if you want to disable the REPL
             ;; :repl false
             
             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             
             ;; to pipe all the output to the repl
             ;; :server-logfile false
             :server-port 3449
             :nrepl-host  "0.0.0.0"
             :nrepl-port  35543
             :repl        false
            ;  :repl        true
             :nrepl-middleware ["cider.nrepl/cider-middleware"
                            ;     "refactor-nrepl.middleware/wrap-refactor"
                                "cider.piggieback/wrap-cljs-repl"]
             }



  ;; Setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.9"]
                                  [figwheel-sidecar "0.5.16"]
                                  [cider/piggieback "0.3.1"]]

                   :plugins      [[lein-figwheel "0.5.16"]
                                  [cider/cider-nrepl "0.18.0"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
