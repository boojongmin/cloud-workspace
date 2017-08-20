#!/bin/bash
cd cloud-client
mvn -Dmaven.test.skip=true package
cd ..
kill $(cat cloud-client.pid)
nohup java -Xms256m -jar cloud-client/target/cloud-client-0.0.1-SNAPSHOT.jar & echo $! > cloud-client.pid



