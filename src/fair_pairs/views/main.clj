(ns fair-pairs.views.main
  (:require [fair-pairs.views.common :as common]
            [noir.response :as response])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/participants" []
  (response/json
   [{:name "Jake"}
    {:name "Finn"}
    {:name "Ice King"}
    {:name "Princess Bubblegum"}
    {:name "Laidy Rainicorn"}
    {:name "Marceline"}
    {:name "LSP"}
    {:name "Beemo"}
    {:name "Gunter"}
    {:name "Earl of Lemongrab"}
    {:name "Flame Princess"}
    {:name "Peppermint Butler"}
    {:name "Tree Trunks"}
    {:name "Fionna"}
    {:name "Cake"}
    {:name "Me-Mow"}]))

(defpage [:post "/participants"] {:keys [name]}
  (response/status 201))

(defpage "/pairs" []
  (response/json
    []))

(defpage "/" []
         (common/layout
           [:article
            [:h3 "Today's Pairs"]
            [:section#pairs]
            [:h3 "Available Pairs"]
            [:section#setup
             [:section]
             [:section#participants]]
            [:h3 "Pair History"]
            [:section#history]
            [:footer
             [:fieldset#control
              [:label {:for "pair"} "Pair name:"]
              [:input#pair]
              [:button#add_pair "Add"]]]]))
