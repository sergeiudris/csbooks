(ns lab.cascalog.main
  (:gen-class)
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
            [ lab.cascalog.examples]
            [ lab.cascalog.jcascalog]
   ;
            ))



(defn -main [& args]
  ( lab.cascalog.examples/age-25)
  )







(comment
  (clojure-version)
  ;
  )




(comment
  (?- (stdout)
      sentence)

  (?- (stdout)
      (<- [?line]
          (sentence :> ?line)))

  (defc/defmapcatfn tokenise [line]
    "reads in a line of string and splits it by a regular expression"
    (clojure.string/split line #"[\[\]\\\(\),.)\s]+"))


  (?<- (stdout)
       [?word ?count]
       (sentence ?line)
       (tokenise ?line :> ?word)
       (c/count ?count)))