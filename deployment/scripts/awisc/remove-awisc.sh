#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Deleting all AWISC artifacts
echo - ------------------------------------------------------------------------
echo

cd remove && \
    ./remove-awisc-mysql.sh && \
    ./remove-awisc-elasticsearch.sh && \
    ./remove-awisc-karaf.sh &&

echo
echo - AWISC artifacts deleted

