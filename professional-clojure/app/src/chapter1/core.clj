(ns chapter1.core
  (:require [clojure.repl :refer :all]
            [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]
            ))

  (defn hi [] "hi")


(comment

  (hi)

  ;; ch1 p3
  ; substitution model for procedure applications
  
  (defn square [a] (* a a))
  (defn sum-of-squares [a b] (+ (square a) (square b)))

  (sum-of-squares 4 5)
  (+ (square 4) (square 5))
  (+ (* 4 4) (* 5 5))
  (+ 16 25)
  41


  ;; fib and memoization
  
  (defn fib [n]
    (cond
      (= n 0) 0
      (= n 1) 1
      :else (+ (fib (- n 1))
               (fib (- n 2)))))

  (time (fib 5))

  (time (fib 42))

  (def memoized-fib
    (memoize (fn [n]
               (cond
                 (= n 0) 0
                 (= n 1) 0
                 :else (+ (fib (- n 1))
                          (fib (- n 2)))))))

  (time (memoized-fib 38))

  ;; thinking recursively
  
  (defn factorial [n]
    (if (= n 1)
      1
      (* n (factorial (- n 1)))))

  (factorial 6)

  (factorial 6)
  (* 6 (factorial 5))
  (* 6 (* 5 (factorial 4)))
  (* 6 (* 5 (* 4 (factorial 3))))
  (* 6 (* 5 (* 4 (* 3 (factorial 2)))))
  (* 6 (* 5 (* 4 (* 3 (* 2 (factorial 1))))))
  (* 6 (* 5 (* 4 (* 3 (* 2 1)))))
  (* 6 (* 5 (* 4 (* 3 2))))
  (* 6 (* 5 (* 4 6)))
  (* 6 (* 5 24))
  (* 6 120)
  720

  (defn factorial2 [n]
    (loop [count n acc 1]
      (if (zero? count)
        acc
        (recur (dec count) (* acc count)))))

  (factorial2 6)

  (factorial2 6)
  (loop 6 1)
  (loop 5 6)
  (loop 4 30)
  (loop 3 120)
  (loop 2 360)
  (loop 1 720)
  720

  ;; mutual recursion
  
  (declare my-odd? my-even?)

  (defn my-odd? [n]
    (if (= n 0)
      false
      (my-even? (dec n))))

  (defn my-even? [n]
    (if (= n 0)
      true
      (my-odd? (dec n))))

  (my-even? 2)
  (my-odd? 3)


  (defn my-odd? [n]
    (if (= n 0)
      false
      #(my-even? (dec n))))

  (defn my-even? [n]
    (if (= n 0)
      true
      #(my-odd? (dec n))))

  (my-odd? 3)
  (my-even? 2)

  (trampoline my-even? 2)

  (defn my-even? [n]
    (letfn [(e? [n]
              (if (= n 0)
                true
                #(o? (dec n))))
            (o? [n]
                (if (= n 0)
                  false
                  #(e? (dec n))))]
      (trampoline e? n)))

  (defn my-odd? [n]
    (not (my-even? n)))

  (my-even? 4)
  (my-odd? 4)

  (def customers [{:state "CA" :name "Todd"}
                  {:state "MI" :name "Jeremy"}
                  {:state "CA" :name "Lisa"}
                  {:state "NC" :name "Rich"}])
  (filter #(= "CA" (:state %)) customers)

  ;; command pattern
  
  (defn wrapTransaction [f]
    (do
      (startTransaction)
      (f)
      (completeTransaction)))

  (wrapTransaction #((do
                       (add order user)
                       (addLineItemsFrom cart orderKey))))


 ;; builder pattern vs partail
  
  (def add2 (partial + 2))

  
  (def spec {:classname "org.postgresql.Driver"
             :subprotocol "postgresql"
             :subname "//localhost:5432/sampledb"})

  (defn all-users []
    (jdbc/query spec ["select * from login order by username desc"])
    )
  
  (defn find-user [username]
    (jdbc/query spec ["select * from login where username = ?" username])
    )
  
  (defn create-user [username password]
    (jdbc/insert! spec :login {:username username :password password :salt "some_salt"})
    )
  
  ;; to  much repetition above, use partial
  
  (def query (partial jdbc/query spec))
  
  (def insert! (partial jdbc/insert! spec))
  
  (defn all-users []
    (query ["select * from login order by username desc"]))
  
  (defn find-user [username]
    (query ["select * from login where username = ?" username]))
  
  (defn create-user [username password]
    (insert! :login {:username username :password password :salt "some_salt"}))

  (defn apply-sales-tax [items]
    ((map (partial * 1.06) items))
    )
  
  ;; function composition
  
  (defn minify [input]
    (str/join (map str/trim (str/split-lines input) ))
    )

  (def minify (comp str/join (partial map str/trim) str/split-lines ) )

  ;; embracing laziness
  
  (def result (map (fn [i] (println "." i) (inc i)) '[0 1 2 3] ))
  
  result

  (println ".")

  (def fib-seq
    (lazy-cat [1 1] (map + (rest fib-seq) fib-seq))
    )

  (take 10 fib-seq)

  (def names '["Christia" "Arline" "Bethann" "Keva" "Arnold" "Germaine"
               "Tanisha" "Jenny" "Erma" "Magdalen" "Carmelia" "Joana"
               "Violeta" "Gianna" "Shad" "Joe" "Justin" "Donella"
               "Raeann" "Karoline"])
  
  (mapv #(vector %1 %2) (cycle '[:first :second :third :fourth]) names )
  
  (doc cycle)
  (doc mapv)
  
  ;; atoms
  
  (def app-state (atom {}))

  app-state

  (swap! app-state assoc :current-user "Jeremy")
  app-state
  (swap! app-state assoc :session-id "some-session-id")
  app-state
  (reset! app-state {})
  app-state
  (swap! app-state assoc :current-user "Jeremy" :session-id "some-session-id")
  (deref app-state)
  (:session-id @app-state)
  (:foo @app-state :not-found)
  
  ;; concurrency and Software Transactional Memory
  (def savings (atom {:balance 500}))
  (def checking (atom {:balance 250}))
  (do
    (swap! checking assoc :balance 700)
    (throw (Exception. "oops..."))
    (swap! savings assoc :balance 50)
    )
  (:balance @checking)
  (:balance @savings)
  
  (def checking (ref {:balance 500}))
  (def savings (ref {:balance 250}))

  (dosync
   (commute checking assoc :balance 700)
   (throw (Exception. "oops..."))
   (commute savings assoc :balance 50)
   )
  (:balance @checking)
  (:balance @savings)

  
  

  )