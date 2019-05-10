(ns plants.mx.colors
  (:require [clojure.repl :refer :all]
   [plants.mx.core :refer :all :as plants]))


(comment

  (def A (plants/mkmx [[1 0 2]
                       [-1 3 4]
                       [7 1 0]]))

  (plants/system-of-linear-equations  [1 2 3] A)

  (def B (plants/mk-square-mx 3 1))

  (plants/det-leibniz B)

  (->
   (plants/mk-square-mx 3 1)
   (plants/multiply-vec [1 2 3])

   (plants/prnmx 3))

  (->
   (plants/mk-square-mx 3 0)
   (plants/multiply-vec [1 2 3])

   (plants/prnmx 3))

  (plants/vec-unit [1 2 3])

  (def I (plants/iden 3))
  (def q [17,246,239])
  (def q-unit (plants/vec-unit q))

  (def Aq (plants/multiply-vec I q))

  (def qM (mkmx [[17 0 0
                  0 246 0
                  0 0 239]]))


  (def Aq (multiply qM I 3 3))

  (def U (mk-square-mx 3 1))
  (def Aq (multiply qM U 3 3))

  (def qU (multiply q-unit U 1 3))

  (def qI (multiply q-unit I 1 3))

  (def yellow [[255 250 30]
               [240 243 20]
               [238 255 10]])

  (def teal [[10 251 248]
             [15 240 242]
             [30 243 254]])

  (def magenta [[238 12 250]
                [239 21 253]
                [248 30 245]])

  (def red [[255 2 3]
            [250 10 9]
            [247 8 11]])

  (nth red 2)

  (def Y (->>
          (reduce (fn [acc x]
                    (multiply (vec-unit x) acc 1 3)) U  yellow)
          vec))

  (prnmx Y 3)


  (det-leibniz Y)
  (det-leibniz U)

  (inverse Y)

  (system-of-linear-equations q-unit Y)

  (def R (mkmx [[1 1 1]
                [2 2 2]
                [3 3 3]]))

  (det-leibniz R)

  (def M (->>
          (reduce (fn [acc x]
                    (multiply (vec-unit x) acc 1 3)) I  magenta)
          vec))

  (def U2 (mk-square-mx 3 0.5))

  (det-leibniz U2)

  (def U3 (mkmx [[1 2 0]
                 [3 4 -1]
                 [0 2 -2]]))


  (= (det-leibniz U3) (det-leibniz (transpose U3 3)))

  (def M2 (->
           (reduce (fn [acc x]
                     (prnmx acc 3)
                     (prn (det-leibniz acc))
                     (multiply (vec-unit x) acc 1 3)) U3  magenta)

           vec))
  (prnmx M2 3)

  (system-of-linear-equations q-unit M2)

  (->>
   (multiply  M2 q-unit 3 1)
   det-leibniz)

  (inverse M2)
  (det-leibniz M2)




  ;;;
  )
