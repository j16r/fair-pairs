(ns fair-pairs.views.main
  (:require [fair-pairs.views.common :as common]
            [noir.response :as response])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/participants" []
  (response/json
   [{:name "John Barker"}
    {:name "Zack Parness"}
    {:name "Johny Urgiles"}
    {:name "Liz Starin"}
    {:name "Rich Rosen"}
    {:name "James Pothen"}
    {:name "Scott Rogers"}
    {:name "Dave Cameron"}
    {:name "Doug McClurg"}]))

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
