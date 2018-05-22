#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the RDSH Mosquitto
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-rdsh-mosquitto

echo
echo - Configuring Docker Container...
echo

sudo docker cp ../injected/rdsh-docker-injected-mosquitto.sh openwis-rdsh-mosquitto:/ && \
    sudo docker exec openwis-rdsh-mosquitto /rdsh-docker-injected-mosquitto.sh

echo - RDSH Mosquitto started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-rdsh-mosquitto /bin/sh
