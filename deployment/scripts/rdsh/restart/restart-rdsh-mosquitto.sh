#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting the RDSH Mosquitto
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-rdsh-mosquitto

echo - RDSH Mosquitto started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-rdsh-mosquitto /bin/bash
