#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting AWISC
echo - ------------------------------------------------------------------------
echo

../network/build-network.sh && \
    cd restart && \
    ./restart-awisc-mysql.sh && \
    ./restart-awisc-elasticsearch.sh && \
    ./restart-awisc-karaf.sh && \

echo - AWISC started