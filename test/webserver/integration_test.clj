(ns webserver.integration-test
  (:require
   [clj-http.client :as client]
   [clojure.test :refer :all]
   [integrant.core :as ig]
   [webserver.config :as config]))

(def ^:dynamic *port* 0)

(defn- assign-random-port [cfg]
  (assoc-in cfg [:webserver.http-server/jetty :port] 0))

(defn- get-port [state]
  (-> state
      :webserver.http-server/jetty
      (.getURI)
      (.getPort)))

(defn start-webserver [f]
  (let [state (-> "config.edn"
                  config/read-config
                  assign-random-port
                  config/start-config)]
    (try
      (with-redefs [*port* (get-port state)]
        (f))
      (finally
        (some-> state ig/halt!)))))

(use-fixtures :once start-webserver)

(deftest ping-pong
  (testing "ping-pong"
    (is (= "PONG" (-> (client/get (str "http://localhost:" *port*))
                      :body)))))
