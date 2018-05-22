#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting LDSH
echo - ------------------------------------------------------------------------
echo

../network/build-network.sh && \
    cd restart && \
    ./restart-ldsh-mysql.sh && \
    ./restart-ldsh-ftpd.sh && \
    ./restart-ldsh-karaf.sh && 

echo - LDSH started