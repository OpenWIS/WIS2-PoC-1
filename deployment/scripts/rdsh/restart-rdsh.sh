#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting RDSH
echo - ------------------------------------------------------------------------
echo

../network/build-network.sh && \
    cd restart && \
    ./restart-rdsh-mysql.sh && \
    ./restart-rdsh-mosquitto.sh && \
    ./restart-rdsh-karaf.sh && 

echo - RDSH started