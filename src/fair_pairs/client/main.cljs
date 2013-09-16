(ns fair-pairs.client.main
  (:require [noir.cljs.client.watcher :as watcher]
            [clojure.browser.repl :as repl]
            [crate.core :as crate]
            [jayq.core :as jq])
  (:use [jayq.core :only [$ append delegate bind alert]]
        [jayq.util :only [log]])
  (:use-macros 
    [jayq.macros :only [let-ajax ready]]
    [crate.def-macros :only [defpartial]]))

;;************************************************
;; Dev stuff
;;************************************************

(watcher/init)
;(repl/connect "http://localhost:9000/repl")

;;************************************************
;; Code
;;************************************************

(def $body ($ :body))
(def $history ($ :#history))
(def $setup ($ :#setup))
(def $participant-name ($ :#pair))

(defn add-participant [name]
  (log "Adding participant")
  (jq/ajax {:method "POST"
            :url "/participants"
            :success (fn [xhr] )}))
(defn anchor-participant [event]
  (log "Anchoring participant")
  (log (.remove (.closest ($ (aget event "target") :li)))))
(defn pair-participant [name]
  )
(defn solo-participant [name]
  )

(defpartial setup [participants]
   [:ul
    (map (fn [participant]
           [:li (aget participant "name")
            [:button.anchor "Anchor"]
            [:button.pair "Pair"]
            [:button.solo "Solo"]])
         participants)])

(defpartial history [pairs]
  [:ul
   [:li "John Barker + Liz Starin"]])

(delegate $body :.anchor :click
    (fn [event] (anchor-participant event)))
(delegate $body :.pair :click
    (fn [event] (pair-participant event)))
(delegate $body :.solo :click
    (fn [event] (solo-participant event)))

(ready
  (bind ($ :#add_pair) :click
        (fn [event] (add-participant (.val $participant-name))))
  (let-ajax [participants {:url "/participants"}]
    (append $setup (setup participants)))
  (let-ajax [pairs {:url "/pairs"}]
    (append $history (history pairs))))
