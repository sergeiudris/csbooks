(ns io.print
  (:require [puget.printer :as pug]
            [clojure.repl :refer :all]))


(defn cprn
  "color-prints a value "
  ([x]
   (pug/cprint x nil))
  ([x opts]
   (pug/cprint x opts)))



(comment
  

  ;;;
  )