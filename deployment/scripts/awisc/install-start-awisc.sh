#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Building and deploying AWISC
echo - ------------------------------------------------------------------------
echo && echo

./install/build-awisc-app.sh $1 && \
    ./install/build-awisc-mysql.sh && \
    ./install/build-awisc-elasticsearch.sh && \
    ./install/build-awisc-karaf.sh && \
    ./install/start-awisc-mysql.sh && \
    ./install/start-awisc-elasticsearch.sh && \
    ./install/start-awisc-karaf.sh && \



echo - AWISC deployed
echo - ------------------------------------------------------------------------