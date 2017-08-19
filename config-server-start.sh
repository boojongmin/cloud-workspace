#!/bin/bash
kill $(cat config-server.pid)
kill $(cat eureka-server.pid)
nohup java -Xms256m -jar config-server/target/config-server-0.1.0-SNAPSHOT.jar & echo $! > config-server.pid
sleep 10
nohup java -Xms256m -jar eureka-server/target/eureka-server-0.1.0-SNAPSHOT.jar & echo $! > eureka-server.pid



