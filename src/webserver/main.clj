(ns webserver.main
  (:require
   [integrant.core :as ig]
   [webserver.config :as config])
  (:gen-class))

(defmethod ig/init-key ::main [_ _]
  (println "Hello world!"))

(defn -main [config]
  (let [state (->
               config
               config/read-config
               config/start-config)]
    (.addShutdownHook (Runtime/getRuntime)
                      (Thread.
                       (fn []
                         (config/stop-config state))))
    :done))
