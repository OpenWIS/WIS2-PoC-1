#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Deleting all RDSH artifacts
echo - ------------------------------------------------------------------------
echo

cd remove && \
    ./remove-rdsh-mysql.sh && \
    ./remove-rdsh-mosquitto.sh && \
    ./remove-rdsh-karaf.sh &&

echo
echo - RDSH artifacts deleted

