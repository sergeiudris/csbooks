(ns chapter-6.user
  "Database functions for user operations"
  (:require [datomic.api :as d]
            [crypto.password.bcrypt :as password]))


(defn entity
  "Returns user entity from :db/id, string login, or arg if already
 an entity."
  [db user]
  (cond (instance? datomic.query.EntityMap user) user
        (string? user) (d/entity db [:user/login user])
        :else (d/entity db user)))


(defn id
  "Returns :db/id for a user when passed an entity, login, or long id."
  [db user]
  (:db/id (entity db user)))

(defn check-login
  "Checks if login and password are correct, and if so returns the
 user entity."
  [db login password]
  (when-let [user (d/entity db [:user/login login])]
    (when (password/check password (:user/password user))
      user)))


(defn login-available?
  "Checks if a login is available. Returns false if already used by a
 user."
  [db login]
  (nil? (d/entity db [:user/login login])))


(defn email-available?
  "Checks if an email address is available. Returns false if already
 used by a user."
  [db email]
  (nil? (d/q '[:find ?user .
               :in $ ?email
               :where [?user :user/email ?email]]
             db email)))

(def add
  (d/function {:lang "clojure"
               :params '[x y]
               :code "(+ x y)"}))


(defn create
  "Attempts to create a new user entity with given login, password,
 and email. If paid? is true, creates a paid account and link to the
 user, otherwise creates a free account. Returns the transaction
 data if succesful. Will throw an exception if the login or email
 already belong to another user."
  ([conn login password email]
   (create conn login password email false))
  ([conn login password email paid?]
   (let [tempid (d/tempid :db.part/account)
         user-tx [{:db/id tempid
                   :user/email email
                   :user/password (password/encrypt password)
                   :user/account {:account/type (if paid?
                                                  :account.type/paid
                                                  :account.type/free)}}
 ;; db function ensures login doesn't exist already
                  [:add-identity tempid :user/login login]]]

     @(d/transact conn user-tx))))

(defn add-transaction
  "Takes a conn, user or account entity, transaction type (ident of
 the enum), and amount, and adds the transaction to the user's
 account. Returns the transaction data."
  [conn user-or-account trans-type amount]
  (let [account (or (:user/account user-or-account) user-or-account)
        amount (bigdec amount)
        charge-tx [{:db/id (d/tempid :db.part/account)
                    :transaction/type trans-type
                    :transaction/amount amount
                    :account/_transaction (:db/id account)}
                   [:account/update-balance (:db/id account) amount]]]
    @(d/transact conn charge-tx)))


(comment
  
  (add 1 2)
  
  )