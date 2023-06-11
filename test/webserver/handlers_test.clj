(ns webserver.handlers-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [webserver.handlers :as sut]))

(deftest app
  (testing "app"
    (are [endpoint body]
        (is (= body (-> (sut/app (mock/request :get endpoint))
                        :body)))
      "/ping" "pong"
      "/not-exist" "404 not found")))
