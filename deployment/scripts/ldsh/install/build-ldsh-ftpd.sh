#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating LDSH FTPD Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Creating Docker Container...
sudo docker run --name=openwis-ldsh-ftpd -p 21:21 -p 30000-30009:30000-30009 -e "PUBLICHOST=localhost" \
    --network openwis-docker-bridge -d stilliard/pure-ftpd:latest
echo

echo - Stopping Docker Container...
echo
sudo docker stop openwis-ldsh-ftpd


echo - LDSH FTPD Docker Container Created Successfully
