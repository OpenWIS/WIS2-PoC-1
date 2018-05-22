#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Stopping AWISC
echo - ------------------------------------------------------------------------
echo

cd stop && \
    ./stop-awisc-mysql.sh && \
    ./stop-awisc-elasticsearch.sh && \
    ./stop-awisc-karaf.sh &&

echo - AWISC stopped