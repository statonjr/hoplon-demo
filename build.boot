#!/usr/bin/env boot

(merge-env! :source-paths #{"src"}
            :target-path "target"
            :dependencies '[[tailrecursion/hoplon "6.0.0-SNAPSHOT"]
                            [org.clojure/clojurescript "0.0-2850"]

                            [tailrecursion/boot-hoplon "0.1.0-SNAPSHOT" :scope "test"]
                            [adzerk/boot-cljs          "0.0-2814-1"     :scope "test"]
                            [adzerk/boot-cljs-repl     "0.1.9"          :scope "test"]
                            [adzerk/boot-reload        "0.2.4"          :scope "test"]
                            [boot-garden               "1.2.5-2"        :scope "test"]
                            [pandeiro/boot-http        "0.6.2"          :scope "test"]])

(require   '[adzerk.boot-cljs             :refer [cljs]]
           '[adzerk.boot-cljs-repl        :refer [cljs-repl start-repl]]
           '[adzerk.boot-reload           :refer [reload]]
           '[tailrecursion.boot-hoplon    :refer [hoplon]]
           '[boot-garden.core             :refer [garden]]
           '[pandeiro.boot-http           :refer [serve]])

(deftask development
  "Build hoplon-demo for development."
  []
  (comp
   (serve :dir (get-env :target-path)
          :port 5000
          :httpkit true)
   (watch :verbose true)
   (garden :styles-var 'stylesheet/screen)
   (hoplon :pretty-print true)
   (cljs :optimizations :none
         :source-map true)))
