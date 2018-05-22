#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Deleting all LDSH artifacts
echo - ------------------------------------------------------------------------
echo

cd remove && \
    ./remove-ldsh-mysql.sh && \
    ./remove-ldsh-ftpd.sh && \
    ./remove-ldsh-karaf.sh &&

echo
echo - LDSH artifacts deleted

