(ns webserver.main
  (:require
   [webserver.config :as config])
  (:gen-class))

(defn -main [config]
  (let [state (->
               config
               config/read-config
               config/start-config)]
    (.addShutdownHook (Runtime/getRuntime)
                      (Thread.
                       (fn []
                         (config/stop-config state))))))
