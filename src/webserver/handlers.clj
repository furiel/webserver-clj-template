(ns webserver.handlers
  (:require
   [ring.util.response :as response]))

(defn app [_request]
  (response/response "PONG"))
