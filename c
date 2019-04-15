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


schemer(){
    docker-compose \
    -f ./little-seasoned-schemer/docker-compose.yml \
    "$@"
}



ml(){
    docker-compose \
    -f ./clojure-for-machine-learning/docker-compose.yml \
    "$@"
}

mlup(){
  ml up -d --build
}

mldown(){
  ml down
}


sicp(){
    docker-compose \
    -f ./structure-and-interpretation-of-computer-programs2/docker-compose.yml \
    "$@"
}

sicpup(){
  sicp up -d --build
}

sicpdown(){
  sicp down
}


tools(){
    docker-compose \
    -f ./_tools/docker-compose.yml \
    "$@"
}


toolsup(){
  tools up -d --build
}

toolsdown(){
  tools down
}

jltoken(){
  tools logs jupyterlab
}


"$@"
