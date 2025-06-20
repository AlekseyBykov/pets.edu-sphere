#!/bin/bash

set -e

echo "Setting Testcontainers JVM options..."
export JAVA_TOOL_OPTIONS="-Dtestcontainers.ryuk.disabled=true -Dtestcontainers.reuse.enable=true"

echo "Running tests for all modules..."
mvn clean verify

echo "Building Docker images with Jib..."

mvn compile jib:dockerBuild -pl config-server
mvn compile jib:dockerBuild -pl discovery-server
mvn compile jib:dockerBuild -pl profiles-service -am
mvn compile jib:dockerBuild -pl courses-service -am
mvn compile jib:dockerBuild -pl content-service -am

echo "Starting containers with Docker Compose..."

docker-compose up
