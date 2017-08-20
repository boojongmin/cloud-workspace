#!/bin/bash
cd zuul-proxy
mvn -Dmaven.test.skip=true package
cd ..
kill $(cat zuul-proxy.pid)
nohup java -Xms256m -jar zuul-proxy/target/zuul-proxy-0.0.1-SNAPSHOT.jar & echo $! > zuul-proxy.pid



