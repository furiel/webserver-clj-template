# -*- mode: makefile -*-

aot:
    rm -rf classes
    mkdir classes
    clojure -A:aot -M -e "(compile 'webserver.main)"

uberjar: aot
    clojure -M:uberjar --main-class webserver.main

lint:
    clj -M:clj-kondo --lint src --lint test

run config="config.edn":
    clj -M -m webserver.main {{config}}

test *kaocha_opts:
    bin/kaocha {{kaocha_opts}}
