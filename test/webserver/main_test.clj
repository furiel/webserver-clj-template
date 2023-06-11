(ns webserver.main-test
  (:require
   [webserver.main :as sut]
   [clojure.test :refer :all]))

(deftest main
  (is (= :done (sut/-main "config.edn"))))
