(ns lobos.config
  (:use lobos.connectivity))

; FIXME: This duplicates what's in fair-pairs.db, but Korma's
; defdb doesn't seem to produce a map compatible with lobos :/
(def db
     {:classname "org.postgresql.Driver"
      :subprotocol "postgresql"
      :user "fairpairer"
      :subname "//192.168.0.2:5432/fairpairs"})
