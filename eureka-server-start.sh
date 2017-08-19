#!/bin/bash
kill $(cat eureka-server.pid)
nohup java -Xms256m -jar eureka-server/target/eureka-server-0.1.0-SNAPSHOT.jar & echo $! > eureka-server.pid



