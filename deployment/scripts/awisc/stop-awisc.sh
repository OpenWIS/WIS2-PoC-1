#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Stopping AWISC
echo - ------------------------------------------------------------------------
echo && echo

./stop/stop-awisc-mysql.sh && \
    ./stop/stop-awisc-elasticsearch.sh && \
    ./stop/stop-awisc-karaf.sh && \

echo - AWISC stopped
echo - ------------------------------------------------------------------------