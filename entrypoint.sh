#!/bin/bash

case ${1} in
  start)
    while ! nc -z college-app-tracker-db 5432; do sleep 3; done
    cd "/app"
    java -XX:+UseG1GC -Xmx2G -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=50 -Dsun.net.inetaddr.ttl=0 -jar college-app-tracker.jar
    ;;
  help)
    echo "Available options:"
    echo " start        - Starts the service"
    echo " http|https://config/url/location  - url to config json"
    echo " help         - Displays the help"
    echo " [command]    - Execute the specified command, eg. bash."
    ;;
esac