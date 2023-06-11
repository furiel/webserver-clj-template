(ns webserver.handlers
  (:require
   [reitit.ring :as ring]
   [ring.util.response :as response]))

(def app
  (ring/ring-handler
   (ring/router
    ["/ping"
     {:get {:handler (constantly (response/response "pong"))}}])
   (ring/create-default-handler
      {:not-found (constantly {:status 404, :body "404 not found"})
       :method-not-allowed (constantly {:status 405, :body "405 not allowed"})
       :not-acceptable (constantly {:status 406, :body "406 not acceptable"})})))
