(ns webserver.config
  (:require [integrant.core :as ig]))

(defn read-config [cfg]
  (ig/read-string (slurp cfg)))

(defn start-config [ig-config]
  (ig/load-namespaces ig-config)
  (ig/init ig-config))
