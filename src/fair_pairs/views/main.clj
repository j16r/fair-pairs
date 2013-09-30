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
    {:name "Lady Rainicorn"}
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
            [:section#today
              [:h3 "Today's Pairs"]
              [:section#pairs]
              [:div.clear]
              [:h4 "Soloing"]
              [:ul#cowboys]
              [:div.clear]
              [:h4 "Absent"]
              [:ul#sickies]
              [:div.clear]]
            [:div.clear]
            [:h3 "Available"]
            [:section#setup
             [:section
              [:ul#participants]
              [:div.clear]]]
            [:div.clear]
            [:h3 "History"]
            [:section
             [:ul#history]
             [:div.clear]]
            [:footer
             [:fieldset#control
              [:label {:for "pair"} "Name:"]
              [:input#pair]
              [:button#add_pair "Add"]]]]))
