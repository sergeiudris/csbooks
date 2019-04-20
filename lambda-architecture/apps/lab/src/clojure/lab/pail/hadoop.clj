(ns lab.pail.hadoop  
  (:require [clojure.repl :refer :all]
            [clojure.reflect :as r]
            [clojure.pprint :refer [print-table] :as pp]
            [virgil]
            [virgil.compile]
            [lab.virgil.virgil :as lv]
            [lab.virgil.virgil.compile :as lvc]
            [clojure.tools.namespace.repl :refer (refresh-all)]
   ;
            )
  (:import 
   (com.backtype.hadoop.pail Pail PailStructure SequenceFileFormat PailSpec)
   (lab.pail Login LoginPailStructure PartitionedLoginPailStructure)
   (lab.pail Examples)
   (java.util HashMap)
   )
  )

  



(comment
  (doc doc)
  (System/getProperty "java.vm.version")
  (System/getProperty "java.version")
  (System/getProperty "java.specification.version")





  (virgil/watch "src/java")
  (virgil.compile/compile-all-java ["src/java"])

  (virgil.compile/compile-all-java ["src/java/lab/cascalog"])

  (virgil.compile/compile-all-java ["src/java/lab/pail"])

  (virgil.compile/compile-all-java ["src/java/lab"])





  (lvc/compile-all-java ["src/java/lab/cascalog"])

  (lvc/compile-all-java ["src/java/lab/pail"])

  (lvc/compile-all-java ["src/java/lab"])

  (lv/watch "src/java/lab")

  
  
  (lab.cascalog.Examples/hello)
  (lvc/compile-all-java ["src/java/lab/cascalog"])
  (binding [*ns* *ns*]
    (refresh-all))
  (lab.cascalog.Examples/hello)
  
  (Examples/partitionData "/tmp/22223")
  



  (clojure-version)
  (use 'cascalog.api)
  (use 'cascalog.playground)

  true


    ; https://livebook.manning.com/#!/book/big-data/about-this-book/2
  (defn partition-data
    [path]
    (let
     [pail (Pail/create path (PartitionedLoginPailStructure.))
      os   (.openWrite pail)]
      (.writeObject os (Login. "chris" 1352702020))
      (.writeObject os (Login. "david" 1352788472))
      (.close os)
      (prn "done")))
  (partition-data "/tmp/pw2wsw211")



  (Examples/partitionData "/tmp/1ps2dss3sssw2ssw2")




  (def pail (Pail/create "/tmp/mypail"))
  (def os (.openWrite pail))
  (.writeObject os (bytes (byte-array [1 2 3])))
  (.writeObject os (bytes (byte-array [1 2 3 4])))
  (.writeObject os (bytes (byte-array [1 2 3 4 5])))
  (.close os)


  (defn simpleIO
    []
    (let
     [pail (Pail/create "/tmp/mypail2")
      os   (.openWrite pail)]
      (.writeObject os (bytes (byte-array [1 2 3])))
      (.writeObject os (bytes (byte-array [1 2 3 4])))
      (.writeObject os (bytes (byte-array [1 2 3 4 5])))
      (.close os)
      os))

  (def os (simpleIO))

  ; (gen-class :name app.hadoop.Login
  ;            :prefix login
  ;            :main false)


  ; (gen-class :name app.hadoop.LoginPailStructure 
  ;            :extends  PailStructure
  ;            :state :state
  ;            :prefix login-pail-structure
  ;            :main false

  ;            )



  (.getName Pail)
  ; (.create Pail)

  (doc bytes)
  (doc byte-array)


  (bytes (byte-array [1 2 3]))


  (defn write-logins
    [path logins]
    (let [login-pail (Pail/create path (LoginPailStructure.))
          out        (.openWrite login-pail)]
      ;  (.writeObject out (Login. "alex" 123456789231))
; (.writeObject out (Login.  "bob"  135123423413))
      (doall
       (map (fn [x]
              (.writeObject out (Login.  (first x) (second x)))) logins))
      (.close out)))

  (write-logins "/tmp/mylogins2227" {"alex" 123456789231
                                     "bob"  135123423413})


; (map 
; (fn [x] (prn x) )
;  {"alex" 123456789231
;       "bob"  135123423413})

  (defn read-logins
    [path]
    (let [login-pail (Pail. path)]
      (for [l login-pail]
        (prn (.-userName l) (.-loginUnixTime l)))))

  (read-logins  "/tmp/mylogins")
  (read-logins  "/tmp/mylogins1")


  (defn append-pail
    [path path-updates]
    (let [pail         (Pail. path)
          pail-updates (Pail. path-updates)]
      (.absorb pail pail-updates)
      (.consolidate pail)))

  (append-pail "/tmp/mylogins" "/tmp/mylogins1")
  (read-logins  "/tmp/mylogins")


  (map #(prn %1) {1 2
                  2 3})


  (print-table (:member (r/reflect "com.backtype.hadoop.pail.Pail")))

  (print-table
   (sort-by :name
            (filter :exception-types (:members (r/reflect Pail)))))


  (->> com.backtype.hadoop.pail.Pail
       r/reflect
       :members
       (filter #(contains? (:flags %) :static))
       (filter #(contains? (:flags %) :public))
      ;  pp/pprint
       print-table)




  (read-logins  "/tmp/partitioned_logins6")

  HashMap
  SequenceFileFormat/CODEC_ARG

  (defn compressed-pail
    [path]
    (let [options (HashMap.)
          struct  (LoginPailStructure.)]
      (.put options SequenceFileFormat/CODEC_ARG SequenceFileFormat/CODEC_ARG_GZIP)
      (.put options SequenceFileFormat/TYPE_ARG SequenceFileFormat/TYPE_ARG_BLOCK)
      (Pail/create path (PailSpec. "SequenceFile" options struct))))

  (def compressed (compressed-pail "/tmp/compressedwe2"))


  ;
  )