#!/bin/bash
kill $(cat config-server.pid)
nohup java -Xms256m -jar config-server/target/config-server-0.1.0-SNAPSHOT.jar & echo $! > config-server.pid



