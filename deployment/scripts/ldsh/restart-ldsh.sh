#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting LDSH
echo - ------------------------------------------------------------------------
echo && echo

cd restart && \
    ./restart-ldsh-mysql.sh && \
    ./restart-ldsh-ftpd.sh && \
    ./restart-ldsh-karaf.sh && 

echo - LDSH started
echo - ------------------------------------------------------------------------