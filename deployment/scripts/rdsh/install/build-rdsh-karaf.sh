#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating RDSH Karaf Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Assembling Docker Container...
echo
sudo docker create -v $(pwd)/../../../artifacts/rdsh:/root/.m2/repository \
    -v $(pwd)/../../../logs/rdsh:/opt/karaf/data/log -p 8182:8181 \
    --network openwis-docker-bridge \
    --link openwis-rdsh-mysql --link openwis-rdsh-mosquitto \
    --name openwis-rdsh-karaf openwis-karaf


echo - RDSH Karaf Docker Container Created Successfully
