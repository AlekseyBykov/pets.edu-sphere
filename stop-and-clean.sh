#!/bin/bash

echo "Stopping and removing containers, volumes, and orphan services..."

docker compose -p edu-sphere down -v --remove-orphans

echo "Docker Compose services stopped and cleaned"
