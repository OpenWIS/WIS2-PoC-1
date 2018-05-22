#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating RDSH Mosquitto Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Performing clean-up...
rm -rf ../../../artifacts/mosquitto
mkdir ../../../artifacts/mosquitto
mkdir ../../../artifacts/mosquitto/data
echo - Done.
echo

echo - Assembling Docker Image...
echo
cp mosquitto.dockerfile ../../../artifacts/mosquitto && \
    cp ../injected/rdsh-docker-injected-mosquitto-entrypoint.sh ../../../artifacts/mosquitto && \
    cp ../injected/rdsh-docker-injected-mosquitto.passwd ../../../artifacts/mosquitto && \
    cp ../injected/rdsh-docker-injected-mosquitto.conf ../../../artifacts/mosquitto && \
    cd ../../../artifacts/mosquitto


sudo docker build --no-cache -f mosquitto.dockerfile -t openwis-mosquitto .

echo
echo - Creating docker container
echo



sudo docker create -v $(pwd)/../../../artifacts/mosquitto/data:/mosquitto/data \
    -v $(pwd)/../../../logs/mosquitto:/mosquitto/log -p 1883:1883 -p 9001:9001 \
    --network openwis-docker-bridge \
    --name openwis-rdsh-mosquitto openwis-mosquitto