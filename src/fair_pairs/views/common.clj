(ns fair-pairs.views.common
  (:require [noir.cljs.core :as cljs])
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "Fair Pairs"]
               (include-css "/css/reset.css")
               (include-css "/css/default.css")
               (include-css "/css/font-awesome.min.css")]
              [:body
               [:div#wrapper
                content]
               (cljs/include-scripts :with-jquery)]))
