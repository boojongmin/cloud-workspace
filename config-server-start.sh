#!/bin/bash
cd config-server
mvn -Dmaven.test.skip=true package
cd ..
kill $(cat config-server.pid)
nohup java -Xms256m -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar & echo $! > config-server.pid



