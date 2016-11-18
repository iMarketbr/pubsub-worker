#!/bin/bash
INSTANCE_NAME=$(gcloud compute instance-groups list-instances pubsub-worker-group --zone us-east1-d |grep pubsub-worker-group | awk '{print $1}')
gcloud compute ssh $INSTANCE_NAME --zone us-east1-d --command "sudo /opt/deploy.sh"