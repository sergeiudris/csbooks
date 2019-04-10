(ns app.superwebanalytics  
  (:require [clojure.repl :refer :all]
            [cascalog.api :as cs]
   [clojure.reflect :as r]
   [clojure.pprint :refer [print-table] :as pp]
   ;
   )
  (:import 
   (com.backtype.hadoop.pail Pail PailStructure SequenceFileFormat PailSpec)
   (app.java Login LoginPailStructure PartitionedLoginPailStructure)
   
   (org.apache.hadoop.conf Configuration)
   (manning.tap SplitDataPailStructure ThriftPailStructure)
   (org.apache.hadoop.hdfs DistributedFileSystem)
   (java.net URI)
   (java.util HashMap)
   
   (org.apache.hadoop.fs FSDataInputStream FSDataOutputStream FileSystem Path LocalFileSystem)
  ;  (org.apache.hadoop.io SequenceFile)
   
   )
  )

(comment
 
  SplitDataPailStructure
  ;
  )