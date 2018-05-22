#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Building and deploying LDSH
echo - ------------------------------------------------------------------------
echo

../network/build-network.sh && \
    cd install && \
    ./build-ldsh-app.sh $1 && \
    ./build-ldsh-mysql.sh && \
    ./build-ldsh-ftpd.sh && \
    ./build-ldsh-karaf.sh && \
    ./start-ldsh-mysql.sh && \
    ./start-ldsh-ftpd.sh && \
    ./start-ldsh-karaf.sh && \



echo - LDSH deployed