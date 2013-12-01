(defproject fair-pairs "0.1.0-SNAPSHOT"
  :description "Fair pairs"
  :url "http://github.com/excepttheweasel/fair-pairs"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [noir-cljs "0.3.7"]
                 [jayq "2.4.0"]
                 [fetch "0.1.0-alpha2" :exclusions [org.clojure/clojure]]
                 [crate "0.2.3"]
                 [korma "0.3.0-RC5"]
                 [lobos "1.0.0-beta1"]
                 [postgresql "9.1-901.jdbc4"]
                 [lib-noir "0.6.8"]]
  :plugins [[lein-lobos "1.0.0-beta1"]]
  :cljsbuild {:builds [{}]}
  :main ^{:skip-aot true} fair-pairs.server)
