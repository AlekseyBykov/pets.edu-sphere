#!/bin/bash

set -e

echo "Building Docker images with Jib..."

mvn compile jib:dockerBuild -pl profiles-service -am
mvn compile jib:dockerBuild -pl courses-service -am

echo "Starting containers with Docker Compose..."

docker-compose up
