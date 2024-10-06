#!/bin/bash
mvn -Dmaven.test.skip=true clean package
docker compose build --no-cache
docker compose up -d

