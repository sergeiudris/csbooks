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
    (jdbc/query spec ["select * from login order by username desc"]))

  (defn find-user [username]
    (jdbc/query spec ["select * from login where username = ?" username]))

  (defn create-user [username password]
    (jdbc/insert! spec :login {:username username :password password :salt "some_salt"}))

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
    ((map (partial * 1.06) items)))

  ;; function composition
  
  (defn minify [input]
    (str/join (map str/trim (str/split-lines input))))

  (def minify (comp str/join (partial map str/trim) str/split-lines))

  ;; embracing laziness
  
  (def result (map (fn [i] (println "." i) (inc i)) '[0 1 2 3]))

  result

  (println ".")

  (def fib-seq
    (lazy-cat [1 1] (map + (rest fib-seq) fib-seq)))

  (take 10 fib-seq)

  (def names '["Christia" "Arline" "Bethann" "Keva" "Arnold" "Germaine"
               "Tanisha" "Jenny" "Erma" "Magdalen" "Carmelia" "Joana"
               "Violeta" "Gianna" "Shad" "Joe" "Justin" "Donella"
               "Raeann" "Karoline"])

  (mapv #(vector %1 %2) (cycle '[:first :second :third :fourth]) names)

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
    (swap! savings assoc :balance 50))
  (:balance @checking)
  (:balance @savings)

  (def checking (ref {:balance 500}))
  (def savings (ref {:balance 250}))

  (dosync
   (commute checking assoc :balance 700)
   (throw (Exception. "oops..."))
   (commute savings assoc :balance 50))
  (:balance @checking)
  (:balance @savings)


  ;; nil punning
  
  (if nil "true" "false")

  ; nil can be treated like an empty seq
  
  (first nil)
  (last nil)
  (second nil)

  (seq? nil)

  (if '() "true" "false")
  (if '[] "true" "false")
  (if '{} "true" "false")


  (:foo {:foo nil :bar "baz"})
  (:foo {:foo nil :bar "baz"} :not-found)
  (:foo {:bar "baz"} :not-found)


  ;; polymorphic dispatch and defmulti
  
  (defmulti area
    (fn [shape & _]
      shape))

  (defmethod area :triangle
    [_ base height]
    (/ (* base height) 2))

  (defmethod area :square
    [_ side]
    (* side side))

  (defmethod area :rectangle
    [_ length width]
    (* length width))

  (defmethod area :circle
    [_ radius]
    (* radius radius Math/PI))

  (area :square 5)
  (area :triangle  3 4)
  (area :circle 5)

  ;; surcharge example of using multimethods
  
  (def invoice {:id 42
                :issue-date #inst "2016-01-01"
                :due-date #inst "2016-02-01"
                :customer {:name "Foo Bar Industries"
                           :address "123 Main St"
                           :city "New York"
                           :state "NY"
                           :zipcode "10101"}
                :amount-due 5000})

  (defmulti calculate-final-invoice-amount (fn [invoice]
                                             (get-in invoice [:customer :state])))

  (defmethod calculate-final-invoice-amount "CA" [invoice]
    (let [amount-due (:amount-due invoice)]
      (+ amount-due (* amount-due 0.05))))

  (defmethod calculate-final-invoice-amount "NY" [invoice]
    (let [amount-due (:amount-due invoice)]
      (+ amount-due (* amount-due 0.045))))

  (defmethod calculate-final-invoice-amount :default [invoice]
    (:amount-due invoice))

  (defprotocol Shape
    (calc-area [this])
    (perimeter [this])
    (hello [this y]))

  (defrecord Rectangle [width length]
    Shape
    (calc-area [this] (* (:width this) (:length this)))
    (perimeter [this] (+ (* 2 (:width this)) (* 2 (:length this)))))

  (defrecord Square [side]
    Shape
    (calc-area [this] (* (:side this) (:side this)))
    (perimeter [this]  (* 4 (:side this)))
    (hello [this y] y))

  (def sq1 (->Square 4))


  (calc-area sq1)

  (def rect1 (->Rectangle 4 2))

  (calc-area rect1)

  (def sq2 (map->Square {:side 3}))

  (calc-area sq2)

  (def rect2 (map->Rectangle {:width 4 :length 7}))

  (calc-area rect2)
  (into {} rect2)
  (:width rect2)
  (:length rect2)
  (:foo rect2 :not-found)


  ;; reify
  
  (def some-shape
    (reify Shape
      (calc-area [this] "I calculate area")
      (perimeter [this] "I calculate perimeter")))

  (calc-area some-shape)


  (doc declare)
  (declare Node)
  (declare ->Node)


  (defprotocol INode
    (entry [_])
    (left [_])
    (right [_])
    (contains-value? [_ _])
    (insert-value [_ _]))

  (deftype Node [value left-branch right-branch]
    INode
    (entry [_] value)
    (left [_] left-branch)
    (right [_] right-branch)
    (contains-value? [tree v]
      (cond
        (nil? tree) false
        (= v value) true
        (< v value) (contains-value? left-branch v)
        (> v value) (contains-value? right-branch v)))
    (insert-value [tree v]
      (cond
        (nil? tree) (->Node v nil nil)
        (= v value) tree
        (< v value) (->Node value (insert-value left-branch v) right-branch)
        (> v value) (->Node value left-branch (insert-value right-branch v)))))

  (def root (Node. 7 nil nil))

  (left root)
  (right root)
  (entry root)
  (contains-value? root 7)

  (contains-value? root 5)

  (extend-protocol INode
    nil
    (entry [_] nil)
    (left [_] nil)
    (right [_] nil)
    (contains-value? [_ _] false)
    (insert-value [_ value] (Node. value nil nil)))

  (deftype Node [value left-branch right-branch]
    INode
    (entry [_] value)
    (left [_] left-branch)
    (right [_] right-branch)
    (contains-value? [tree v]
      (cond
        (= v value) true
        (< v value) (contains-value? left-branch v)
        (> v value) (contains-value? right-branch v)))
    (insert-value [tree v]
      (cond
        (= v value) tree
        (< v value) (Node. value (insert-value left-branch v) right-branch)
        (> v value) (Node. value left-branch (insert-value right-branch v)))))

  (contains-value? root 5)

  (def root (Node. 7
                   (Node. 5
                          (Node. 3 nil nil)
                          nil)
                   (Node. 12
                          (Node. 9 nil nil)
                          (Node. 17 nil nil))))


  (left root)

  (entry  (left root))
  (entry  (left (left root)))
  (entry (right root))

  (entry (right (rigth root)))

  (identity (left root))
  ;; #object[user.Node 0x5cedcfe8 "user.Node@5cedcfe8"]
  (identity (right root))
  ;; #object[user.Node 0x124ee325 "user.Node@124ee325"]
  
  ;; create new list by inserting 6
  (def l (insert-value root 6))

  ;; check the identity of nodes from new list - right is the same
  (identity (left l))
  ;; #object[user.Node 0x167286ec "user.Node@167286ec"]
  (identity (right l))
  ;; #object[user.Node 0x124ee325 "user.Node@124ee325"]
  
  (defprotocol Palindrome 
    (is-palindrome? [object]))
  
  (extend-type java.lang.String
    Palindrome
    (is-palindrome? [s]
      (= s (apply str (reverse s)))
      )
    )

  (is-palindrome? "tacocat")  



  "
   Clojure is different. If you don't come at it with a clear mind and learn
   how to do things the Clojure way, you'll simply be writing the same old code in a different syntax
  "

  (defn x [] "x")

  )

