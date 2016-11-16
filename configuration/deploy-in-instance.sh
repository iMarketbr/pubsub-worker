#!/bin/bash

APP_WORKER=pubsub-worker
WORKER_PROPERTIES=/opt/application.properties

cp -rfv /opt/bucket/$APP_WORKER/prod/production.properties $WORKER_PROPERTIES

if docker ps | awk -v app="APP_WORKER" 'NR>1{  ($(NF) == APP_WORKER )  }'; then 
    docker stop "$APP_WORKER" && docker rm -f "$APP_WORKER" 
fi

docker run --name $APP_WORKER -d -v $WORKER_PROPERTIES:$WORKER_PROPERTIES imarket/$APP_WORKER