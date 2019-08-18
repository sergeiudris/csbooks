(ns pc.core
  (:require [clojure.repl :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.io InputStream File)
           (java.util Random Locale)
           (java.text MessageFormat))
  )

; (example-fn required-arg)
; (example-fn optional-arg?)
; (example-fn zero-or-more-arg*)
; (example-fn one-or-more-arg+)
; (example-fn & collection-of-variable-args)

(defn blank?
  [str]
  (every? #(Character/isWhitespace %) str))

(defrecord Person [first-name last-name])

(def foo (->Person "Aaron" "Bedra"))

; http://www.paulgraham.com/icad.html

; If you start a startup, don't design your product to please VCs or potential acquirers.
; Design your product to please the users. If you win the users, everything else will follow. 
; And if you don't, no one will care how comfortingly orthodox your technology choices were.

(def accounts (ref #{}))
(defrecord Account [id balance])

(dosync
 (alter accounts conj (->Account "CLJ" 1000.00)))

(comment
  (blank? "    ")

  (prn foo)

  (.. "hello" getClass getProtectionDomain)

  (.start (Thread. (fn [] (prn "Hello" (Thread/currentThread)))))
  
  (/ 1 0)
  (pst)
  
  (def visitors (atom #{}))
  
  (swap! visitors conj "John")
  
  (deref visitors)
  @visitors
  
  (source deref)

  
  ;
  )


;  a     a Java array
; agt    an agent
; coll   collection
; expr   an expression
; f      a function
; idx    Index
; r      a ref
; v      a vector
; val    a value

(comment
  
  (find-doc "reduce")
  
  (ancestors (class [1 2 3]))
  
  (quot 22 7) ; 3
  (rem 22 7); 1
  
  (println "another\nmultiline\nstring")
  
  foo
  
  (var foo)
  
  #'foo
  
  
  (let [[x y :as coords] [1 2 3 4 5 6]]
    (str "x: " x ", y: " y ", total dimensions " (count coords)))
  
  ;
  )


(defn ellipsize [words]
  (let [[w1 w2 w3] (str/split words #"\s+")]
    (str/join " " [w1 w2 w3 "..."])))

(comment

  (ellipsize "The quick brown fox jumps over the lazy dog.")

  (resolve 'foo)

  (.exists (File. "/tmp"))

  (def rnd (new java.util.Random))

  (def rnd (new Random))


  (. rnd nextInt)

  (. Math PI)

  (loop [result [] x 5]
    (if (zero? x)
      result
      (recur (conj result x) (dec x))))
  
  (into [] (take 5 (iterate dec 5)))
  
  (into [] (drop-last (reverse (range 6))))
  
  (vec (reverse (rest (range 6))))
  
  ;
  )

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(comment
  
  (countdown [] 5)
  
  ;
  )