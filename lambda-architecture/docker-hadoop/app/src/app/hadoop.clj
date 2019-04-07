(ns app.hadoop  
  (:require [clojure.repl :refer :all]
            [cascalog.api :as cs]
   [clojure.reflect :as r]
   [clojure.pprint :refer [print-table] :as pp]
   
            ; [cascalog.playground :as csp]
            ; [com.backtype.hadoop.pail]
            ; [org.apache.hadoop/hadoop-core]
   ;
   )
  (:import 
   (com.backtype.hadoop.pail Pail PailStructure)
   (app.java Login LoginPailStructure PartitionedLoginPailStructure)
   (org.apache.hadoop.conf Configuration)
   (org.apache.commons.io IOUtils)
   (org.apache.hadoop.hdfs DistributedFileSystem)
   (java.net URI)
   
   (org.apache.hadoop.fs FSDataInputStream FSDataOutputStream FileSystem Path LocalFileSystem)
   )
  )


(comment
  (doc doc)
  (use 'cascalog.api)
  (use 'cascalog.playground)

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
    (let [
          login-pail (Pail/create path (LoginPailStructure.) )
          out (.openWrite login-pail)
          ]
      ;  (.writeObject out (Login. "alex" 123456789231))
; (.writeObject out (Login.  "bob"  135123423413))
      (doall
       (map (fn [x]
              (.writeObject out (Login.  (first x) (second x)))) logins)
       )
      (.close out)
      ))
  
  (write-logins "/tmp/mylogins1" {
                                 "alex" 123456789231
                                 "bob"  135123423413
                                 } )
  
  
; (map 
; (fn [x] (prn x) )
;  {"alex" 123456789231
;       "bob"  135123423413})
  
  (defn read-logins
    [path ]
    (let [
          login-pail (Pail. path)
          ]
      (for [l login-pail]
        (prn (.-userName l) (.-loginUnixTime l) ) 
        )
      ))
  
  (read-logins  "/tmp/mylogins")
  (read-logins  "/tmp/mylogins1")
  
  
  (defn append-pail
    [path path-updates]
    (let [
          pail (Pail. path)
          pail-updates (Pail. path-updates)
          ]
      (.absorb pail pail-updates)
      (.consolidate pail)
      )
    )
  
  (append-pail "/tmp/mylogins" "/tmp/mylogins1" )
  (read-logins  "/tmp/mylogins")
  

  (map #(prn %1) {1 2 2 3}  )
  
  (def hdfsuri "hdfs://namenode:8020")
  (def hdfsuri-file01 "hdfs://namenode:8020/user/joe/wordcount/input/file01")
  (def hdfsuri-file03 "hdfs://namenode:8020/user/joe/wordcount/input/file03")
  
  ; hadoop fs -cat hdfs://localhost:8020/user/joe/wordcount/input/file01
  (defn hadoop-conf
    []
      (let [conf (Configuration.) ]
            (.set conf "fs.defaultFS" hdfsuri)
            (.set conf "fs.hdfs.impl" (.getName org.apache.hadoop.hdfs.DistributedFileSystem))
            (.set conf "fs.fs.impl" (.getName org.apache.hadoop.fs.LocalFileSystem))
            (System/setProperty "HADOOP_USER_NAME" "hdfs")
            (System/setProperty "hadoop.home.dir" "/")
        conf
        ))

  (defn hdfs-fs
    []
    (let [
          conf (hadoop-conf)
          fs (FileSystem/get  (URI/create hdfsuri) conf )
          ]
      fs
      )
    )
  
  (def fs (hdfs-fs) )
  
  (.getWorkingDirectory fs)
  
  ; https://mvnrepository.com/artifact/org.apache.hadoop
  ; https://creativedata.atlassian.net/wiki/spaces/SAP/pages/52199514/Java+-+Read+Write+files+with+HDFS
  (defn read-file
    [path]
    (let [
          hdfs-path (Path. path)
          is (.open fs hdfs-path)
          out (. IOUtils toString is "UTF-8")
          ]
      (prn out)
      (.close is)
      )
    )
  
  (read-file hdfsuri-file01)
  
  (defn write-file
    [path content]
     (let [hdfs-path (Path. path)
           os        (.create fs hdfs-path)
           ]
       (.writeBytes os content)
       (.close os)
       )
    )
  
  (write-file hdfsuri-file03 "abc")
  
  (read-file hdfsuri-file03)
  
  ; https://livebook.manning.com/#!/book/big-data/about-this-book/2
  (defn partition-data
    [path]
    (let 
     [pail (Pail/create path (PartitionedLoginPailStructure.))
      os (.openWrite pail)
      ]
      (.writeObject os (Login. "chris" 1352702020) )
      (.writeObject os (Login. "david" 1352788472))
      (.close os)
      (prn "done")
      )
    )
  
  (print-table (:member (r/reflect "com.backtype.hadoop.pail.Pail")) )
  
  (print-table
   (sort-by :name
            (filter :exception-types (:members (r/reflect Pail)))))
  
  
  (->> com.backtype.hadoop.pail.Pail 
     r/reflect 
     :members 
        (filter #(contains? (:flags %) :static ))
        (filter #(contains? (:flags %) :public ))
      ;  pp/pprint
     print-table
       
       )
  
  
  
  (partition-data "/tmp/partitioned_logins5")
  
  (read-logins  "/tmp/partitioned_logins5")
  
  
  ;
  )