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
(def $pairs ($ :#pairs))
(def $participants ($ :#participants))
(def $sickies ($ :#sickies))
(def $cowboys ($ :#cowboys))

; Shuffling participants around
(defn anchor-participant [$participant]
  (.append $pairs
           (.append ($ "<li>")
                    (.addClass (.detach $participant) "anchor"))))

(defn pair-participant [$participant]
  (let [$participant (.detach $participant)
        $first-available-pair ($ "#pairs > li:not(.paired):first")]
    (if (= (aget $first-available-pair "length") 1)
      (-> $first-available-pair
        (.addClass "paired")
        (.append $participant))
      (.append $pairs (.append ($ "<li>") $participant)))))

(defn unpair-participant [$participant]
  (let [$container (.parent $participant "li")]
    (.append $participants (.detach $participant))
    (if (.hasClass $container "paired")
      (.removeClass $container "paired")
      (.remove $container))))

(defn solo-participant [$participant]
  (.append $cowboys (.detach $participant)))

(defn absent-participant [$participant]
  (.append $sickies (.detach $participant)))

; Adding a participant
(defn add-participant [name]
  (jq/ajax {:method "POST"
            :url "/participants"
            :success (fn [xhr] )}))


; Partials
(defpartial participant [participant]
  [:li.participant (aget participant "name")
   [:i.anchor.icon-anchor.icon-2x]
   [:i.pair.icon-group.icon-2x]
   [:i.solo.icon-user.icon-2x]
   [:i.absent.icon-beer.icon-2x]
   [:i.unpair.icon-remove.icon-2x]])
(defpartial setup [participants]
   [:ul
    (map participant participants)])
(defpartial history [pairs]
  [:li
   [:span "Jake"]
   [:span "Finn"]])

; Event handlers
(defn participant-event-handler [handler]
  (fn [event]
    (do
      (.preventDefault event)
      (handler (.closest ($ (aget event "target")) ".participant")))))
(delegate $body "#setup .anchor" :click
          (participant-event-handler anchor-participant))
(delegate $body "#setup .pair" :click
          (participant-event-handler pair-participant))
(delegate $body "#setup .solo" :click
          (participant-event-handler solo-participant))
(delegate $body "#pairs .unpair" :click
          (participant-event-handler unpair-participant))
(delegate $body "#pairs .absent" :click
          (participant-event-handler absent-participant))

; DOM ready
(ready
  (bind ($ :#add_pair) :click
        (fn [event] (add-participant (.val $participant-name))))
  (let-ajax [participants {:url "/participants"}]
    (append $setup (setup participants)))
  (let-ajax [pairs {:url "/pairs"}]
    (append $history (history pairs))))
