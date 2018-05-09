#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting AWISC
echo - ------------------------------------------------------------------------
echo && echo

./restart/restart-awisc-mysql.sh && \
    ./restart/restart-awisc-elasticsearch.sh && \
    ./restart/restart-awisc-karaf.sh && \

echo - AWISC started
echo - ------------------------------------------------------------------------