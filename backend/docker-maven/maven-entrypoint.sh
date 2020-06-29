#!/bin/sh

set -xe

mvn package && \
mv /usr/src/app/target/MAJ-1.0-SNAPSHOT.jar /dist/MAJ-1.0-SNAPSHOT.jar
