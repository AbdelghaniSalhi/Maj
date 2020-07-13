#!/bin/sh

set -e

build() {
  sudo docker-compose up --build maven
}

restart() {
  sudo docker-compose restart back
}

logs() {
  sudo docker-compose logs -f back
}

clear() {
  sudo docker-compose down -v --remove-orphans
  sudo docker volume prune --force
  sudo docker image prune --force
}

up() {
  build
  sudo docker-compose up -d bdd
  sudo docker-compose up --no-start back
  restart
}

deploy() {
  cd ~/Cash_Manager_Back
  git pull
  up
}

apk() {
  if [ ! -d "../Cash_Manager_Front" ]; then
    echo "Folder cash manager doesn't exists! Downloading..."
    git clone git@github.com:Arnoways/Cash_Manager_Front.git ../Cash_Manager_Front || git clone https://github.com/Arnoways/Cash_Manager_Front.git ../Cash_Manager_Front
  fi
  docker-compose build gradle
  docker-compose up gradle
}

help() {
  echo "usage: ./cm_back.sh [option]"
  echo "available options:"
  echo "build\t\tre-compiles the code, should be followed by a restart."
  echo "restart\t\trestarts the java container - is needed to take modifications into account."
  echo "up\t\ttriggers build then a restart."
  echo "logs\t\tgets java's logs."
  echo "clear\t\tWARNING: hard reset on everything. Kills containers, volumes (including database data), images and kittens."
  echo "help\t\tdisplays this message."
  echo "apk\t\tbuilds front code into an apk."
  exit 0
}

if [ $# -eq 0 ]
  then
    help
fi

case $1 in
  build|restart|logs|clear|up|help|deploy|apk) $1;;
  *) help;;
esac
