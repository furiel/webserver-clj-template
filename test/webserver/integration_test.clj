(ns webserver.integration-test
  (:require
   [clj-http.client :as client]
   [clj-time.core :as t]
   [clj-time.format :as f]
   [clojure.string :as string]
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
    (is (= "pong" (-> (client/get (str "http://localhost" ":" *port* "/ping"))
                      :body)))))

(deftest current-time
  (let [date-hour-prefix
        (f/unparse
         (f/formatter :date-hour)
         (t/from-time-zone (t/now) (t/time-zone-for-id "Europe/Budapest")))

        current-time (-> (client/get (str "http://localhost" ":" *port*
                                          "/api/current-time?TZ=Europe/Budapest"))
                         :body)]
    (is (string/starts-with?
         current-time date-hour-prefix))))
