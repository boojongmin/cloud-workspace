#!/bin/bash
cd eureka-server
mvn -Dmaven.test.skip=true package
cd ..
kill $(cat eureka-server.pid)
nohup java -Xms256m -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar & echo $! > eureka-server.pid



