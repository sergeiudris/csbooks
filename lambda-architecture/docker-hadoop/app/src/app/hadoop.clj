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


  ;
  )