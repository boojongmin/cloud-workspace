#!/bin/bash
cd api-service
mvn -Dmaven.test.skip=true package
cd ..
kill $(cat api-service.pid)
nohup java -Xms256m -jar api-service/target/api-service-0.0.1-SNAPSHOT.jar & echo $! > api-service.pid



