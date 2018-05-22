#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Building and deploying RDSH
echo - ------------------------------------------------------------------------
echo

../network/build-network.sh && \
    cd install && \
    ./build-rdsh-app.sh $1 && \
    ./build-rdsh-mysql.sh && \
    ./build-rdsh-mosquitto.sh && \
    ./build-rdsh-karaf.sh && \
    ./start-rdsh-mysql.sh && \
    ./start-rdsh-mosquitto.sh && \
    ./start-rdsh-karaf.sh && \



echo - RDSH deployed