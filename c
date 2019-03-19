#!/bin/bash

export COMPOSE_CONVERT_WINDOWS_PATHS=1

# kill $(lsof -t -i:8080)
# kill -9


dc(){
    docker-compose \
    -f ./professional-clojure/docker-compose.yml \
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

caddy_install(){
  curl https://getcaddy.com | bash -s personal
}

jess(){
    docker-compose \
    -f ./jess-in-action-java-rule-based-systems/docker-compose.yml \
    "$@"
}

jessbash(){
  sh c jess exec jess bash
}


"$@"
