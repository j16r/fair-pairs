(ns fair-pairs.db
  (:use [korma.db :only [defdb postgres]]
        [korma.core :only [defentity]]))

(defdb db (postgres
            {:db "fairpairs"
             :user "fairpairer"
             :host "192.168.0.2"}))

(defentity users)
