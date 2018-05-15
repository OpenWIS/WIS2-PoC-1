#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Building and deploying AWISC
echo - ------------------------------------------------------------------------
echo && echo

cd install && \
    ./build-awisc-app.sh $1 && \
    ./build-awisc-mysql.sh && \
    ./build-awisc-elasticsearch.sh && \
    ./build-awisc-karaf.sh && \
    ./start-awisc-mysql.sh && \
    ./start-awisc-elasticsearch.sh && \
    ./start-awisc-karaf.sh && \



echo - AWISC deployed
echo - ------------------------------------------------------------------------