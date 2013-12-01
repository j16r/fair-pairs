(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defmigration add-users-table
  (up [] (create
          (table :users
            (varchar :name 100 :unique)
            (check :name (> (length :name) 1)))))
  (down [] (drop (table :users))))
