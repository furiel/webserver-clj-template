(ns webserver.handlers
  (:require
   [reitit.http :as http]
   [reitit.http.interceptors.parameters :refer [parameters-interceptor]]
   [reitit.interceptor.sieppari :as sieppari]
   [reitit.ring :as ring]
   [ring.util.response :as response]
   [webserver.time-handler :as time-handler]))

(def app
  (http/ring-handler
   (http/router
    [["/ping"
      {:get {:handler (constantly (response/response "pong"))}}]

     ["/api"
      {:interceptors [(parameters-interceptor)]}
      ["/current-time"
       {:get {:handler time-handler/current-time-handler}}]]])

   (ring/create-default-handler
    {:not-found (constantly {:status 404, :body "404 not found"})
     :method-not-allowed (constantly {:status 405, :body "405 not allowed"})
     :not-acceptable (constantly {:status 406, :body "406 not acceptable"})})

   {:executor sieppari/executor}))
