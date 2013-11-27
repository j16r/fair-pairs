(defproject fair-pairs "0.1.0-SNAPSHOT"
  :description "Fair pairs"
  :url "http://github.com/excepttheweasel/fair-pairs"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir-cljs "0.3.7"]
                 [jayq "2.4.0"]
                 [fetch "0.1.0-alpha2" :exclusions [org.clojure/clojure]]
                 [crate "0.2.3"]
                 [lib-noir "0.6.8"]]
  :cljsbuild {:builds [{}]}
  :main ^{:skip-aot true} fair-pairs.server)
