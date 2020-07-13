#!/bin/sh

set -xe

mvn package && \
mv /usr/src/app/target/Auth-1.0-SNAPSHOT.jar /dist/Auth-1.0-SNAPSHOT.jar
