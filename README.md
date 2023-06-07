# webserver-clj-template
![tests](https://github.com/furiel/webserver-clj-template/actions/workflows/clojure.yml/badge.svg)

template for a clojure prject with webserver

## Dependencise

### just

https://github.com/casey/just

## Features

The following features are added to the template

* kaocha
* aot
* uberjar
* github actions
* clj-kondo

## Run tests

```
bin/kaocha
```

## Lint

```
just lint
```

## aot

```
just aot
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
