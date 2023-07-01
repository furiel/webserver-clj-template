(ns webserver.time-handler
  (:require
   [clj-time.core :as t]
   [clj-time.format :as f]
   [ring.util.response :as response]))

(def default-formatter (f/formatter :date-hour-minute-second))

(defn format-timestamp
  ([t] (format-timestamp default-formatter t))
  ([formatter t] (f/unparse formatter t)))

(defn current-time-handler [{:keys [params] :as _request}]
  (let [tz-param (get params "TZ")
        tz (try
             (t/time-zone-for-id tz-param)
             (catch IllegalArgumentException _
                :invalid))]
    (if (= tz :invalid)
      (response/bad-request (format "%s is not a valid timezone" tz-param))
      (response/response (format-timestamp (t/from-time-zone (t/now) tz))))))
