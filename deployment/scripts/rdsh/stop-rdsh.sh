#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Stopping RDSH
echo - ------------------------------------------------------------------------
echo

cd stop && \
    ./stop-rdsh-mysql.sh && \
    ./stop-rdsh-mosquitto.sh && \
    ./stop-rdsh-karaf.sh &&

echo - RDSH stopped