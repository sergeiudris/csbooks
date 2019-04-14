(ns casc.examples
  (:require [clojure.repl :refer :all]
            [cascalog.api :refer :all]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as defc]
            [cascalog.playground :refer :all]
            [clojure.pprint :refer [print-table] :as pp]
            [jackknife.seq :as s :refer (unweave collectify)]))

(comment
  (clojure-version)
  ;
  )

(defn age-25
  []
  (?<- (stdout)
       [?person]
       (age ?person 25))
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
       (c/count ?count))

  (?<- (stdout)
       [?person]
       (age ?person 25))


  (?<- (stdout)
       [?person]
       (age ?person ?age)
       (< ?age 30))

  (?<- (stdout)
       [?person ?age]
       (age ?person ?age)
       (< ?age 30))

  (?<- (stdout)
       [?person]
       (follows "emily" ?person)
       (gender ?person "m"))

  (?<- (stdout)
       [?person ?a2]
       (age ?person ?age)
       (< ?age 30)
       (* 2 ?age :> ?a2))

  (?<- (stdout)
       [?person1 ?person2]
       (age ?person1 ?age1)
       (follows ?person1 ?person2)
       (age ?person2 ?age2)
       (< ?age2 ?age1))

  (?<- (stdout)
       [?person1 ?person2 ?delta]
       (age ?person1 ?age1)
       (follows ?person1 ?person2)
       (age ?person2 ?age2)
       (- ?age2 ?age1 :> ?delta)
       (< ?delta 0))

  (?<- (stdout)
       [?count]
       (age _ ?a)
       (< ?a 30)
       (c/count ?count))

  
;   When executing the
; aggregator, the
; output fields imply
; tuples should be
; grouped by ?person.
  (?<- (stdout)
       [?person ?count]
       (follows ?person _)
       (c/count ?count))

   ; smart query
  (?<- (stdout)
       [?country ?avg]
       (location ?person ?country _ _)
       (age ?person ?age)
       (c/count ?count)
       (c/sum ?age :> ?sum)
       (div ?sum ?count :> ?avg))

  (defmapcatop split [sentence]
    (seq (.split sentence "\\s+")))

  (?<- (stdout) [?word ?count] (sentence ?s)
       (split ?s :> ?word) (c/count ?count))

  (defn lowercase [w] (.toLowerCase w))

  (?<- (stdout) [?word ?count]
       (sentence ?s) (split ?s :> ?word1)
       (lowercase ?word1 :> ?word) (c/count ?count))

   ; sirius
  (defn agebucket [age]
    (s/find-first (partial <= age) [17 25 35 45 55 65 100 200]))

  (?<- (stdout) [?bucket ?gender ?count]
       (age ?person ?age) (gender ?person ?gender)
       (agebucket ?age :> ?bucket) (c/count ?count))


  (?<- (stdout) [?person ?city] (location ?person _ _ ?city))

  (?<- (stdout) [?person !city] (location ?person _ _ !city))
  
  
  ;subqueries
  
  (let [many-follows (<- [?person] (follows ?person _)
                         (c/count ?c) (> ?c 2))]
    (?<- (stdout) [?person1 ?person2] (many-follows ?person1)
         (many-follows ?person2) (follows ?person1 ?person2)))
  
  (let [many-follows   (<- [?person] (follows ?person _)
                           (c/count ?c) (> ?c 2))
        active-follows (<- [?p1 ?p2] (many-follows ?p1)
                           (many-follows ?p2) (follows ?p1 ?p2))]
    (?- (stdout) many-follows (stdout) active-follows))
  
  
  
  ;
  )