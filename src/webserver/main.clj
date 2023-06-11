(ns webserver.main
  (:require
   [integrant.core :as ig]
   [webserver.config :as config])
  (:gen-class))

(defmethod ig/init-key ::main [_ _]
  (println "Hello world!"))

(defn -main [config]
  (->
   config
   config/read-config
   config/start-config)
  :done)
