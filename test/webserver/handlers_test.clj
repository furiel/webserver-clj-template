(ns webserver.handlers-test
  (:require
   [webserver.handlers :as sut]
   [clojure.test :refer :all]))

(deftest app
  (testing "app"
    (is (= "PONG" (-> (sut/app {})
                      :body)))))
