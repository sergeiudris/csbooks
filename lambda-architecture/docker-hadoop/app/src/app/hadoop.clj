(ns app.hadoop  
  (:require [clojure.repl :refer :all]
            [cascalog.api :as cs]
            ; [cascalog.playground :as csp]
            ; [com.backtype.hadoop.pail]
            ; [org.apache.hadoop/hadoop-core]
   ;
   )
  (:import 
   (com.backtype.hadoop.pail Pail PailStructure)
   (app.java Login LoginPailStructure)
   (app.java Login LoginPailStructure)
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
    []
    (let [
          login-pail (Pail/create "/tmp/mylogins" (LoginPailStructure.) )
          out (.openWrite login-pail)
          ]
      (.writeObject out (Login. "alex" 123456789231 ) )
      (.writeObject out (Login. "bob"  135123423413))
      (.close out)))
  
  (write-logins)
  
  (defn read-logins
    []
    (let [
          login-pail (Pail. "/tmp/mylogins")
          ]
      (for [l login-pail]
        (prn (.-userName l) (.-loginUnixTime l) ) 
        )
      ))
  
  (read-logins)

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
  ;
  )