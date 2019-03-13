#!/bin/bash

export COMPOSE_CONVERT_WINDOWS_PATHS=1

# kill $(lsof -t -i:8080)
# kill -9


dc(){
    docker-compose \
    -f ./datomic.yml \
    "$@"
}

up(){
  dc up -d --build
  # emacs_up
}

down(){
  dc down
  # emacs_up
}


"$@"
