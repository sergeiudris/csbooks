(ns app.hadoop  
  (:require   [clojure.repl :refer :all]
              [clojure.pprint :refer [print-table] :as pp]
            ; [org.apache.hadoop/hadoop-core]
   ;
              )
  (:import
   (org.apache.hadoop.conf Configuration)
   (org.apache.commons.io IOUtils)
   (org.apache.hadoop.hdfs DistributedFileSystem)
   (java.net URI)
   (java.util HashMap)
   (org.apache.hadoop.fs FSDataInputStream FSDataOutputStream FileSystem Path LocalFileSystem)
  ;  (org.apache.hadoop.io IOUtils)
   )
  )

(comment
  (doc doc)
  
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
  
  (write-file hdfsuri-file03 "abcasds2")
  
  (read-file hdfsuri-file03)
  
  
  ;
  )