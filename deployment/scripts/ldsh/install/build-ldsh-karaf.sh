#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating LDSH Karaf Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Assembling Docker Container...
echo
sudo docker create -v $(pwd)/../../../artifacts/ldsh:/root/.m2/repository \
    -v $(pwd)/../../../logs/ldsh:/opt/karaf/data/log -p 8181:8181 -p 5005:5005 \
    --network openwis-docker-bridge \
    --link openwis-ldsh-mysql --link openwis-ldsh-ftpd --link openwis-rdsh-karaf \
    --name openwis-ldsh-karaf openwis-karaf


echo - LDSH Karaf Docker Container Created Successfully
