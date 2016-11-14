#!/bin/bash
set -ve

git clone https://github.com/iMarketbr/pubsub-worker.git $WORKER_HOME
WORKER_PROPERTIES=/opt/application.properties

cd $WORKER_HOME
./gradlew build

SPRING_CONFIG="--spring.config.location=file://$WORKER_PROPERTIES"
exec java -jar $WORKER_HOME/build/libs/pubsub-worker.jar $SPRING_CONFIG
