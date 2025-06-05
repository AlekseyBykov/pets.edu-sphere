#!/bin/bash

PROJECT_NAME="edu-sphere"

echo "Stopping and removing containers, volumes, and network for project: $PROJECT_NAME"

docker compose -p "$PROJECT_NAME" down -v --remove-orphans

echo "Removing associated Docker volumes..."

docker volume ls --filter "name=${PROJECT_NAME}_" -q | xargs -r docker volume rm

echo "Removing associated Docker networks..."

docker network ls --filter "name=${PROJECT_NAME}_" -q | xargs -r docker network rm

echo "Optionally removing images built for $PROJECT_NAME..."

docker images --filter "reference=${PROJECT_NAME}_*" -q | xargs -r docker rmi

echo "Full purge of $PROJECT_NAME complete"
