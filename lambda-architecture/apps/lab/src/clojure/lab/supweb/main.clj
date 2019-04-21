(ns lab.supweb.main
  (:gen-class)
  (:import 
   
   (lab.bigdata.batchlayer BatchWorkflow)
   ( jcascalog Api)
   )
  )


(defn -main [& args]
  ; (BatchWorkflow/batchWorkflow)
  (BatchWorkflow/hello)
  )


(comment
  
  (BatchWorkflow/batchWorkflow/setApplicationConf)
  
  
  )