(ns cas.main
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
            [cas.examples]
            [cas.jcascalog]
   ;
            ))



(defn -main [& args]
  (cas.examples/age-25)
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