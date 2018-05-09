#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Deleting all AWISC artifacts
echo - ------------------------------------------------------------------------
echo

./remove/remove-awisc-mysql.sh && \
    ./remove/remove-awisc-elasticsearch.sh && \
    ./remove/remove-awisc-karaf.sh && \

echo
echo - AWISC artifacts deleted
echo - ------------------------------------------------------------------------

