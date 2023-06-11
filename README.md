# webserver-clj-template
![tests](https://github.com/furiel/webserver-clj-template/actions/workflows/clojure.yml/badge.svg)

template for a clojure project with webserver

## Dependencise

### just

https://github.com/casey/just

## Features

The following features are added to the template

* ring
* reitit
* kaocha (unit tests and integration tests)
* aot
* uberjar
* github actions
* clj-kondo

## Run tests

```
just test
```

## Lint

```
just lint
```

## aot

```
just aot
```

## run

```
just run
```

## Uberjar

### Creating

```
just uberjar
```

### Running

```
java -jar target/*.jar
```
