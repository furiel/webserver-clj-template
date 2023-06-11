(ns dev.user
  (:require
   [integrant.core :as ig]
   [integrant.repl :as ig-repl]
   [webserver.config :as config]))

(integrant.repl/set-prep!
 #(ig/prep
   (config/read-config "config.edn")))

(defn reload []
  (ig-repl/reset))
