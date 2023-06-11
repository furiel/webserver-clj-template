(ns webserver.http-server
  (:require
   [integrant.core :as ig]
   [ring.adapter.jetty :as jetty]
   [webserver.handlers :as handlers]))

(defmethod ig/init-key ::jetty [_ opts]
  (jetty/run-jetty #'handlers/app opts))

(defmethod ig/halt-key! ::jetty [_ server]
  (.stop server))
