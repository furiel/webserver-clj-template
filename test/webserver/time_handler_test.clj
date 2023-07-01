(ns webserver.time-handler-test
  (:require
   [clj-time.core :as t]
   [clojure.test :refer :all]
   [org.senatehouse.expect-call :refer :all]
   [webserver.time-handler :as sut]))

(deftest time-handler
  (testing "current-time with valid TZ"
    (with-expect-call [(t/now [] (t/date-time 2023 7 2))]
      (is (= "2023-07-01T22:00:00"
             (->
              (sut/current-time-handler {:params {"TZ" "Europe/Budapest"}})
              :body)))))

  (testing "current-time without TZ"
    (let [testing-timezone (t/time-zone-for-id "Canada/Pacific")]
      (with-expect-call [(t/time-zone-for-id [_] testing-timezone)
                         (t/now [] (t/date-time 2023 7 2))]
        (is (= "2023-07-02T07:00:00"
               (->
                (sut/current-time-handler {:params {}})
                :body))))))

  (testing "current-time invalid TZ"
    (is (= 400
           (->
            (sut/current-time-handler {:params {"TZ" "not-existent"}})
               :status)))))
