; this file needed to enhance repl with  syntax highilighting (throughmvxcvi/whidbey )
; works both  for 'lein repl' and vscode calva 
; added here to make it portable for project instead of machine specific profiles.clj
(defproject wokspace "0.1.0"
  :repositories {"conjars" "https://conjars.org/repo"
                 "clojars" "https://clojars.org/repo"
                 "oracle"  "https://download.oracle.com/maven"}

  :min-lein-version "2.0.0"

  :plugins [[mvxcvi/whidbey "2.1.1"]]





  :dependencies [;; casaclog
                 [org.clojure/clojure "1.10.1-beta2"]
                 [nrepl "0.6.0"]
                 ;
                 ]

  :repl-options {:init-ns          main
                 :main             main
                 :host             "0.0.0.0"
                 :port             4001
                 :nrepl-middleware [cider.nrepl/wrap-apropos
                                    cider.nrepl/wrap-classpath
                                    cider.nrepl/wrap-complete
                                    cider.nrepl/wrap-debug
                                    cider.nrepl/wrap-format
                                    cider.nrepl/wrap-info
                                    cider.nrepl/wrap-inspect
                                    cider.nrepl/wrap-macroexpand
                                    cider.nrepl/wrap-ns
                                    cider.nrepl/wrap-spec
                                    cider.nrepl/wrap-profile
                                    cider.nrepl/wrap-refresh
                                    cider.nrepl/wrap-resource
                                    cider.nrepl/wrap-stacktrace
                                    cider.nrepl/wrap-test
                                    cider.nrepl/wrap-trace
                                    cider.nrepl/wrap-out
                                    cider.nrepl/wrap-undef
                                    nrepl.middleware.print/wrap-print
                                    cider.nrepl/wrap-version]
                ;  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                 }

  :middleware [whidbey.plugin/repl-pprint]


  :whidbey {:print-color     true
            :map-delimiter   ""
            :extend-notation true
            ; :width 180
            ; :print-meta      true
            }

  ;
  )
