@echo off
docker-compose down
docker build
docker-compose up
