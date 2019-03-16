(ns ch3shortener.storage
  (:require [clojure.repl :refer :all]
            )
  )

; create a link with the supplied ID and URL
; retrieve a link, given its ID
; update a link, given its ID and URL
; DELETE a link, given its ID
; list all known links

(defprotocol Storage
  (create-link [this id url]
    "Store the url under the id. Returns the id if successful, nil if the id is already in use"
    )
  (get-link [this id]
    "given an ID, returns the associtaed URL. Returns nil if there is no associtaed URL"
    )
  (update-link [this id new-url]
    "updates id to point to new-url. returns the id if successful, nil if the id has not yet been created"
    )
  (delete-link [this id]
    "removes a link with the given ID from storage, if exists"
    )
  (list-links [this]
    "returns a map of all known Ids to URLS"
    )
  )
