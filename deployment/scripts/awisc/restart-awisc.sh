#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting AWISC
echo - ------------------------------------------------------------------------
echo && echo

cd restart && \
    ./restart-awisc-mysql.sh && \
    ./restart-awisc-elasticsearch.sh && \
    ./restart-awisc-karaf.sh && \

echo - AWISC started
echo - ------------------------------------------------------------------------